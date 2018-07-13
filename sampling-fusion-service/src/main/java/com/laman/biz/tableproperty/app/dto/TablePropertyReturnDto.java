package com.laman.biz.tableproperty.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


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
public class TablePropertyReturnDto extends BaseDto {

    /**属性大类编号**/
    private Long propertyTreeId;

    /**属性大类名称**/
    private String propertyTreeName;

    /**子属性**/
    private List<TablePropertyDto> children;

}