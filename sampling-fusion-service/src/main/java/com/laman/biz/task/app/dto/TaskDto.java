package com.laman.biz.task.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
* @Title: Task
* @Description:  事项数据传输体
* @Author: Away
* @Date: 2018/7/6 16:30
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class TaskDto extends BaseDto {

    /**业务编号**/
    private String businessCode;

    /**委托单位**/
    private String clientName;

    /**委托单位地址**/
    private String clientAddress;

    /**委托单位联系人**/
    private String clientContactPerson;

    /**委托单位联系人电话**/
    private String clientContactPhone;

    /**所有采样项目**/
    private String samplingItems;

    /**多个照片地址，当为多个时用英文逗号隔开**/
    private String imgUrls;

    /**多个视频地址，当为多个时用英文逗号隔开**/
    private String videoUrls;

    /**采样项目数量**/
    private int samplingItemsNum;

    /**采样项目完成数量**/
    private int samplingCompleteNum;

    /**任务执行状态  no:代办  pending:进行中   end:结束**/
    private String samplingPendingStatus;
    /**检查项列表*/
    List<DetectiveItemDto> detectiveItemDtoList = new ArrayList<>();
    /** 样品列表 */
    List<SamplingItemDto> samplingItemDtoList = new ArrayList<>();
}