package com.laman.biz.task.domain.repository;

import com.laman.biz.task.domain.entity.DetectiveItem;
import com.laman.fusion.base.base.BaseRepository;

/**
* @Title: DetectiveItemRepository
* @Description:  检测项自定义操作库
* @Author: Away
* @Date: 2018/7/7 11:06
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface DetectiveItemRepository extends BaseRepository<DetectiveItem,Long>{

    /**
     * @Method:  findByBusinessCodeAndDetectiveItemCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据业务单号和检测项查找
     * @Param: businessCode
     * @Param: detectiveItemCode
     * @Return: com.laman.biz.task.domain.entity.DetectiveItem
     * @Date: 2018/7/9 17:02
     */
    DetectiveItem findByBusinessCodeAndDetectiveCode(String businessCode,String detectiveItemCode);
}
