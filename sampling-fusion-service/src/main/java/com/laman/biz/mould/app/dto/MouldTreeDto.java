package com.laman.biz.mould.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;


/**
* @Title: MouldTree
* @Description:  模板树
* @Author: Away
* @Date: 2018/7/4 15:54
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class MouldTreeDto extends BaseDto{

    /**上级节点ID**/
    private Long treeParentId;

    /**上级节点名称**/
    private String treeParentName;

    /**节点名称**/
    private String treeName;

    /**节点编码**/
    private String treeCode;

    /**节点顺序**/
    private Integer order;

}
