package com.laman.biz.task.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;


/**
* @Title: SamplingItem
* @Description:  样品数据传输体
* @Author: Away
* @Date: 2018/7/6 19:23
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class SamplingItemDto extends BaseDto {

    /**业务编号**/
    private String businessCode;

    /**检测项编号**/
    private String detectiveCode;

    /**样品编号**/
    private String samplingCode;

    /**样品名称**/
    private String samplingName;

    /**样品类别**/
    private String samplingProduceType;

    /**样品计量单位**/
    private String dataUnit;

    /**采样点编号**/
    private String samplingPositionCode;

    /**采样点名称**/
    private String samplingPositionName;

    /** 样品备注 */
    private String remark;

    /** 采样项目 */
    private String detectiveName;

    /** 样品状态 */
    private String samplingStatus;

    /**所属模板树节点ID（只是用于传递数据，相应域对象中无此字段）**/
    private Long mouldBelongId;
}