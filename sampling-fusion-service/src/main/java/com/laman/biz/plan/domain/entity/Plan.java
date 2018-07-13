package com.laman.biz.plan.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* @Title: Plan
* @Description: 计划
* @Author: Away
* @Date: 2018/6/26 11:25
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="sampling_sampling_plan")
@org.hibernate.annotations.Table(appliesTo = "sampling_sampling_plan",comment = "采样计划")
@Getter
@Setter
public class Plan extends BaseEntity {

    @Column(name="union_name",columnDefinition="varchar(100)  comment '单位名称'")
    private String unionName;

    @Column(name="union_address",columnDefinition="varchar(255)  comment '单位地址'")
    private String unionAddress;

    @Column(name="union_contract_person",columnDefinition="varchar(255)  comment '单位联系人'")
    private String unionContractPerson;

    @Column(name="union_contract_phone",columnDefinition="varchar(20) comment '单位联系人电话'")
    private String unionContractPhone;

    @Column(name="sample_type_code",columnDefinition="varchar(36) comment '样品类别编号'")
    private String sampleTypeCode;

    @Column(name="sample_type_name",columnDefinition="varchar(36) comment '样品类别名称'")
    private String sampleTypeName;

    @Column(name="sample_position_code",columnDefinition="varchar(36) comment '采样点编号编号'")
    private String samplePositionCode;

    @Column(name="sample_position_name",columnDefinition="varchar(36) comment '采样点编号名称'")
    private String samplePositionName;

    @Column(name="sample_num",columnDefinition="int(10) comment '样品个数'")
    private String sampleNum;

    @Column(name="detected_union_name",columnDefinition="varchar(100) comment '受检单位名称'")
    private String detectedUnionName;

    @Column(name="detected_union_address",columnDefinition="varchar(100) comment '受检单位地址'")
    private String detectedUnionAddress;

    @Column(name="detected_contact_person",columnDefinition="varchar(100) comment '受检单位联系人'")
    private String detectedContactPerson;

    @Column(name="detected_contact_phone",columnDefinition="varchar(20) comment '受检单位联系电话'")
    private String detectedContactPhone;

    @Column(name="mould_classes",columnDefinition="text comment '模板样式数据'")
    private String mouldClasses;

    @Column(name="mould_height_with",columnDefinition="varchar(2000) comment '模板行高列宽数据'")
    private String mouldHeightWith;

}