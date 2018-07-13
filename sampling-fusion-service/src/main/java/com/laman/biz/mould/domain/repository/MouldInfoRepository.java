package com.laman.biz.mould.domain.repository;

import com.laman.biz.mould.app.dto.MouldInfoDto;
import com.laman.biz.mould.domain.entity.MouldInfo;
import com.laman.fusion.base.base.BaseRepository;
import com.laman.fusion.base.util.ObjectHelper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

/**
* @Title: MouldInfoRepository
* @Description: 模板信息自定义操作库
* @Author: Away
* @Date: 2018/6/22 9:56
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface MouldInfoRepository extends BaseRepository<MouldInfo,Long>{

    /**
     * @Method:  findByMouldCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照模板唯一编号查找
     * @Param: mouldCode
     * @Return: com.laman.biz.mould.domain.entity.MouldInfo
     * @Date: 2018/6/22 9:58
     */
    MouldInfo findByMouldCode(String mouldCode);

    /**
     * @Method:  findByMouldBelongId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照所属树形节点ID查找
     * @Param: id
     * @Return: com.laman.biz.mould.domain.entity.MouldInfo
     * @Date: 2018/7/4 16:56
     */
    MouldInfo findByMouldBelongId(Long id);

    /**
     * @Method:  deleteByMouldBelongId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据所属节点ID删除模板
     * @Param: mouldBelongId
     * @Return: com.laman.biz.mould.domain.entity.MouldInfo
     * @Date: 2018/7/5 14:09
     */
    void deleteByMouldBelongId(Long mouldBelongId);

    /**
     * @Method:  findByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查询模板分页数据
     * @Param: pageable
     * @Param: mouldInfoDto
     * @Return: org.springframework.data.domain.Page<com.laman.biz.mould.domain.entity.MouldInfo>
     * @Date: 2018/7/5 16:35
     */
    default Page<MouldInfo> findByConditions(Pageable pageable, MouldInfoDto mouldInfoDto){
        StringBuilder hql=new StringBuilder("from MouldInfo m where 1=1");
        Map<String,Object> params=new HashMap<>();
        //所属节点ID
        if(ObjectHelper.isNotEmpty(mouldInfoDto.getMouldBelongId())){
            hql.append(" and m.mouldBelongId = :mouldBelongId ");
            params.put("mouldBelongId",mouldInfoDto.getMouldBelongId());
        }
        //所属节点名称
        if(ObjectHelper.isNotEmpty(mouldInfoDto.getMouldBelong())){
            hql.append(" and m.mouldBelong like :mouldBelong ");
            params.put("mouldBelong","%"+ StringEscapeUtils.escapeSql(mouldInfoDto.getMouldBelong())+"%");
        }
        //所属父节点ID
        if(ObjectHelper.isNotEmpty(mouldInfoDto.getMouldParentId())){
            hql.append(" and m.mouldParentId = :mouldParentId ");
            params.put("mouldParentId",mouldInfoDto.getMouldParentId());
        }
        //所属父节点名称
        if(ObjectHelper.isNotEmpty(mouldInfoDto.getMouldParentName())){
            hql.append(" and m.mouldParentName like :mouldParentName ");
            params.put("mouldParentName","%"+StringEscapeUtils.escapeSql(mouldInfoDto.getMouldParentName().trim())+"%");
        }
        //模板名称
        if(ObjectHelper.isNotEmpty(mouldInfoDto.getMouldName())){
            hql.append(" and m.mouldName like :mouldName  ");
            params.put("mouldName","%"+StringEscapeUtils.escapeSql(mouldInfoDto.getMouldName())+"%");
        }
        hql.append(" order by m.rawAddTime desc ");
        return this.findByHqlPage(pageable,hql.toString(),params);
    }
}
