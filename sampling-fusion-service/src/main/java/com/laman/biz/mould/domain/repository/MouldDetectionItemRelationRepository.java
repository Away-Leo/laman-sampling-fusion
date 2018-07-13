package com.laman.biz.mould.domain.repository;

import com.laman.biz.mould.app.dto.MouldDetectionItemRelationDto;
import com.laman.biz.mould.domain.entity.MouldDetectionItemRelation;
import com.laman.fusion.base.base.BaseRepository;
import com.laman.fusion.base.util.ObjectHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Title: MouldItemRelationRepository
* @Description:  模板检测项关系自定义操作库
* @Author: Away
* @Date: 2018/7/6 10:09
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface MouldDetectionItemRelationRepository extends BaseRepository<MouldDetectionItemRelation,Long>{

    /**
     * @Method:  findByItemCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照所属检测项查询模板
     * @Param: pageRequest
     * @Param: itemCode
     * @Return: org.springframework.data.domain.Page<com.laman.biz.mould.domain.entity.MouldDetectionItemRelation>
     * @Date: 2018/7/6 10:11
     */
    Page<MouldDetectionItemRelation> findByDetectiveItemCode(Pageable pageable, String itemCode);

    /**
     * @Method:  findPageByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 通过条件分页查询
     * @Param: pageable
     * @Param: conditions
     * @Return: org.springframework.data.domain.Page<com.laman.biz.mould.domain.entity.MouldDetectionItemRelation>
     * @Date: 2018/7/9 15:20
     */
    default Page<MouldDetectionItemRelation> findPageByConditions(Pageable pageable, MouldDetectionItemRelationDto conditions){
        StringBuilder hql=new StringBuilder("from MouldDetectionItemRelation r where 1=1 ");
        Map<String,Object> params=new HashMap<>();
        if(ObjectHelper.isNotEmpty(conditions.getDetectiveItemCode())){
            hql.append(" and r.detectiveItemCode = :detectiveItemCode ");
            params.put("detectiveItemCode",conditions.getDetectiveItemCode());
        }
        hql.append(" order by r.rawAddTime desc ");
        return this.findByHqlPage(pageable,hql.toString(),params);
    }

    /**
     * @Method:  findByItemCodeAndMouldTreeId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据检测项编码和模板所属节点ID查找
     * @Param: itemCode
     * @Param: mouldTreeId
     * @Return: com.laman.biz.mould.domain.entity.MouldDetectionItemRelation
     * @Date: 2018/7/6 10:25
     */
    MouldDetectionItemRelation findByDetectiveItemCodeAndMouldTreeId(String itemCode,Long mouldTreeId);

    /**
     * @Method:  findByDetectiveItemCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据检测项查询模板
     * @Param: detectiveItemCode
     * @Return: java.util.List<com.laman.biz.mould.domain.entity.MouldDetectionItemRelation>
     * @Date: 2018/7/9 11:29
     */
    List<MouldDetectionItemRelation> findByDetectiveItemCode(String detectiveItemCode);
}
