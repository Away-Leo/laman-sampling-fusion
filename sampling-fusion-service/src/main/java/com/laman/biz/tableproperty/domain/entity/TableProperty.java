package com.laman.biz.tableproperty.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* @Title: TableProperty
* @Description:  表格属性
* @Author: Away
* @Date: 2018/7/10 17:02
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="sampling_table_property")
@org.hibernate.annotations.Table(appliesTo = "sampling_table_property",comment = "表格原始属性")
@Getter
@Setter
public class TableProperty extends BaseEntity {

    @Column(name="property_code",unique = true,columnDefinition="varchar(100)  comment '属性编号'")
    private String propertyCode;

    @Column(name="property_name",columnDefinition="varchar(100)  comment '属性名称'")
    private String propertyName;

    @Column(name="property_tree_id",columnDefinition="int(11)  comment '属性树节点ID'")
    private Long propertyTreeId;

    @Column(name="property_tree_name",columnDefinition="varchar(100)  comment '属性树节点名称'")
    private String propertyTreeName;

    @Column(name="property_label_type",columnDefinition="varchar(100)  comment '属性标签类型'")
    private String propertyLabelType;

    @Column(name="property_html",columnDefinition="varchar(500)  comment '属性值转换为html显示'")
    private String propertyHtml;


}