package com.laman.biz.task.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
* @Title: Task
* @Description:  事项
* @Author: Away
* @Date: 2018/7/6 16:30
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="sampling_task",indexes = {@Index(name = "sampling_business_code_index",  columnList="task_business_code", unique = true)})
@org.hibernate.annotations.Table(appliesTo = "sampling_task",comment = "事项")
@Getter
@Setter
public class Task extends BaseEntity {

    @Column(name="task_business_code",columnDefinition="varchar(36)  comment '业务编号'")
    private String businessCode;

    @Column(name="client_name",columnDefinition="varchar(255)  comment '委托单位'")
    private String clientName;

    @Column(name="client_address",columnDefinition="varchar(255)  comment '委托单位地址'")
    private String clientAddress;

    @Column(name="client_contact_person",columnDefinition="varchar(255)  comment '委托单位联系人'")
    private String clientContactPerson;

    @Column(name="client_contact_phone",columnDefinition="varchar(255)  comment '委托单位联系人电话'")
    private String clientContactPhone;

    @Column(name="sampling_items",columnDefinition="varchar(255)  comment '所有采样项目'")
    private String samplingItems;

    @Column(name="img_urls",columnDefinition="text  comment '多个照片地址，当为多个时用英文逗号隔开'")
    private String imgUrls;

    @Column(name="video_urls",columnDefinition="text  comment '多个视频地址，当为多个时用英文逗号隔开'")
    private String videoUrls;

    @Column(name="sampling_items_num",columnDefinition="int(3)  comment '采样项目数量'")
    private int samplingItemsNum;

    @Column(name="sampling_complete_num",columnDefinition="int(3)  comment '采样项目完成数量'")
    private int samplingCompleteNum;

    @Column(name="sampling_pending_status",columnDefinition="varchar(20)  comment '任务执行状态  no:代办  pending:进行中   end:结束'")
    private String samplingPendingStatus;

}