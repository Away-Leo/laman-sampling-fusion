package com.laman.biz.task.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;


/**
* @Title: DetectiveItem
* @Description:  检测项
* @Author: Away
* @Date: 2018/7/6 21:07
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="sampling_detective_item",indexes = {@Index(name = "business_code_index",  columnList="business_code")})
@org.hibernate.annotations.Table(appliesTo = "sampling_detective_item",comment = "样品")
@Getter
@Setter
public class DetectiveItem extends BaseEntity {

    @Column(name="business_code",columnDefinition="varchar(36)  comment '业务编号'")
    private String businessCode;

    @Column(name="detective_item_code",columnDefinition="varchar(36)  comment '检测项编号'")
    private String detectiveCode;

    @Column(name="detective_item_name",columnDefinition="varchar(36)  comment '检测项名称'")
    private String detectiveName;

    @Column(name="method_code",columnDefinition="varchar(255)  comment '方法编号'")
    private String methodCode;

    @Column(name="method_name",columnDefinition="varchar(255)  comment '方法名称'")
    private String methodName;

    @Column(name="position_url",columnDefinition="varchar(255)  comment '点位图路径'")
    private String positionUrl;

    @Column(name="inspector_name",columnDefinition="varchar(255)  comment '检测人名称'")
    private String inspectorName;

    @Column(name="inspector_code",columnDefinition="varchar(255)  comment '检测人编码'")
    private String inspectorCode;

    @Column(name="department_code",columnDefinition="varchar(255)  comment '科室编码'")
    private String departmentCode;

    @Column(name="department_name",columnDefinition="varchar(255)  comment '科室名称'")
    private String departmentName;

    @Column(name="demand_complete_time",columnDefinition="timestamp comment '要求完成时间'")
    private Date demandCompleteTime;

    @Column(name="actual_complete_time",columnDefinition="timestamp comment '实际完成时间'")
    private Date actualCompleteTime;

}