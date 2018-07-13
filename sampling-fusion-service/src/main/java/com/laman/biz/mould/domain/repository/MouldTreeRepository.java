package com.laman.biz.mould.domain.repository;

import com.laman.biz.mould.domain.entity.MouldTree;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: MouldTreeRepository
* @Description:  模板树自定义操作库
* @Author: Away
* @Date: 2018/7/4 16:34
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface MouldTreeRepository extends BaseRepository<MouldTree,Long>{

    /**
     * @Method:  findByTreeName
     * @Author: Away
     * @Version: v1.0
     * @See: 根据名称获得
     * @Param: treeName
     * @Return: java.util.List<com.laman.biz.mould.domain.entity.MouldTree>
     * @Date: 2018/7/4 16:37
     */
    List<MouldTree> findByTreeName(String treeName);

    /**
     * @Method:  findByTreeNameLike
     * @Author: Away
     * @Version: v1.0
     * @See: 根据名称模糊查询获得
     * @Param: treeName
     * @Return: java.util.List<com.laman.biz.mould.domain.entity.MouldTree>
     * @Date: 2018/7/4 16:37
     */
    List<MouldTree> findByTreeNameLike(String treeName);

}
