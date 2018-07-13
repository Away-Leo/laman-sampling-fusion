package com.laman.biz.task.domain.repository;

import com.laman.biz.task.domain.entity.Task;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: TaskDomainRepository
* @Description:  事项自定义操作库
* @Author: Away
* @Date: 2018/7/6 20:09
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface TaskRepository extends BaseRepository<Task,Long>{

    /**
     * @Method:  findByTaskBusinessCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照
     * @Param: taskBusiness
     * @Return: com.laman.biz.task.domain.entity.Task
     * @Date: 2018/7/7 11:11
     */
    Task findByBusinessCode(String taskBusiness);

    /**
     * @Method:  findBySamplingPendingStatus
     * @Author: Away
     * @Version: v1.0
     * @See: 根据任务状态查询
     * @Param: samplingPendingStatus
     * @Return: java.util.List<com.laman.biz.task.domain.entity.Task>
     * @Date: 2018/7/11 22:34
     */
    List<Task> findBySamplingPendingStatus(String samplingPendingStatus);

}
