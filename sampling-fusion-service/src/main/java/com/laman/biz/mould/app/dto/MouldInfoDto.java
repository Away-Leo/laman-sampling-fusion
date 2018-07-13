package com.laman.biz.mould.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;


/**
* @Title: MouldInfoDto
* @Description:  模板信息数据传输体
* @Author: Away
* @Date: 2018/6/21 18:05
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class MouldInfoDto extends BaseDto {

    /**模板唯一编码**/
    private String mouldCode;

    /**模板名称**/
    private String mouldName;

    /**模板所属项目**/
    private String mouldBelong;

    /**模板所属项目ID**/
    private Long mouldBelongId;

    /**所属父节点ID**/
    private Long mouldParentId;

    /**所属父节点名称**/
    private String mouldParentName;

    /**模板内容数据**/
    private String mouldData;

    /**模板合并单元格数据**/
    private String mouldMergeCell;

    /**模板样式数据**/
    private String mouldClasses;

    /**模板行高列宽数据**/
    private String mouldHeightWith;

}