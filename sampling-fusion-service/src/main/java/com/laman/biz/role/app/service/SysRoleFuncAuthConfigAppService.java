package com.laman.biz.role.app.service;

import com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto;
import com.laman.biz.role.domain.entity.SysRoleFuncAuthConfig;
import com.laman.biz.role.domain.service.SysRoleFuncAuthConfigDomainService;
import com.laman.fusion.base.base.BaseAppService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
* @Title: SysRoleConfigDomainService
* @Description:  角色功能权限配置应用服务
* @Author: Away
* @Date: 2018/6/1 17:48
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
@Transactional
public class SysRoleFuncAuthConfigAppService extends BaseAppService<SysRoleFuncAuthConfigDomainService>{

    /**
     * @Method:  findFuncByRoles
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色编号查找功能权限
     * @Param: roles
     * @Return: java.util.List<com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto>
     * @Date: 2018/6/3 17:28
     */
    public List<SysRoleFuncAuthConfigDto> findFuncByRoles(String roles){
        return this.BDS.findByRoleCodes(roles);
    }

    /**
     * @Method:  findPageByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查询功能权限分页数据
     * @Param: pageable
     * @Param: conditions
     * @Return: org.springframework.data.domain.Page<com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto>
     * @Date: 2018/6/13 16:03
     */
    public Page<SysRoleFuncAuthConfigDto> findPageByConditions(Pageable pageable,SysRoleFuncAuthConfigDto conditions) throws BusinessException{
        return this.BDS.findByConditions(pageable, conditions);
    }

    /**
     * @Method:  batchSaveWithouRepeat
     * @Author: Away
     * @Version: v1.0
     * @See: 批量保存不重复的数据
     * @Param: sourceData
     * @Return: java.util.List<com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto>
     * @Date: 2018/6/13 18:52
     */
    public List<SysRoleFuncAuthConfigDto> batchSaveWithouRepeat(List<SysRoleFuncAuthConfigDto> sourceData) throws Exception{
        if(ObjectHelper.isNotEmpty(sourceData)){
            List<SysRoleFuncAuthConfig> toSaveData=new ArrayList<>();
            for(SysRoleFuncAuthConfigDto temp:sourceData){
                List<SysRoleFuncAuthConfigDto> haseSavedData=this.BDS.findByRoleCodeAndFuncUrl(temp.getRoleCode(),temp.getFuncUrl());
                if(ObjectHelper.isEmpty(haseSavedData)||haseSavedData.size()==0){
                    toSaveData.add(temp.toEntity(SysRoleFuncAuthConfig.class));
                }
            }
            return this.BDS.batchSave(toSaveData,SysRoleFuncAuthConfigDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }
}
