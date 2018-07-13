package com.laman.biz.task.domain.service;

import com.laman.biz.task.app.dto.DetectiveItemDto;
import com.laman.biz.task.app.dto.SamplingItemDto;
import com.laman.biz.task.domain.entity.DetectiveItem;
import com.laman.biz.task.domain.repository.DetectiveItemRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: DetectiveItemDomainService
* @Description:  检测项数据服务
* @Author: Away
* @Date: 2018/7/7 11:07
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class DetectiveItemDomainService extends BaseDomainService<DetectiveItemRepository,DetectiveItem,DetectiveItemDto>{

	private final SamplingItemDomainService samplingService;

	public DetectiveItemDomainService(SamplingItemDomainService
			samplingService) {
		this.samplingService = samplingService;
	}

	/**
	 * @param businessCode
	 * @return 根据业务编码获取列表
	 **/
	public List<DetectiveItemDto> findListBybusiness(String businessCode){
		DetectiveItem dItem = new DetectiveItem();
    	dItem.setBusinessCode(businessCode);
	   	Example<DetectiveItem> dItemExample = Example.of(dItem);
	   	List<DetectiveItemDto> dItemDtoList =
	   			toDtoList(this.CT.findAll(dItemExample),DetectiveItemDto.class);
	   	for(DetectiveItemDto itemDto : dItemDtoList){
	   		List<SamplingItemDto> sDto =
	   	samplingService.findByBusinessCode(businessCode,itemDto.getDetectiveCode());
	   		itemDto.setSamplingItemDtoList(sDto);
	   	}
	   	return dItemDtoList;
	}

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
	public DetectiveItemDto findByBusinessCodeAndDetectiveItemCode(String businessCode,String detectiveItemCode) throws BusinessException {
		if(ObjectHelper.isNotEmpty(businessCode)&&ObjectHelper.isNotEmpty(detectiveItemCode)){
			return toDto(this.CT.findByBusinessCodeAndDetectiveCode(businessCode, detectiveItemCode),DetectiveItemDto.class);
		}else{
			throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
		}
	}
}
