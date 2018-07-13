package com.laman.biz.original.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* @Title: DetectionItem
* @Description:  检测项数据字典
* @Author: Away
* @Date: 2018/7/5 16:43
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="sampling_detection_item")
@org.hibernate.annotations.Table(appliesTo = "sampling_detection_item",comment = "检测项数据字典")
@Getter
@Setter
public class DetectionItem extends BaseEntity {

    @Column(name="item_code",columnDefinition="varchar(36)  comment '检测项唯一编码'")
    private String itemCode;

    @Column(name="item_name",columnDefinition="varchar(255)  comment '检测项名称'")
    private String itemName;

    @Column(name="item_type",columnDefinition="varchar(255)  comment '检测项类型'")
    private String itemType;

    @Column(name="state",unique = true,columnDefinition="varchar(1)  comment '状态'")
    private String state;

    @Column(name="letter",columnDefinition="varchar(255) comment '不知'")
    private String letter;

    @Column(name="node",columnDefinition="varchar(255) comment '不知'")
    private String node;

    @Column(name="parent_node",columnDefinition="varchar(255) comment '父节点'")
    private String parentNode;

    @Column(name="item_old",columnDefinition="varchar(255) comment 'itemOld'")
    private String itemOld;

    @Column(name="old_code",columnDefinition="varchar(255) comment 'oldCode'")
    private String oldCode;

    @Column(name="re_code",columnDefinition="varchar(255) comment 're_code'")
    private String reCode;

}