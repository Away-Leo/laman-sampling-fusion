package com.laman.web.backend.controller.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.laman.biz.task.app.dto.TaskDto;
import com.laman.biz.task.app.service.TaskAppService;
import com.laman.web.backend.controller.AbstractBackendController;
import com.laman.web.common.dto.CPViewResultInfo;

/**
 * @Title: TaskController.java
 * @Description:  代办事项信息
 * @Author: lyn
 * @Date: 2018/4/12 18:06
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@RestController("backendTaskController")
public class TaskController extends AbstractBackendController{

	private final TaskAppService taskAppService;
	
	@Autowired
	public TaskController(TaskAppService taskAppService) {
		this.taskAppService = taskAppService;
	}
	/**
	 * @param dto
	 * @return 获取任务列表
	 **/
	@GetMapping(value="/task/getTask.json",name="事项管理-获取事项")
	public CPViewResultInfo getTask(CPViewResultInfo info,TaskDto dto){
        try{
            info.newSuccess(taskAppService.findTaskStatus(dto));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }
	/**
	 * @return 获取事项详情
	 **/
	@GetMapping(value="/task/getTaskDetail.json",name="事项管理-获取事项详情")
	public CPViewResultInfo getTaskDetail(CPViewResultInfo info,TaskDto dto){
		try{
            info.newSuccess(taskAppService.findByDetail(dto.getId()));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
	}
}