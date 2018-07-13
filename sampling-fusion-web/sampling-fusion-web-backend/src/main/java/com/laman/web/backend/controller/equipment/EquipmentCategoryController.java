package com.laman.web.backend.controller.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.laman.biz.equipment.app.dto.EquipmentCategoryDto;
import com.laman.biz.equipment.app.service.EquipmentCategoryAppService;
import com.laman.biz.equipment.domain.entity.EquipmentCategory;
import com.laman.web.backend.controller.AbstractBackendController;
import com.laman.web.common.dto.CPViewResultInfo;

/**
 * @Title: EquipmentCategoryController.java
 * @Description:  设备分类信息
 * @Author: lyn
 * @Date: 2018/4/12 18:06
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@RestController
public class EquipmentCategoryController extends AbstractBackendController{
      
	private final EquipmentCategoryAppService equipmentCategoryAppService;
	
	@Autowired
	public EquipmentCategoryController(EquipmentCategoryAppService 
			equipmentCategoryAppService){
		this.equipmentCategoryAppService = equipmentCategoryAppService;
	}
	
	/**
	 * @param info
	 * @return 获取分类列表
	 **/
	@GetMapping(value="/equipmentCategory/getCategory.json",name="仪器管理-获得分类列表")
    public CPViewResultInfo equipmentList(CPViewResultInfo info){
        try{
            info.newSuccess(equipmentCategoryAppService.findCategoryList());
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }
	
	/**
	 * @param info
	 * @param dto
	 * @return 新增或者编辑设备
	 **/
	@PostMapping(value="/equipmentCategory/addOrEditCategory.json",name="仪器管理-新增或者编辑设备")
    public CPViewResultInfo addEquipment(CPViewResultInfo info,
    		@RequestBody EquipmentCategoryDto dto){
        try{
            info.newSuccess(equipmentCategoryAppService.saveOrUpdateData(dto,EquipmentCategory.class));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }
	/**
	 * @param info
	 * @param id
	 * @return 删除设备信息
	 **/
	@PostMapping(value="/equipmentCategory/deleteCategory.json",name="仪器管理-删除设备")
	public CPViewResultInfo deleteEquipment(CPViewResultInfo info,
			@RequestBody EquipmentCategoryDto dto){
		try{
			equipmentCategoryAppService.physicalDelete(dto.getId());
            info.newSuccess(true);
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
	}
	
	/**
	 * @param info
	 * @return 获取最底层的子节点元素
	 **/
	@GetMapping(value="/equipmentCategory/findDownCategory.json",name="仪器管理-获取子元素")
	public CPViewResultInfo zidCategory(CPViewResultInfo info){
		try{
            info.newSuccess(equipmentCategoryAppService.findCategoryByZid());
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
	}
	
}