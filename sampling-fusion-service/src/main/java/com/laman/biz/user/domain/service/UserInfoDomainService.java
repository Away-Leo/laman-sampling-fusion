package com.laman.biz.user.domain.service;

import com.laman.biz.user.app.dto.UserDto;
import com.laman.biz.user.app.dto.UserInfoDto;
import com.laman.biz.user.domain.entity.UserInfo;
import com.laman.biz.user.domain.repository.UserInfoRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.enums.ENUM_IDENTIFY_TYPE;
import com.laman.fusion.base.enums.ENUM_USER_TYPE;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: UserInfoDomainService.java
 * @Description: 用户信息app服务
 * @Author: Away
 * @Date: 2018/4/12 14:25
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Service
public class UserInfoDomainService extends BaseDomainService<UserInfoRepository,UserInfo,UserInfoDto>{

    /**
     * 新增客户信息
     * @param UserInfoDto
     * @return
     */
    private UserInfo create(UserInfoDto UserInfoDto){
        UserInfo UserInfo = new UserInfo();
        BeanUtils.copyProperties(UserInfoDto,UserInfo);
        return CT.save(UserInfo);
    }

    /**
     * @Author: Away
     * @Title: creatNewUserInfo
     * @Description: 根据用户注册信息新建用户信息
     * @Param: userDto 用户注册信息
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/4/12 14:42
     * @Version: 2018/4/12 14:42
     */
    public UserInfoDto creatNewUserInfo(UserDto userDto) throws Exception{
        if(ObjectHelper.isNotEmpty(userDto)){
            UserInfoDto userInfoDto=new UserInfoDto();
            userInfoDto.setUserId(userDto.getId());
            userInfoDto.setIdentifyCode(ENUM_IDENTIFY_TYPE.WAITTING.code);
            userInfoDto.setRoles(ENUM_USER_TYPE.USER.code);
            userInfoDto.setEmail(userDto.getEmail());
            return this.saveOrUpdateData(userInfoDto,UserInfo.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据用户ID查找用户基本信息
     * @Param: userId
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/6/1 14:30
     */
    public UserInfoDto findByUserId(Long userId) throws BusinessException{
        if(ObjectHelper.isNotEmpty(userId)){
            UserInfo userInfo=this.CT.findByUserId(userId);
            if(ObjectHelper.isNotEmpty(userInfo)){
                return userInfo.to(UserInfoDto.class);
            }else{
                return null;
            }
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  findByConditons
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查找分页数据
     * @Param: pageRequest
     * @Param: userInfoDto
     * @Return: org.springframework.data.domain.Page<com.laman.biz.user.app.dto.UserInfoDto>
     * @Date: 2018/6/4 17:15
     */
    public Page<UserInfoDto> findByConditons(PageRequest pageRequest,UserInfoDto userInfoDto) throws BusinessException{
        if(ObjectHelper.isNotEmpty(pageRequest)){
            return toDtoPage(CT.findPageByConditins(pageRequest, userInfoDto),UserInfoDto.class,pageRequest);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10028.code,ENUM_EXCEPTION.E10028.msg);
        }
    }

    /**
     * @Method:  findByEmail
     * @Author: Away
     * @Version: v1.0
     * @See: 按照电子邮件查找
     * @Param: email
     * @Return: java.util.List<com.laman.biz.user.app.dto.UserInfoDto>
     * @Date: 2018/6/15 17:37
     */
    public List<UserInfoDto> findByEmail(String email) throws BusinessException{
        if(ObjectHelper.isNotEmpty(email)){
            return toDtoList(CT.findByEmail(email),UserInfoDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    public List<UserInfoDto> findByUserInfoByDeptId(Long deptId) throws BusinessException{
    	if(ObjectHelper.isNotEmpty(deptId)){
    		return toDtoList(this.CT.findByDeptIdOrderById(deptId),UserInfoDto.class);
    	}else{
    		throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
    	}
    }
    
    public void updateSignature(Long userId,String signatureUrl) throws Exception{
    	UserInfo userInfo = this.CT.findByUserId(userId);
    	userInfo.setSignature(signatureUrl);
    	this.CT.updateEntity(userInfo);
    }
    
    
     
}
