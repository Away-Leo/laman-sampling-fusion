package com.laman.biz.dept.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptDto extends BaseDto{

	/** 部门id */
	private Long id;
	/** 部门名称 */
	private String name;
	/** 部门码 */
	private String code;
	/** 部门父id */
	private Long pid;
	/** 部门子集合 */
	private List<DeptDto> dto = new ArrayList<>();
}