package com.laman.fusion.base.mail.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @Title: EmailLog.java
 * @Description: 电子邮件日志
 * @Author: Away
 * @Date: 2018/4/23 16:48
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
public class EmailLogDto extends BaseDto{

    /**电子邮件内容**/
    private String emailContent;

    /**所属组件（有可能是 表名、功能名、等）名称**/
    private String emailComponents;

    /**所属组件内ID**/
    private String emailComponentsId;

    /**失效时间**/
    private Long emailExpiry;

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public String getEmailComponents() {
        return emailComponents;
    }

    public void setEmailComponents(String emailComponents) {
        this.emailComponents = emailComponents;
    }

    public String getEmailComponentsId() {
        return emailComponentsId;
    }

    public void setEmailComponentsId(String emailComponentsId) {
        this.emailComponentsId = emailComponentsId;
    }

    public Long getEmailExpiry() {
        return emailExpiry;
    }

    public void setEmailExpiry(Long emailExpiry) {
        this.emailExpiry = emailExpiry;
    }
}
