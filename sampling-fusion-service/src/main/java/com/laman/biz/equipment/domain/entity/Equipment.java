package com.laman.biz.equipment.domain.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
* @Title: Equipment.java
* @Description: 仪器
* @author lyn
* @date 2018/7/5 11:34
* @copyright 重庆拉曼科技有限公司
* @version V1.0
*/
@Entity
@Table(name="sampling_equipment")
@org.hibernate.annotations.Table(appliesTo="sampling_equipment",comment="仪器设备管理，仪器管理")
@Getter
@Setter
public class Equipment extends BaseEntity{
	
	private static final long serialVersionUID = 4493420169283585644L;
	
	/** 编号 */
	@Column(name="no",columnDefinition="varchar(100) null comment '编号'")
	private String no;
	/** 仪器名称 */
	@Column(name="name",columnDefinition="varchar(100) null comment '仪器名称'")
	private String name;
	/** 型号 */
	@Column(name="modle",columnDefinition="varchar(100) null comment '型号'")
	private String modle;
	/** 生产厂家 */
	@Column(name="manufacturer",columnDefinition="varchar(100) null comment '生产厂家'")
	private String manufacturer;
	/** 出厂编号 */
	@Column(name="number",columnDefinition="varchar(100) null comment '出厂编号'")
	private String number;
	/** 部门名称 */
	@Column(name="dept_name",columnDefinition="varchar(100) null comment '部门名称'")
	private String deptName;
	/** 下次检查日期1 */
	@Column(name="inspection_date1",columnDefinition="datetime COMMENT '下次检查日期1'")
	private Date inspectionDate1;
	/** 下次检查日期2 */
	@Column(name="inspection_date2",columnDefinition="datetime COMMENT '下次检查日期2'")
	private Date inspectionDate2;
	
	@Column(name="status",columnDefinition="varchar(255) default 0 COMMENT '仪器状态'")
	private String status ;
    
    @Column(name="inspection_cycle",columnDefinition="int(4) NULL COMMENT '检查周期'")
	private Integer inspectionCycle;
    
	@Column(name="purchase_date",columnDefinition="datetime COMMENT '购置日期'")
	private Date purchaseDate;
	
	@Column(name="last_inspection_date",columnDefinition="datetime COMMENT '上次核查日期'")
	private Date lastInspectionDate;
	
	@Column(name="measuring_range",columnDefinition="int(4) NULL COMMENT '测量范围'")
	private Integer measuringRange;
	
	@Column(name="level",columnDefinition="int(2) NULL COMMENT '精度等级'")
	private Integer level;
	
	@Column(name="verification_cycle",columnDefinition="int(4) NULL COMMENT '核查周期'")
	private Integer verificationCycle;
	
	@Column(name="next_verification_date",columnDefinition="datetime COMMENT '下次核查日期'")
	private Date nextVerificationDate;
	
	@Column(name="approved_unit",columnDefinition="varchar(100) null comment '核定单位'")
	private String approvedUnit;
	
	@Column(name="warranty_period",columnDefinition="int(2) null comment '保修年限'")
	private Integer warrantyPeriod;
	
	@Column(name="assets_number",columnDefinition="varchar(200) null comment '固定资产编号'")
	private String assetsNumber;
	
	@Column(name="supplier",columnDefinition="varchar(100) null comment '供应商'")
	private String supplier;
	
	@Column(name="specifications",columnDefinition="varchar(20) null comment '规格'")
	private String specifications;
	
	@Column(name="remarks",columnDefinition="varchar(200) null comment '备注'")
	private String remarks;
	
	@Column(name="category_id",columnDefinition="int(11) NULL COMMENT '分类id'")
	private Long rquipementCategoryId;
	
	@Column(name="imgs",columnDefinition="varchar(255) NULL COMMENT '图片地址 多张以,隔开'")
	private String imgsUrl;
}
