package com.laman.fusion.base.mail.app.service;

import com.laman.fusion.base.base.BaseAppService;
import com.laman.fusion.base.mail.domain.service.EmailLogDomainService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
* @Title: EmialLogAppService
* @Description:  邮件日志应用服务
* @Author: Away
* @Date: 2018/6/13 16:36
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
@Transactional
public class EmailLogAppService extends BaseAppService<EmailLogDomainService>{
}
