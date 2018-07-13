package com.laman.biz.tableproperty.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;


/**
* @Title: TablePropertyDto
* @Description:  表格属性dto
* @Author: Away
* @Date: 2018/7/11 10:17
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class TablePropertyDto extends BaseDto {

    /**属性编号**/
    private String propertyCode;

    /**属性名称**/
    private String propertyName;

    /**属性树节点ID**/
    private Long propertyTreeId;

    /**属性树节点名称**/
    private String propertyTreeName;

    /**属性标签类型**/
    private String propertyLabelType;

    /**属性值转换为html显示**/
    private String propertyHtml;


}