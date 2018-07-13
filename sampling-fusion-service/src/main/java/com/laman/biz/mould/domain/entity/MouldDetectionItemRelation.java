package com.laman.biz.mould.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* @Title: MouldItemRelation
* @Description:  模板与检测项关系
* @Author: Away
* @Date: 2018/6/21 18:05
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="sampling_mould_detection_relation")
@org.hibernate.annotations.Table(appliesTo = "sampling_mould_detection_relation",comment = "模板与检测项关系")
@Getter
@Setter
public class MouldDetectionItemRelation extends BaseEntity {

    @Column(name="mould_tree_id",columnDefinition="int(11)  comment '所属模板树节点ID'")
    private Long  mouldTreeId;

    @Column(name="detective_item_code",columnDefinition="varchar(255)  comment '检测项编号'")
    private String detectiveItemCode;

    @Column(name="mould_name",columnDefinition="varchar(255)  comment '模板名称'")
    private String mouldName;

    @Column(name="detective_item_name",columnDefinition="varchar(255)  comment '检测项名称'")
    private String detectiveItemName;



}