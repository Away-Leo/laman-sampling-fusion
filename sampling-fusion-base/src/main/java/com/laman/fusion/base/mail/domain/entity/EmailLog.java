package com.laman.fusion.base.mail.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @Title: EmailLog.java
 * @Description: 电子邮件日志表
 * @Author: Away
 * @Date: 2018/4/23 16:48
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Entity
@Table(name="ess_email_log")
@org.hibernate.annotations.Table(appliesTo = "ess_email_log",comment = "系统基本组件-电子邮件日志表")
@Getter
@Setter
public class EmailLog extends BaseEntity{

    @Column(name="email_content",columnDefinition="varchar(500) comment '电子邮件内容'")
    private String emailContent;

    @Column(name="email_components",columnDefinition="varchar(100)  comment '所属组件（有可能是 表名、功能名、等）名称'")
    private String emailComponents;

    @Column(name="email_components_id",columnDefinition="varchar(100)  comment '所属组件内ID'")
    private String emailComponentsId;

    @Column(name="email_expiry",columnDefinition="int(11)  comment '失效时间'")
    private Long emailExpiry;

}
