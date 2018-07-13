package com.laman.biz.task.domain.service;

import com.laman.biz.task.app.dto.SamplingItemDto;
import com.laman.biz.task.domain.entity.SamplingItem;
import com.laman.biz.task.domain.repository.SamplingItemRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: TaskDomainService
* @Description:  事项数据服务
* @Author: Away
* @Date: 2018/7/6 20:19
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class SamplingItemDomainService extends BaseDomainService<SamplingItemRepository,SamplingItem,SamplingItemDto>{


    /**
     * @Method:  findByBusinessCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据业务编号查找样品列表
     * @Param: businessCode
     * @Return: java.util.List<com.laman.biz.task.app.dto.SamplingItemDto>
     * @Date: 2018/7/7 11:56
     */
    public List<SamplingItemDto> findByBusinessCode(String businessCode) throws BusinessException{
        if(ObjectHelper.isNotEmpty(businessCode)){
            return toDtoList(this.CT.findByBusinessCode(businessCode),SamplingItemDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @param businessCode
     * @param detectiveCode
     * @return 根据检查项和业务编号查询列表
     * @throws BusinessException
     **/
    public List<SamplingItemDto> findByBusinessCode(String businessCode,
    		String detectiveCode) throws BusinessException{
    	 if(ObjectHelper.isNotEmpty(businessCode) ||
    			 ObjectHelper.isNotEmpty(detectiveCode)){
    		 SamplingItem samplingItem = new SamplingItem();
    		 samplingItem.setBusinessCode(businessCode);
    		 samplingItem.setDetectiveCode(detectiveCode);
    		 Example<SamplingItem> example = Example.of(samplingItem);
    		 return toDtoList(this.CT.findAll(example),SamplingItemDto.class);
         }else{
             throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
         }
    }

    /**
     * @Method:  findByIdIn
     * @Author: Away
     * @Version: v1.0
     * @See: 根据多个ID查询样品集合
     * @Param: ids
     * @Return: java.util.List<com.laman.biz.task.app.dto.SamplingItemDto>
     * @Date: 2018/7/9 9:43
     */
    public List<SamplingItemDto> findByIdIn(List<Long> ids) throws BusinessException{
        if(ObjectHelper.isNotEmpty(ids)){
            return toDtoList(this.CT.findByIdIn(ids),SamplingItemDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

}
