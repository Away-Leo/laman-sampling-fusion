package com.laman.biz.user.app.service;

import com.laman.biz.user.app.dto.*;
import com.laman.biz.user.domain.entity.User;
import com.laman.biz.user.domain.entity.UserInfo;
import com.laman.biz.user.domain.service.*;
import com.laman.fusion.base.CPContext;
import com.laman.fusion.base.CPContext.SeUserInfo;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.laman.fusion.base.util.ObjectProperUtil;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Title: UserAppService.java
 * @Description: 用户服务
 * @Author: Away
 * @Date: 2018/4/12 13:53
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Service
@Transactional
public class UserAppService {

    private final UserDomainService userDomainService;

    private final UserInfoAppService userInfoAppService;
    
    private final UserInfoDomainService userInfoDomainService;

    @Autowired
    public UserAppService(UserDomainService userDomainService,
    	UserInfoAppService userInfoAppService,
    	UserInfoDomainService userInfoDomainService) {
        this.userDomainService = userDomainService;
        this.userInfoAppService = userInfoAppService;
        this.userInfoDomainService = userInfoDomainService;
    }


    /**
     * @Author: Away
     * @Description: 按照条件查找
     * @Param: pageable
     * @Param: conditions
     * @Return org.springframework.data.domain.Page<com.laman.biz.user.app.dto.UserDto>
     * @Date 2018/3/1 14:54
     * @Copyright 重庆拉曼科技有限公司
     */
    public Page<UserDto> findManageUserPage(Pageable pageable, UserDto conditions) {
        return this.userDomainService.findManageUserPageByCondition(pageable, conditions);
    }

    /**
     * @Author: Away
     * @Title: findByUserName
     * @Description: 按照注册帐号查找
     * @Param: userName 注册帐号
     * @Return: java.util.List<com.laman.biz.user.app.dto.UserDto>
     * @Date: 2018/4/12 15:01
     * @Version: 2018/4/12 15:01
     */
    public UserDto findByUserName(String userName) throws BusinessException {
        return this.userDomainService.findByUserName(userName);
    }

    /**
     * @Method:  findUserInfoByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照用户ID查找用户基本信息
     * @Param: id
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/6/3 17:08
     */
    public UserInfoDto findUserInfoByUserId(Long id) throws BusinessException {
        return this.userInfoAppService.findByUserId(id);
    }

    /**
     * @Method:  findCurrentUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 查找当前登录用户的基本信息
     * @Param:
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/6/1 14:32
     */
    public UserInfoDto findCurrentUserInfo() throws Exception{
        return this.userInfoAppService.findCurrentUserInfo();
    }

    /**
     * @Author: Away
     * @Title: userRegister
     * @Description: 用户注册
     * @Param: toRegisterUser 待注册用户数据
     * @Return: com.laman.biz.user.app.dto.UserDto
     * @Date: 2018/4/12 14:59
     * @Version: 2018/4/12 14:59
     */
    public UserDto userRegister(LoginModel toRegisterUser) throws Exception {
        if (ObjectHelper.isNotEmpty(toRegisterUser)) {
            //同账户验证
            UserDto sameNameUser = this.userDomainService.findByUserName(toRegisterUser.getUserName());
            //同邮箱验证
            List<UserInfoDto> sameEmalUsers=this.userInfoAppService.findByEmail(toRegisterUser.getEmailAddress());
            if(ObjectHelper.isNotEmpty(sameEmalUsers)&&sameEmalUsers.size()>0)throw new BusinessException(ENUM_EXCEPTION.E10029.code,ENUM_EXCEPTION.E10029.msg);
            if (ObjectHelper.isEmpty(sameNameUser)) {
                //密码掩码器
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                toRegisterUser.setPassword(bCryptPasswordEncoder.encode(toRegisterUser.getPassword()));
                //新建账号
                UserDto createdUser = this.userDomainService.createUser(ObjectProperUtil.compareAndValue(toRegisterUser,new UserDto(),true,null));
                //新建用户信息数据
                createdUser.setEmail(toRegisterUser.getEmailAddress());//赋值邮件地址
                this.userInfoAppService.creatNewUserInfo(createdUser);
                return createdUser;
            } else {
                throw new BusinessException(ENUM_EXCEPTION.E10010.code, ENUM_EXCEPTION.E10010.msg);
            }
        } else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code, ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  saveOrUpdateUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 新增或修改用户信息
     * @Param: userInfoDto
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/6/1 14:19
     */
    public UserInfoDto saveOrUpdateUserInfo(UserInfoDto userInfoDto) throws Exception{
        return this.userInfoAppService.saveOrUpdateUserInfo(userInfoDto);
    }

    /**
     * @param id 用户id
     * @throws Exception
     * @retunrn 根据id物理删除用户
     **/
    public void deleteUserInfo(Long id) throws Exception{
    	this.userInfoAppService.physicalDelete(id);
    }
    
    /**
     * @param deptId
     * @return 根据部门id获取用户列表
     **/
    public List<UserInfoDto> findUserInfoDto(Long deptId){
    	List<UserInfoDto> list = userInfoDomainService.findByUserInfoByDeptId(deptId);
    	return list;
    }
    
    public void updatePassword(UserInfoDto userInfoDto) throws Exception{
    	SeUserInfo userInfo = CPContext.getContext();
    	Long userId = userInfo.getId();
    	//密码掩码器
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	String password = bCryptPasswordEncoder.encode(userInfoDto.getPassword());
    	userDomainService.updateUser(userId,password);
    }
    
    public void setSingture(UserInfoDto userInfoDto) throws Exception{
    	SeUserInfo userInfo = CPContext.getContext();
    	Long userId = userInfo.getId();
    	userInfoDomainService.updateSignature(userId,userInfoDto.getSignature());
    }
    
    /**
     * @param userInfoDto
     * @throws Exception
     * @Return 保存用户信息 如果没有则添加 有修改
     **/
    public UserInfoDto saveOrUpdateUser(UserInfoDto userInfoDto) throws Exception{
    	if(userInfoDto.getId() == null){
    		UserDto userDto = new UserDto();
    		userDto.setUserName(userInfoDto.getPesonCode());//操作员编码作为用户名
    		BCryptPasswordEncoder bEncoder = new BCryptPasswordEncoder();
        	String password = bEncoder.encode("123456");
    		userDto.setPassword(password);
    		UserDto user = userDomainService.saveData(userDto, User.class);
    		userInfoDto.setUserId(user.getId());
    	}
    	return userInfoDomainService.saveData(userInfoDto, UserInfo.class);
    }
    
}
