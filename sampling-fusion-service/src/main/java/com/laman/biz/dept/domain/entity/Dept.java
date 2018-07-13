package com.laman.biz.dept.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="sampling_dept")
@org.hibernate.annotations.Table(appliesTo="sampling_dept",comment="部门表，用于存储部门信息")
@Getter
@Setter
public class Dept extends BaseEntity{

	@Column(name="name",columnDefinition="varchar(20) null comment '部门名称'")
	private String name;
	@Column(name="code",columnDefinition="varchar(20) null comment '部门码'")
	private String code;
	@Column(name="pid",columnDefinition="int(11) null comment '部门父id(如果0一级部门)'")
	private Long pid;

}