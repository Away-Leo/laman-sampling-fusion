package com.laman.biz.role.domain.repository;

import com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto;
import com.laman.biz.role.domain.entity.SysRoleFuncAuthConfig;
import com.laman.fusion.base.base.BaseRepository;
import com.laman.fusion.base.util.ObjectHelper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Title: SysRoleConfigRepository
* @Description:  角色功能配置操作库
* @Author: Away
* @Date: 2018/6/1 17:38
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface SysRoleFuncAuthConfigRepository extends BaseRepository<SysRoleFuncAuthConfig,Long>{

    /**
     * @Method:  findByRoleCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色编号查找相应的功能权限配置
     * @Param: roleCode
     * @Return: java.util.List<com.laman.biz.role.domain.entity.SysRoleFuncAuthConfig>
     * @Date: 2018/6/3 17:20
     */
    List<SysRoleFuncAuthConfig> findDistinctByRoleCodeIn(List<String> roleCodes);

    /**
     * @Method:  findByCondition
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查询功能权限分页数据
     * @Param: pageable
     * @Param: conditions
     * @Return: org.springframework.data.domain.Page<com.laman.biz.role.domain.entity.SysRoleFuncAuthConfig>
     * @Date: 2018/6/13 15:58
     */
    default Page<SysRoleFuncAuthConfig> findByCondition(Pageable pageable, SysRoleFuncAuthConfigDto conditions){
        StringBuilder hql=new StringBuilder(" from SysRoleFuncAuthConfig s where 1=1 ");
        Map<String,Object> param=new HashMap<>();
        if(ObjectHelper.isNotEmpty(conditions)){
            //功能名称
            if(ObjectHelper.isNotEmpty(conditions.getFuncName())){
                hql.append(" and s.funcName like :funcName ");
                param.put("funcName","%"+ StringEscapeUtils.escapeSql(conditions.getFuncName().trim())+"%");
            }
            //角色编号
            if(ObjectHelper.isNotEmpty(conditions.getRoleCode())){
                hql.append(" and s.roleCode = :roleCode ");
                param.put("roleCode",conditions.getRoleCode());
            }
            //所属菜单
            if(ObjectHelper.isNotEmpty(conditions.getBelongMenu())){
                hql.append(" and s.belongMenu like :belongMenu ");
                param.put("belongMenu","%"+StringEscapeUtils.escapeSql(conditions.getBelongMenu())+"%");
            }
            //功能地址
            if(ObjectHelper.isNotEmpty(conditions.getFuncUrl())){
                hql.append(" and funcUrl like :funcUrl ");
                param.put("belongMenu","%"+StringEscapeUtils.escapeSql(conditions.getFuncUrl())+"%");
            }
        }
        return this.findByHqlPage(pageable,hql.toString(),param);
    }

    /**
     * @Method:  findDistinctByRoleCodeInAndAndFuncUrl
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色编号和功能地址查找
     * @Param: roleCodes
     * @Param: funcUrl
     * @Return: java.util.List<com.laman.biz.role.domain.entity.SysRoleFuncAuthConfig>
     * @Date: 2018/6/13 18:43
     */
    List<SysRoleFuncAuthConfig> findDistinctByRoleCodeInAndAndFuncUrl(List<String> roleCodes,String funcUrl);
}
