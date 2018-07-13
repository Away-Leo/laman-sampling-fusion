package com.laman.biz.user.app.service;

import com.laman.biz.user.app.dto.UserDto;
import com.laman.biz.user.app.dto.UserInfoDto;
import com.laman.biz.user.domain.entity.UserInfo;
import com.laman.biz.user.domain.service.UserInfoDomainService;
import com.laman.fusion.base.CPContext;
import com.laman.fusion.base.base.BaseAppService;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Title: UserInfoAppService.java
 * @Description:  用户信息app服务
 * @Author: Away
 * @Date: 2018/4/12 14:19
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Transactional
@Service
public class UserInfoAppService extends BaseAppService<UserInfoDomainService>{

    /**
     * @Author: Away
     * @Title: creatNewUserInfo
     * @Description: 根据用户表数据新建用户信息数据
     * @Param: userDto
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/4/12 14:22
     * @Version: 2018/4/12 14:22
     */
    public UserInfoDto creatNewUserInfo(UserDto userDto) throws Exception{
        return this.BDS.creatNewUserInfo(userDto);
    }

    /**
     * @Method:  findCurrentUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 查找当前登录用户的基本信息
     * @Param:
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/6/1 14:31
     */
    public UserInfoDto findCurrentUserInfo() throws Exception{
        return this.BDS.findByUserId(CPContext.getContext().getId());
    }

    /**
     * @Method:  saveOrUpdateUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 保存或修改用户信息
     * @Param: userInfoDto
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/6/1 14:18
     */
    public UserInfoDto saveOrUpdateUserInfo(UserInfoDto userInfoDto) throws Exception{
        return BDS.saveOrUpdateData(userInfoDto, UserInfo.class);
    }

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照用户ID查找当前用户基本信息
     * @Param: userId
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/6/3 17:07
     */
    public UserInfoDto findByUserId(Long userId) throws BusinessException {
        return this.BDS.findByUserId(userId);
    }

    /**
     * @Method:  findPageByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据参数查找分页
     * @Param: pageRequest
     * @Param: conditions
     * @Return: org.springframework.data.domain.Page<com.laman.biz.user.app.dto.UserInfoDto>
     * @Date: 2018/6/4 17:17
     */
    public Page<UserInfoDto> findPageByConditions(PageRequest pageRequest,UserInfoDto conditions) throws BusinessException{
        return this.BDS.findByConditons(pageRequest, conditions);
    }

    /**
     * @Method:  findByEmail
     * @Author: Away
     * @Version: v1.0
     * @See: 按照邮件地址查找
     * @Param: email
     * @Return: java.util.List<com.laman.biz.user.app.dto.UserInfoDto>
     * @Date: 2018/6/15 17:43
     */
    public List<UserInfoDto> findByEmail(String email) throws BusinessException{
        return this.BDS.findByEmail(email);
    }
    
    /**
     * @param userId 当前用户id
     * @param signatureUrl 签名图片地址
     * @throws Exception 修改当前图片签名地址
     **/
    public void updateSignature(Long userId,String signatureUrl) throws Exception{
    	this.BDS.updateSignature(userId, signatureUrl);
    }
}
