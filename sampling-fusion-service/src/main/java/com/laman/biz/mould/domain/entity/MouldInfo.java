package com.laman.biz.mould.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
* @Title: MouldInfo
* @Description:  模板信息
* @Author: Away
* @Date: 2018/6/21 18:05
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="sampling_mould_info",uniqueConstraints = {@UniqueConstraint(columnNames={"mould_code"})})
@org.hibernate.annotations.Table(appliesTo = "sampling_mould_info",comment = "模板信息表")
@Getter
@Setter
public class MouldInfo extends BaseEntity {

    @Column(name="mould_code",columnDefinition="varchar(36)  comment '模板唯一编码'")
    private String mouldCode;

    @Column(name="mould_name",columnDefinition="varchar(255)  comment '模板名称'")
    private String mouldName;

    @Column(name="mould_belong",columnDefinition="varchar(255)  comment '模板所属项目'")
    private String mouldBelong;

    @Column(name="mould_parent_id",columnDefinition="int(11)  comment '所属父节点ID'")
    private Long mouldParentId;

    @Column(name="mould_parent_name",columnDefinition="varchar(255)  comment '所属父节点名称'")
    private String mouldParentName;

    @Column(name="mould_belong_id",unique = true,columnDefinition="int(11)  comment '模板所属项目ID'")
    private Long mouldBelongId;

    @Column(name="mould_data",columnDefinition="text comment '模板内容数据'")
    private String mouldData;

    @Column(name="mould_merge_cell",columnDefinition="varchar(2000) comment '模板合并单元格数据'")
    private String mouldMergeCell;

    @Column(name="mould_classes",columnDefinition="text comment '模板样式数据'")
    private String mouldClasses;

    @Column(name="mould_height_with",columnDefinition="varchar(2000) comment '模板行高列宽数据'")
    private String mouldHeightWith;

}