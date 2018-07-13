package com.laman.web.front.controller.task;

import com.laman.biz.task.app.service.TaskAppService;
import com.laman.web.common.dto.CPViewResultInfo;
import com.laman.web.front.controller.AbstractFrontController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Title: TaskController
* @Description:  事项控制器
* @Author: Away
* @Date: 2018/7/7 11:37
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@RestController
public class TaskController extends AbstractFrontController{

    private final TaskAppService taskAppService;

    @Autowired
    public TaskController(TaskAppService taskAppService) {
        this.taskAppService = taskAppService;
    }

    /**
     * @Method:  findTaskByBusinessCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照业务编号查找事项
     * @Param: info
     * @Param: businessCode
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/7 11:39
     */
    @GetMapping(value = "/task/findTaskByBusinessCode.json",name = "事项-按照业务编号查找事项")
    public CPViewResultInfo findTaskByBusinessCode(CPViewResultInfo info,String businessCode){
        try{
            info.newSuccess(taskAppService.findByBusinessCode(businessCode));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }


}
