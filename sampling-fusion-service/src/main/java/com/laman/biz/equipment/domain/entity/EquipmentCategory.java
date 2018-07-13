package com.laman.biz.equipment.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
* @Title: Equipment.java
* @Description: 仪器分类
* @author lyn
* @date 2018/7/5 11:34
* @copyright 重庆拉曼科技有限公司
* @version V1.0
*/
@Entity
@Table(name="sampling_equipment_category")
@org.hibernate.annotations.Table(appliesTo="sampling_equipment_category",comment="仪器设备管理，仪器分类")
@Getter
@Setter
public class EquipmentCategory extends BaseEntity{

	private static final long serialVersionUID = -5732690249401750999L;
	@Column(name="name",columnDefinition="varchar(255) null comment '分类名称'")
	private String name;
	@Column(name="code",columnDefinition="varchar(255) null comment '分类编码'")
	private String code;
	@Column(name="pid",columnDefinition="int(11) COMMENT '父类id'")
	private Long pid;
}
