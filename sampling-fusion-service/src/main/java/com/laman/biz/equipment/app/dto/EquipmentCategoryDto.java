package com.laman.biz.equipment.app.dto;

import java.util.List;
import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentCategoryDto extends BaseDto{

	private Long id;
	private String name;
	private String code;
	private Long pid;
	private List<EquipmentCategoryDto> dto;
}
