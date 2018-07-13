package com.laman.biz.task.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;


/**
* @Title: SamplingItem
* @Description:  样品
* @Author: Away
* @Date: 2018/7/6 19:23
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="sampling_item",indexes = {@Index(name = "business_code_index",  columnList="business_code")})
@org.hibernate.annotations.Table(appliesTo = "sampling_item",comment = "样品")
@Getter
@Setter
public class SamplingItem extends BaseEntity {

    @Column(name="business_code",columnDefinition="varchar(36)  comment '业务编号'")
    private String businessCode;

    @Column(name="detective_name",columnDefinition="varchar(50) comment '检查项目名称'")
    private String detectiveName;
    
    @Column(name="detective_code",columnDefinition="varchar(36)  comment '检测项编号'")
    private String detectiveCode;

    @Column(name="sampling_code",columnDefinition="varchar(36)  comment '样品编号'")
    private String samplingCode;

    @Column(name="sampling_name",columnDefinition="varchar(36)  comment '样品名称'")
    private String samplingName;

    @Column(name="sampling_produce_type",columnDefinition="varchar(255)  comment '样品类别'")
    private String samplingProduceType;

    @Column(name="data_unit",columnDefinition="varchar(255)  comment '样品计量单位'")
    private String dataUnit;

    @Column(name="sampling_position_code",columnDefinition="varchar(255)  comment '采样点编号'")
    private String samplingPositionCode;

    @Column(name="sampling_position_name",columnDefinition="varchar(255)  comment '采样点名称'")
    private String samplingPositionName;
    
    @Column(name="sampling_status",columnDefinition="varchar(50) comment '采样状态'")
    private String samplingStatus;
    
    @Column(name="remark",columnDefinition="varchar(255) comment '采样备注'")
    private String remark;
}