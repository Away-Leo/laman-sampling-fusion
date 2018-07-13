package com.laman.biz.task.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
* @Title: SampleDetectionResultDto
* @Description:  样品检测结果
* @Author: Away
* @Date: 2018/7/9 10:09
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class SampleDetectionResultDto extends BaseDto {

    /**业务编号**/
    private String businessCode;

    /**检测项编号**/
    private String detectiveCode;

    /**样品编号**/
    private String samplingCode;

    /**属性编号**/
    private String attributeCode;

    /**属性名称**/
    private String attributeName;

    /**属性名称**/
    private String attributeValue;

}