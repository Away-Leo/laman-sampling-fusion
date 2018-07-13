package com.laman.web.backend.controller.dept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.laman.biz.dept.app.dto.DeptDto;
import com.laman.biz.dept.app.service.DeptAppService;
import com.laman.biz.dept.domain.entity.Dept;
import com.laman.web.backend.controller.AbstractBackendController;
import com.laman.web.common.dto.CPViewResultInfo;

/**
 * @Title: DeptController.java
 * @Description: 部门管理
 * @Author: lyn
 * @Date: 2018/4/11 9:50
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@RestController
public class DeptController extends AbstractBackendController{

	private final DeptAppService deptAppService;
	
	@Autowired
	public DeptController(DeptAppService deptAppService){
		this.deptAppService = deptAppService;
	}
	/**
	 * @param info
	 * @param dept
	 * @return 获取部门树
	 **/
	@GetMapping(value="/dept/treeDept.json",name="部门管理-获取部门树")
    public CPViewResultInfo treeDept(CPViewResultInfo info){
        try{
            info.newSuccess(deptAppService.findAllList(DeptDto.class));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }
	/**
	 * @param info
	 * @param dept
	 * @return 新增部门
	 **/
	@PostMapping(value="/dept/addOrEditDept.json",name="部门管理-新增编辑部门")
    public CPViewResultInfo addDept(CPViewResultInfo info,
    		@RequestBody DeptDto deptDto){
        try{
            info.newSuccess(deptAppService.saveOrUpdateData(deptDto,Dept.class));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }
	
	/**
	 * @param info
	 * @param dept
	 * @return 删除部门
	 **/
	@GetMapping(value="/dept/deleteDept.json",name="部门管理-删除部门")
    public CPViewResultInfo deleteDept(CPViewResultInfo info,DeptDto deptDto){
        try{
        	deptAppService.physicalDelete(deptDto.getId());
            info.newSuccess(true);
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }
	
	/**
	 * @param name 部门名称
	 * @param pid 父id
	 * @return 验证同一节点下部门名字是否相同
	 **/
	@GetMapping(value="/dept/validDept.json",name="部门管理-验证部门")
	public CPViewResultInfo validateDept(CPViewResultInfo info,
	    	@RequestParam(value="name",required=true) String name,
		@RequestParam(value="pid",required=true) Long pid){
		try{
            info.newSuccess(deptAppService.validateDeptDto(name,pid));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
	}
	
}