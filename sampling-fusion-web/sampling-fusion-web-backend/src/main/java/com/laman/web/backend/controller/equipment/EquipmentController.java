package com.laman.web.backend.controller.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.laman.biz.equipment.app.dto.EquipmentDto;
import com.laman.biz.equipment.app.service.EquipmentAppService;
import com.laman.biz.equipment.domain.entity.Equipment;
import com.laman.web.backend.controller.AbstractBackendController;
import com.laman.web.common.dto.CPViewResultInfo;

/**
 * @Title: EquipmentController.java
 * @Description:  设备管理信息
 * @Author: lyn
 * @Date: 2018/4/12 18:06
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 **/
@RestController
public class EquipmentController extends AbstractBackendController{

	private final EquipmentAppService equipmentAppService;
	
	@Autowired
	public EquipmentController(EquipmentAppService equipmentAppService) {
		this.equipmentAppService = equipmentAppService;
	}
	
	/**
	 * @param category_id 仪器分类id
	 * @return 根据分类id查询仪器列表
	 **/
	@GetMapping(value="/equipment/getEquipment.json",name="仪器管理-获得仪器列表")
    public CPViewResultInfo getEquipment(CPViewResultInfo info,Long id){
        try{
            info.newSuccess(equipmentAppService.findList(id));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }
	/**
	 * @param id 仪器id
	 * @return 根据分类id添加仪器
	 **/
	@PostMapping(value="/equipment/addOrEditEquipment.json",name="仪器管理-添加编辑仪器")
	public CPViewResultInfo addEquipment(CPViewResultInfo info,@RequestBody EquipmentDto equipment){
		 try{
	         info.newSuccess(equipmentAppService.saveOrUpdateData(equipment, Equipment.class));
	     }catch (Exception e){
	         info.newFalse(e);
	     }
	     return info;
	}
	
	
	/**
	 * @param id 
	 * @return 根据id删除设备信息
	 **/
	@PostMapping(value="/equipment/deleteEquipment.json",name="仪器管理-删除仪器")
	public CPViewResultInfo deleteEquipment(CPViewResultInfo info,@RequestBody EquipmentDto equipment){
		 try{
			 equipmentAppService.physicalDelete(equipment.getId());
	         info.newSuccess(true);
	     }catch (Exception e){
	         info.newFalse(e);
	     }
	     return info;
	}
	
}
