package com.laman.biz.task.domain.repository;

import com.laman.biz.task.domain.entity.SamplingItem;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: SamplingItemRepository
* @Description:  样品自定义操作库
* @Author: Away
* @Date: 2018/7/6 20:11
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface SamplingItemRepository extends BaseRepository<SamplingItem,Long>{

    /**
     * @Method:  findByBusinessCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照业务编号查找样品
     * @Param: businessCode
     * @Return: java.util.List<com.laman.biz.task.domain.entity.SamplingItem>
     * @Date: 2018/7/6 20:48
     */
    List<SamplingItem> findByBusinessCode(String businessCode);

    /**
     * @Method:  findByIdIn
     * @Author: Away
     * @Version: v1.0
     * @See: 根据多个ID查找样品集合
     * @Param: ids
     * @Return: java.util.List<com.laman.biz.task.domain.entity.SamplingItem>
     * @Date: 2018/7/9 9:40
     */
    List<SamplingItem> findByIdIn(List<Long> ids);
}
