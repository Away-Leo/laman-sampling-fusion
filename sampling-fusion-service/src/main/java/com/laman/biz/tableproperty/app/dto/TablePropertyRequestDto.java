package com.laman.biz.tableproperty.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
* @Title: TablePropertyRequestDto
* @Description:  表格属性请求参数
* @Author: Away
* @Date: 2018/7/11 16:03
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class TablePropertyRequestDto extends BaseDto {

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

    /**当属性为下拉框、单选框、多选框时里面的选项**/
    private List<TablePropertyOptionDto> options;

}