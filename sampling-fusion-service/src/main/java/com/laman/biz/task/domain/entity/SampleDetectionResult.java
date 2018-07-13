package com.laman.biz.task.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
* @Title: SampleDetectionResult
* @Description:  样品检测结果
* @Author: Away
* @Date: 2018/7/9 10:09
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="sampling_detection_result",indexes = {@Index(name = "business_code_index",  columnList="business_code"),
        @Index(name = "detective_code_index",  columnList="detective_code"),
        @Index(name = "sampling_code_index",  columnList="sampling_code"),
        @Index(name = "attribute_code_index",  columnList="attribute_code")},
        uniqueConstraints = {@UniqueConstraint(columnNames={"business_code", "detective_code","sampling_code","attribute_code"})})
@org.hibernate.annotations.Table(appliesTo = "sampling_detection_result",comment = "样品检测结果")
@Getter
@Setter
public class SampleDetectionResult extends BaseEntity {

    @Column(name="business_code",columnDefinition="varchar(36)  comment '业务编号'")
    private String businessCode;

    @Column(name="detective_code",columnDefinition="varchar(36)  comment '检测项编号'")
    private String detectiveCode;

    @Column(name="sampling_code",columnDefinition="varchar(36)  comment '样品编号'")
    private String samplingCode;

    @Column(name="attribute_code",columnDefinition="varchar(36)  comment '属性编号'")
    private String attributeCode;

    @Column(name="attribute_name",columnDefinition="varchar(100)  comment '属性名称'")
    private String attributeName;

    @Column(name="attribute_value",columnDefinition="varchar(255)  comment '属性名称'")
    private String attributeValue;



}