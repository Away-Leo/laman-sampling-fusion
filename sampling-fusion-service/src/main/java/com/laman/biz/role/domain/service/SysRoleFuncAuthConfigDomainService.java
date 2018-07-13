package com.laman.biz.role.domain.service;

import com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto;
import com.laman.biz.role.domain.entity.SysRoleFuncAuthConfig;
import com.laman.biz.role.domain.repository.SysRoleFuncAuthConfigRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
* @Title: SysRoleConfigDomainService
* @Description:  角色功能权限配置数据服务
* @Author: Away
* @Date: 2018/6/1 17:48
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class SysRoleFuncAuthConfigDomainService extends BaseDomainService<SysRoleFuncAuthConfigRepository,SysRoleFuncAuthConfig,SysRoleFuncAuthConfigDto>{

    /**
     * @Method:  findByRoleCodes
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色编号查找功能权限列表
     * @Param: roleCodes
     * @Return: java.util.List<com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto>
     * @Date: 2018/6/3 17:25
     */
    public List<SysRoleFuncAuthConfigDto> findByRoleCodes(String roleCodes){
        if(ObjectHelper.isNotEmpty(roleCodes)){
            return toDtoList(this.CT.findDistinctByRoleCodeIn(Arrays.asList(roleCodes.split(","))),SysRoleFuncAuthConfigDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  findByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查询功能权限分页数据
     * @Param: pageable
     * @Param: conditions
     * @Return: org.springframework.data.domain.Page<com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto>
     * @Date: 2018/6/13 16:00
     */
    public Page<SysRoleFuncAuthConfigDto> findByConditions(Pageable pageable,SysRoleFuncAuthConfigDto conditions) throws BusinessException{
        if(ObjectHelper.isNotEmpty(conditions)&&ObjectHelper.isNotEmpty(pageable)){
            return toDtoPage(this.CT.findByCondition(pageable, conditions),SysRoleFuncAuthConfigDto.class,pageable);
        }else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  findByRoleCodeAndFuncUrl
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色编号和功能地址查找
     * @Param: roleCodes
     * @Param: funcUrl
     * @Return: java.util.List<com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto>
     * @Date: 2018/6/13 18:47
     */
    public List<SysRoleFuncAuthConfigDto> findByRoleCodeAndFuncUrl(String roleCodes,String funcUrl) throws BusinessException{
        if(ObjectHelper.isNotEmpty(roleCodes)&&ObjectHelper.isNotEmpty(funcUrl)){
            return toDtoList(CT.findDistinctByRoleCodeInAndAndFuncUrl(Arrays.asList(roleCodes.split(",")),funcUrl),SysRoleFuncAuthConfigDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }
}
