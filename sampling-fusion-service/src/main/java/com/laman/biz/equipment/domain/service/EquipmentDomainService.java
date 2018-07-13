package com.laman.biz.equipment.domain.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.laman.biz.equipment.app.dto.EquipmentDto;
import com.laman.biz.equipment.domain.entity.Equipment;
import com.laman.biz.equipment.domain.repository.EquipmentRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;

/**
* @Title: SysUrlsRepository.java
* @Description: 设备服务
* @author lyn
* @date 2018/2/7 18:25
* @copyright 重庆拉曼科技有限公司
* @version V1.0
*/
@Service
public class EquipmentDomainService extends BaseDomainService<
			EquipmentRepository,Equipment,EquipmentDto>{

	public List<EquipmentDto> findListByCategory(Long categoryId) throws BusinessException{
		if(ObjectHelper.isNotEmpty(categoryId)){
			return toDtoList(this.CT.findByRquipementCategoryId(categoryId),EquipmentDto.class);
		}else if(categoryId.intValue() == 0){//等于0表示查询所有
			return toDtoList(this.CT.findAll(),EquipmentDto.class);
		}else{
			throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
		}
	}
	
	
	
}
