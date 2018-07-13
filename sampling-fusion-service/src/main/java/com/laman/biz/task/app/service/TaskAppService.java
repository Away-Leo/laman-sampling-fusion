package com.laman.biz.task.app.service;

import com.laman.biz.task.app.dto.TaskDto;
import com.laman.biz.task.domain.service.DetectiveItemDomainService;
import com.laman.biz.task.domain.service.SamplingItemDomainService;
import com.laman.biz.task.domain.service.TaskDomainService;
import com.laman.fusion.base.base.BaseAppService;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title: TaskAppService.java
 * @Description:  代办事项信息服务
 * @Author: lyn
 * @Date: 2018/4/12 18:06
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Service
@Transactional
public class TaskAppService extends BaseAppService<TaskDomainService>{

	private final DetectiveItemDomainService detectiveItemDomainService;
	private final SamplingItemDomainService samplingService;
	@Autowired
	public TaskAppService(DetectiveItemDomainService detectiveItemDomainService,
			SamplingItemDomainService samplingService){
		this.detectiveItemDomainService = detectiveItemDomainService;
		this.samplingService = samplingService;
	}
	
	/**
     * @param dto 
     * @return 获取代办事项 samplingPendingStatus:必填
     **/
    public List<TaskDto> findTaskStatus(TaskDto dto){
    	return this.BDS.findTaskByPendingStatus(dto);
    }
	

    /**
     * @Method:  findByBusinessCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据业务编号查找
     * @Param: businessCode
     * @Return: com.laman.biz.task.app.dto.TaskDto
     * @Date: 2018/7/7 11:35
     */
    public TaskDto findByBusinessCode(String businessCode) throws BusinessException{
        return this.BDS.findBuBusinessCode(businessCode);
    }

    public TaskDto findByDetail(Long id) throws Exception{
    	TaskDto taskDto = this.BDS.findById(id, TaskDto.class);
    	taskDto.setSamplingItemDtoList(
    			samplingService.findByBusinessCode(taskDto.getBusinessCode())
    	);
    	return taskDto;
    }
}
