package com.laman.fusion.base.mail.domain.repository;

import com.laman.fusion.base.base.BaseRepository;
import com.laman.fusion.base.codecategory.domain.entity.CodeCategory;
import com.laman.fusion.base.mail.app.dto.EmailLogDto;
import com.laman.fusion.base.mail.domain.entity.EmailLog;
import com.laman.fusion.base.util.ObjectHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: CodeCategoryRepository.java
 * @Description: 树形菜单父级大类操作库
 * @Author: Away
 * @Date: 2018/4/23 16:57
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
public interface EmailLogRepository extends BaseRepository<EmailLog,Long>{

    /**
     * @Method:  findByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 按照条件查询
     * @Param: conditions
     * @Return: java.util.List<com.laman.fusion.base.mail.domain.entity.EmailLog>
     * @Date: 2018/6/13 16:30
     */
    default List<EmailLog> findByConditions(EmailLogDto conditions){
        StringBuilder hql=new StringBuilder(" from EmailLog e where 1=1 ");
        Map<String,Object> params=new HashMap<>();
        if(ObjectHelper.isNotEmpty(conditions.getEmailComponents())){
            hql.append(" and e.emailComponents = :EmailComponents ");
            params.put("emailComponents",conditions.getEmailComponents());
        }
        if(ObjectHelper.isNotEmpty(conditions.getEmailComponentsId())){
            hql.append(" and e.emailComponentsId = :emailComponentsId ");
            params.put("emailComponentsId",conditions.getEmailComponentsId());
        }
        if(ObjectHelper.isNotEmpty(conditions.getEmailComponents())){
            hql.append(" and e.emailComponents = :emailComponents ");
            params.put("emailComponents",conditions.getEmailComponents());
        }
        hql.append(" order by e,rawAddTime desc ");
        return this.findByHql(hql.toString(),params);
    }
}
