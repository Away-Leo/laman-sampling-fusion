package com.laman.biz.tableproperty.domain.repository;

import com.laman.biz.tableproperty.app.dto.TablePropertyDto;
import com.laman.biz.tableproperty.domain.entity.TableProperty;
import com.laman.fusion.base.base.BaseRepository;
import com.laman.fusion.base.util.ObjectHelper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Title: TablePropertyRepository
* @Description:  表格属性自定义操作库
* @Author: Away
* @Date: 2018/7/11 10:25
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface TablePropertyRepository extends BaseRepository<TableProperty,Long>{

    /**
     * @Method:  findByPropertyCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照属性编号查找
     * @Param: propertyCode
     * @Return: com.laman.biz.tableproperty.domain.entity.TableProperty
     * @Date: 2018/7/11 14:16
     */
    TableProperty findByPropertyCode(String propertyCode);

    /**
     * @Method:  findByPropertyCategoryCodeGroup
     * @Author: Away
     * @Version: v1.0
     * @See: 查找所有大类
     * @Param: categoryCode
     * @Return: java.util.List<com.laman.biz.tableproperty.domain.entity.TableProperty>
     * @Date: 2018/7/11 14:51
     */
    @Query(" from TableProperty p group by p.propertyTreeId ")
    List<TableProperty> findAllPropertyTreeId();

    /**
     * @Method:  findByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查询
     * @Param: condition
     * @Return: java.util.List<com.laman.biz.tableproperty.domain.entity.TableProperty>
     * @Date: 2018/7/11 10:46
     */
    default List<TableProperty> findByConditions(TablePropertyDto condition){
        StringBuilder hql=new StringBuilder(" from TableProperty t where 1=1 ");
        Map<String,Object> param=new HashMap<>();
        if(ObjectHelper.isNotEmpty(condition.getPropertyTreeId())){
            hql.append(" and t.propertyTreeId = :propertyTreeId ");
            param.put("propertyTreeId",condition.getPropertyTreeId());
        }
        if(ObjectHelper.isNotEmpty(condition.getPropertyTreeName())){
            hql.append(" and t.propertyTreeName like :propertyTreeName ");
            param.put("propertyTreeName","%"+ StringEscapeUtils.escapeSql(condition.getPropertyTreeName())+"%");
        }
        if(ObjectHelper.isNotEmpty(condition.getPropertyName())){
            hql.append(" and t.propertyName like :propertyName ");
            param.put("propertyName","%"+StringEscapeUtils.escapeSql(condition.getPropertyName().trim())+"");
        }
        return this.findByHql(hql.toString(),param);
    }

    /**
     * @Method:  deleteByPropertyTreeId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照属性树ID删除
     * @Param: propertyTreeId
     * @Return: void
     * @Date: 2018/7/12 14:11
     */
    void deleteByPropertyTreeId(Long propertyTreeId);

}
