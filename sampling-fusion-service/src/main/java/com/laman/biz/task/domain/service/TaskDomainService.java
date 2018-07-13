package com.laman.biz.task.domain.service;

import com.laman.biz.task.app.dto.TaskDto;
import com.laman.biz.task.domain.entity.Task;
import com.laman.biz.task.domain.repository.TaskRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: TaskDomainService
* @Description:  事项数据服务
* @Author: lyn
* @Date: 2018/7/6 20:19
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class TaskDomainService extends BaseDomainService<TaskRepository,Task,TaskDto>{

	 /**
	 * @param dto
	 * @return 查询事项列表
	 **/
	public List<TaskDto> findTaskByPendingStatus(TaskDto dto){
    	 return toDtoList(this.CT.findBySamplingPendingStatus(dto.getSamplingPendingStatus()),TaskDto.class);
	}
	
    /**
     * @Method:  findBuBusinessCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据业务单号查找
     * @Param: businessCode
     * @Return: com.laman.biz.task.app.dto.TaskDto
     * @Date: 2018/7/7 11:31
     */
    public TaskDto findBuBusinessCode(String businessCode) throws BusinessException{
        if(ObjectHelper.isNotEmpty(businessCode)){
            return toDto(this.CT.findByBusinessCode(businessCode),TaskDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }
}
