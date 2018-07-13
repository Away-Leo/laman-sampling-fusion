package com.laman.biz.role.app.service;

import com.laman.biz.menu.app.dto.SysMenuConfigDto;
import com.laman.biz.menu.app.service.SysMenuConfigAppService;
import com.laman.biz.menu.domain.service.SysMenuConfigDomainService;
import com.laman.biz.role.app.dto.SysRoleMenuAuthConfigDto;
import com.laman.biz.role.domain.entity.SysRoleMenuAuthConfig;
import com.laman.biz.role.domain.service.SysRoleMenuAuthConfigDomainService;
import com.laman.biz.user.app.dto.UserInfoDto;
import com.laman.biz.user.app.service.UserAppService;
import com.laman.fusion.base.base.BaseAppService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.enums.ENUM_USER_TYPE;
import com.laman.fusion.base.util.ObjectHelper;
import com.laman.fusion.base.util.ObjectProperUtil;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Title: SysRoleConfigDomainService
 * @Description: 角色菜单权限配置应用服务
 * @Author: Away
 * @Date: 2018/6/1 17:48
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Service
public class SysRoleMenuAuthConfigAppService extends BaseAppService<SysRoleMenuAuthConfigDomainService> {

    private final UserAppService userAppService;

    private final SysMenuConfigAppService sysMenuConfigAppService;

    @Autowired
    public SysRoleMenuAuthConfigAppService(UserAppService userAppService, SysMenuConfigAppService sysMenuConfigAppService) {
        this.userAppService = userAppService;
        this.sysMenuConfigAppService = sysMenuConfigAppService;
    }

    /**
     * @Method: findRoleMenuConf
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色获取菜单配置并组装树形菜单
     * @Param:
     * @Return: java.util.List<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/6/2 15:24
     */
    public List<SysMenuConfigDto> findRoleMenuConf(String roles, boolean isShowCommon) throws Exception {
        //获取当前登录用户基本信息
        UserInfoDto userInfoDto = userAppService.findCurrentUserInfo();
        //获取当前登录用户的角色信息
        String roleCodes = userInfoDto.getRoles();
        if (ObjectHelper.isNotEmpty(roles)) roleCodes = roles;
        if (ObjectHelper.isNotEmpty(roleCodes)) {
            //如果是超级管理员则查找所有菜单
            if (roleCodes.contains(ENUM_USER_TYPE.ADMIN.code)) {
                return sysMenuConfigAppService.getAdminTree(true);
            }
            String[] roleArray = roleCodes.split(",");
            //查找当前角色的菜单配置
            List<SysMenuConfigDto> menuConfs = BDS.findMenuByRoleCodesAndCast(Arrays.asList(roleArray));
            if (ObjectHelper.isEmpty(menuConfs)) menuConfs = new ArrayList<>();
            if (isShowCommon) {
                //查找公共菜单
                SysMenuConfigDto menuConfigDto = new SysMenuConfigDto();
                menuConfigDto.setCommon(true);
                List<SysMenuConfigDto> menuConfigDtos = this.sysMenuConfigAppService.findListByConditions(menuConfigDto);
                if (ObjectHelper.isNotEmpty(menuConfigDtos)) menuConfs.addAll(menuConfigDtos);
            }
            return SysMenuConfigDomainService.createTreeMenus(menuConfs);
        } else {
            return null;
        }
    }

    /**
     * @Method: batchSaveMenuAuth
     * @Author: Away
     * @Version: v1.0
     * @See: 批量保存角色菜单权限配置
     * @Param: roleCode
     * @Param: dtos
     * @Return: java.util.List<com.laman.biz.role.app.dto.SysRoleMenuAuthConfigDto>
     * @Date: 2018/6/5 17:56
     */
    public void batchSaveMenuAuth(String roleCode, String menus) throws Exception {
        if (ObjectHelper.isNotEmpty(roleCode) && ObjectHelper.isNotEmpty(menus)) {
            String[] menusArray = menus.split(",");
            this.deleteByRoles(roleCode);
            for (String temp : menusArray) {
                packageConf(roleCode, temp);
            }
        } else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code, ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  packageConf
     * @Author: Away
     * @Version: v1.0
     * @See: 自循环将菜单自身以及菜单对应的父级菜单保存进去
     * @Param: roleCode
     * @Param: menuCode
     * @Return: void
     * @Date: 2018/6/13 10:35
     */
    @Transactional
    public void packageConf( String roleCode, String menuCode) throws Exception {
        if (ObjectHelper.isNotEmpty(roleCode) && ObjectHelper.isNotEmpty(menuCode)) {
            //查找已经配置好的菜单权限
            SysRoleMenuAuthConfigDto savedData = this.BDS.findByRoleCodeAndMenuCode(roleCode, menuCode);
            SysMenuConfigDto sysMenuConfigDto = null;
            if (ObjectHelper.isEmpty(savedData)) {
                //查找相应菜单编号的菜单数据
                sysMenuConfigDto = this.sysMenuConfigAppService.findByMenuCode(menuCode);
                SysRoleMenuAuthConfigDto lineData = new SysRoleMenuAuthConfigDto();
                if (ObjectHelper.isNotEmpty(sysMenuConfigDto)) {
                    lineData.setRoleCode(roleCode);
                    lineData.setMenuCode(sysMenuConfigDto.getMenuCode());
                    lineData.setMenuName(sysMenuConfigDto.getMenuName());
                    lineData.setMenuParentCode(sysMenuConfigDto.getMenuParentCode());
                    lineData.setMenuParentName(sysMenuConfigDto.getMenuParentName());
                    this.BDS.saveData(lineData,SysRoleMenuAuthConfig.class);
                }
            }
            //如果当前菜单有父级菜单则将父级菜单加入循环中
            if (ObjectHelper.isNotEmpty(sysMenuConfigDto) && ObjectHelper.isNotEmpty(sysMenuConfigDto.getMenuParentCode())){
                packageConf(roleCode, sysMenuConfigDto.getMenuParentCode());
            }
        } else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code, ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  deleteByRoles
     * @Author: Away
     * @Version: v1.0
     * @See: 按照角色编号删除菜单权限配置
     * @Param: roleCodes
     * @Return: void
     * @Date: 2018/6/13 11:52
     */
    @Transactional
    public void deleteByRoles(String roleCodes) throws BusinessException{
        this.BDS.deleteByRoleCodes(roleCodes);
    }

    /**
     * @Method:  findMenuCodesByRoles
     * @Author: Away
     * @Version: v1.0
     * @See: 根据多个角色编号查找已经配置好的菜单编号
     * @Param: roleCodes
     * @Return: java.lang.String
     * @Date: 2018/6/14 15:37
     */
    public String findMenuCodesByRoles(String roleCodes) throws BusinessException{
        if(ObjectHelper.isNotEmpty(roleCodes)){
            return this.BDS.findMenuCodesByRoleCodes(Arrays.asList(roleCodes.split(",")));
        }else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }
}
