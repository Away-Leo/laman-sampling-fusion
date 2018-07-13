package com.laman.biz.menu.app.service;


import com.laman.biz.menu.app.dto.SysMenuConfigDto;
import com.laman.biz.menu.domain.entity.SysMenuConfig;
import com.laman.biz.menu.domain.service.SysMenuConfigDomainService;
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
* @Title: SysUrlsDomainService.java
* @Description:  系统菜单配置服务
* @author Away
* @Date: 2018/2/7 18:26
* @Copyright: 重庆拉曼科技有限公司
* @version V1.0
*/
@Service
@Transactional
public class SysMenuConfigAppService extends BaseAppService<SysMenuConfigDomainService>{

    /**
     * @Author: Away
     * @Description: 更新或保存系统菜单配置
     * @Param: sourceData
     * @Return: com.laman.biz.menu.app.dto.SysMenuConfigDto
     * @Date: 2018/2/8 11:30
     * @Copyright: 重庆拉曼科技有限公司
     */
    public SysMenuConfigDto saveOrUpdate(SysMenuConfigDto sourceData) throws Exception{
        return this.BDS.saveOrUpdateData(sourceData, SysMenuConfig.class);
    }

    /**
     * @Author: Away
     * @Description: 获得超级管理员的树形菜单
     * @Param:
     * @Return: java.util.List<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/2/8 16:44
     * @Copyright: 重庆拉曼科技有限公司
     */
    public List<SysMenuConfigDto> getAdminTree(boolean isLogin) throws Exception{
        List<SysMenuConfigDto> sourceData=this.BDS.getAdminTree();
        if(isLogin){
            if(ObjectHelper.isEmpty(sourceData)||sourceData.size()==0){
                sourceData=new ArrayList<>();
                SysMenuConfigDto dto1=new SysMenuConfigDto();
                dto1.setMenuName("系统设置");
                dto1.setMenuCode("set");
                SysMenuConfigDto dto2=new SysMenuConfigDto();
                dto2.setMenuName("菜单设置");
                dto2.setMenuCode("set-menu");
                dto2.setMenuParentCode("set");
                dto1.getNodes().add(dto2);
                sourceData.add(dto1);

                SysMenuConfigDto dto3=new SysMenuConfigDto();
                dto3.setMenuName("个人信息");
                dto3.setMenuCode("info");
                SysMenuConfigDto dto4=new SysMenuConfigDto();
                dto4.setMenuName("个人信息详情");
                dto4.setMenuCode("info-person");
                dto4.setMenuParentCode("info");
                dto3.getNodes().add(dto4);

                sourceData.add(dto3);
            }
        }
        return sourceData;
    }

    /**
     * @Method:  getAllMenuTreeWithoutCommon
     * @Author: Away
     * @Version: v1.0
     * @See: 获取所有非公共的菜单树
     * @Param:
     * @Return: java.util.List<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/6/1 16:49
     */
    public List<SysMenuConfigDto> getAllMenuTreeWithoutCommon() throws Exception{
        return this.BDS.getMenuTreeWithouCommon();
    }

    /**
     * @Author: Away
     * @Description: 根据参数查找分页数据
     * @Param: pageable
     * @Param: condition
     * @Return: org.springframework.data.domain.Page<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/2/23 14:02
     * @Copyright: 重庆拉曼科技有限公司
     */
    public Page<SysMenuConfigDto> findByConditions(Pageable pageable,SysMenuConfigDto condition) throws Exception{
        return this.BDS.findByConditions(pageable, condition);
    }

    /**
     * @Method:  findListByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查找
     * @Param: pageable
     * @Param: condition
     * @Return: java.util.List<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/6/4 10:27
     */
    public List<SysMenuConfigDto> findListByConditions(SysMenuConfigDto condition) throws Exception{
        return this.BDS.findListByConditions(condition);
    }



    /**
     * @Author: Away
     * @Description: 根据ID删除
     * @Param: id
     * @Return: void
     * @Date: 2018/2/23 14:17
     * @Copyright: 重庆拉曼科技有限公司
     */
    public void deleteMenu(Long id) throws Exception{
        this.BDS.deleteSysMenuConf(id);
    }

    /**
     * @Method:  deleteMenuAndChildAndMenuAuth
     * @Author: Away
     * @Version: v1.0
     * @See: 删除菜单（对应的子菜单和角色菜单配置也一并删除）
     * @Param: userId
     * @Param: menuCodes
     * @Return: void
     * @Date: 2018/6/5 15:36
     */
    public void deleteMenuAndChildAndMenuAuth(String menuCodes) throws BusinessException{
        if(ObjectHelper.isNotEmpty(menuCodes)){
            //分解待删除菜单编号
            String[] menuCodeArray=menuCodes.split(",");
            for(String temp:menuCodeArray){
                //删除系统菜单配置
                BDS.deleteSysMenuConfByCode(temp);
//                //删除角色菜单配置
                BDS.deleteByMenuCode(temp);
            }
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  findByMenuCode
     * @Author: Away
     * @Version: v1.0
     * @See: 通过菜单编号查找
     * @Param: menuCode
     * @Return: com.laman.biz.menu.app.dto.SysMenuConfigDto
     * @Date: 2018/6/12 20:14
     */
    public SysMenuConfigDto findByMenuCode(String menuCode) throws BusinessException{
        return this.BDS.findByMenuCode(menuCode);
    }

}
