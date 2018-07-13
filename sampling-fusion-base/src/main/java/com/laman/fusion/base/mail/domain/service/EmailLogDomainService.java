package com.laman.fusion.base.mail.domain.service;

import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.mail.app.dto.EmailLogDto;
import com.laman.fusion.base.mail.domain.entity.EmailLog;
import com.laman.fusion.base.mail.domain.repository.EmailLogRepository;
import org.springframework.stereotype.Service;

/**
* @Title: EmailLogDomainService
* @Description:  邮件日志数据服务
* @Author: Away
* @Date: 2018/6/13 16:34
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class EmailLogDomainService extends BaseDomainService<EmailLogRepository,EmailLog,EmailLogDto>{


}
