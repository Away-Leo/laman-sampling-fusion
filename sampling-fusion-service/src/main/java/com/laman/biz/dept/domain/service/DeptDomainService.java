package com.laman.biz.dept.domain.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.laman.biz.dept.app.dto.DeptDto;
import com.laman.biz.dept.domain.entity.Dept;
import com.laman.biz.dept.domain.repository.DeptRepository;
import com.laman.fusion.base.base.BaseDomainService;

/**
* @Title: DeptDomainService.java
* @Description: 部门服务
* @author Away
* @Date: 2018/2/7 18:26
* @Copyright: 重庆拉曼科技有限公司
* @version V1.0
**/
@Service
@Transactional
public class DeptDomainService extends BaseDomainService<DeptRepository,Dept,DeptDto>{
	
	/**
	 * @return 根据部门名称和父id查询部门
	 **/
	public List<DeptDto> findByNameAndPid(String name,Long pid){
		return toDtoList(this.CT.findByNameAndPid(name, pid),DeptDto.class);
	}
	
}