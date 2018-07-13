package com.laman.biz.equipment.app.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.laman.biz.equipment.app.dto.EquipmentCategoryDto;
import com.laman.biz.equipment.domain.service.EquipmentCategoryDomainService;
import com.laman.fusion.base.base.BaseAppService;

/**
* @Title: SysUrlsDomainService.java
* @Description:  设备分类服务
* @author lyn
* @Date: 2018/2/7 18:26
* @Copyright: 重庆拉曼科技有限公司
* @version V1.0
*/
@Service
@Transactional
public class EquipmentCategoryAppService extends BaseAppService<EquipmentCategoryDomainService>{
     
     /**
     * @return 获取设备列表
     **/
    public List<EquipmentCategoryDto> findCategoryList(){
    	 return this.BDS.findAllList(EquipmentCategoryDto.class);
    }
    /**
     * @throws Exception
     * @return 获取分类中的无子分类的元素节点
     **/
    public List<EquipmentCategoryDto> findCategoryByZid() throws Exception{
		return this.BDS.findCategoryByZid();
    }
}