package com.laman.biz.equipment.app.dto;

import java.util.Date;
import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentDto extends BaseDto{

	private static final long serialVersionUID = -2637229341662121114L;

	/** 仪器编号 */
	private String no;
	/** 仪器名称 */
	private String name;
	/** 型号 */
	private String modle;
	/** 生产厂家 */
	private String manufacturer;
	/** 出厂编号 */
	private String number;
	/** 部门名称 */
	private String deptName;
	/** 下次检查日期1 */
	private Date inspectionDate1;
	/** 下次检查日期2 */
	private Date inspectionDate2;
	/** 仪器状态 */
	private String status;
	/** 检查周期 */
	private Integer inspectionCycle;
	/** 购置日期 */
	private Date purchaseDate;
	/** 上次核查日期 */
	private Date lastInspectionDate;
	/** 测量范围 */
	private Integer measuringRange;
	/** 精度等级 */
	private Integer level;
	/** 核查周期 */
	private Integer verificationCycle;
	/** 下次核查日期 */
	private Date nextVerificationDate;
	/** 核定单位 */
	private String approvedUnit;
	/** 保修年限 */
	private Integer warrantyPeriod;
	/** 固定资产编号 */
	private String assetsNumber;
	/** 供应商 */
	private String supplier;
	/** 规格 */
	private String specifications;
	/** 备注 */
	private String remarks;
	/** 设备分类id */
	private Long rquipementCategoryId;
	/** 图片地址 */
	private String imgsUrl;
}
