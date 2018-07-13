package com.laman.biz.task.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
* @Title: DetectiveItemDto
* @Description:  检测项数据传输体
* @Author: Away
* @Date: 2018/7/6 21:07
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class DetectiveItemDto extends BaseDto {

    /**业务编号**/
    private String businessCode;

    /**检测项编号**/
    private String detectiveCode;

    /**检测项名称**/
    private String detectiveName;

    /**方法编号**/
    private String methodCode;

    /**方法名称**/
    private String methodName;

    /**点位图路径**/
    private String positionUrl;

    /**检测人名称**/
    private String inspectorName;

    /**检测人编码**/
    private String inspectorCode;

    /**科室编码**/
    private String departmentCode;

    /**科室名称**/
    private String departmentName;

    /**要求完成时间**/
    private Date demandCompleteTime;

    /**实际完成时间**/
    private Date actualCompleteTime;
 
    /** 业务编号 */
    private List<SamplingItemDto> samplingItemDtoList = new ArrayList<>();
    
}