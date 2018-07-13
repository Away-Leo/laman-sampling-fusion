package com.laman.biz.equipment.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laman.biz.equipment.app.dto.EquipmentDto;
import com.laman.biz.equipment.domain.service.EquipmentDomainService;
import com.laman.fusion.base.base.BaseAppService;

/**
 * @Title: EquipmentController.java
 * @Description:  设备管理信息
 * @Author: lyn
 * @Date: 2018/4/12 18:06
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 **/
@Service
@Transactional
public class EquipmentAppService extends BaseAppService<EquipmentDomainService>{

	public List<EquipmentDto> findList(Long categoryId){
		return this.BDS.findListByCategory(categoryId);
	}

}
