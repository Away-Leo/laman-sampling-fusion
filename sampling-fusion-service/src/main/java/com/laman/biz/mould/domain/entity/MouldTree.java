package com.laman.biz.mould.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* @Title: MouldTree
* @Description:  模板树
* @Author: Away
* @Date: 2018/7/4 15:54
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="sampling_mould_tree")
@org.hibernate.annotations.Table(appliesTo = "sampling_mould_tree",comment = "模板配置树形菜单")
@Getter
@Setter
public class MouldTree extends BaseEntity{

    @Column(name="tree_parent_id",columnDefinition="int(11) comment '上级节点ID'")
    private Long treeParentId;

    @Column(name="tree_parent_name",columnDefinition="varchar(100) comment '上级节点名称'")
    private String treeParentName;

    @Column(name="tree_name",columnDefinition="varchar(100)  comment '节点名称'")
    private String treeName;

    @Column(name="tree_code",unique = true,columnDefinition="varchar(100) comment '节点编码'")
    private String treeCode;

    @Column(name="tree_order",columnDefinition="int(11) comment '节点顺序'")
    private Integer order;

}
