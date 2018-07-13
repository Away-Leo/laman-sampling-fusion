package com.laman.biz.equipment.domain.repository;

import java.util.List;
import com.laman.biz.equipment.domain.entity.Equipment;
import com.laman.fusion.base.base.BaseRepository;

/**
* @Title: SysUrlsRepository.java
* @Description: 设备操作库
* @author lyn
* @date 2018/2/7 18:25
* @copyright 重庆拉曼科技有限公司
* @version V1.0
*/
public interface EquipmentRepository extends BaseRepository<Equipment,Long>{
	
	List<Equipment> findByRquipementCategoryId(Long categoryId);
}