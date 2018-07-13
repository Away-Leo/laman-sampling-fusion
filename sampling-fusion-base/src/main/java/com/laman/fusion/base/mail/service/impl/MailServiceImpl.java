package com.laman.fusion.base.mail.service.impl;

import com.laman.fusion.base.mail.service.MailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.security.Security;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
* @Title: MailserviceImpl
* @Description:  邮件发送实现类
* @Author: Away
* @Date: 2018/5/28 18:20
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class MailServiceImpl implements MailService {
    //邮件的发送者
    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.password}")
    private String pass;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Value("${spring.mail.host}")
    private String host;
    //注入MailSender
    private final JavaMailSender mailSender;

    //发送邮件的模板引擎
    private final FreeMarkerConfigurer configurer;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender, FreeMarkerConfigurer configurer) {
        this.mailSender = mailSender;
        this.configurer = configurer;
    }

    /**
     * @param params       发送邮件的主题对象 object
     * @param title        邮件标题
     * @param templateName 模板名称
     */
    @Override
    public void sendMessageMail(Object params,String sendTo, String title, String templateName) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);//发送者
            helper.setTo(InternetAddress.parse(sendTo));//发送给谁
            helper.setSubject("【" + title + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "】");//邮件标题

            Map<String, Object> model = new HashMap<>();
            model.put("params", params);
            try {
                Template template = configurer.getConfiguration().getTemplate(templateName);
                try {
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

                    helper.setText(text, true);
                    mailSender.send(mimeMessage);
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用加密的方式,利用465端口进行传输邮件,开启ssl
     * @param to    为收件人邮箱
     * @param message    发送的消息
     */
    @Override
    public void sendSSLEmil(String to,String title, String message) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            //设置邮件会话参数
            Properties props = new Properties();
            //邮箱的发送服务器地址
            props.setProperty("mail.smtp.host", host);
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            //邮箱发送服务器端口,这里设置为465端口
            props.setProperty("mail.smtp.port", port);
            props.setProperty("mail.smtp.socketFactory.port", port);
            props.put("mail.smtp.auth", "true");
            final String username = from;
            final String password = pass;
            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            //通过会话,得到一个邮件,用于发送
            MimeMessage msg = new MimeMessage(session);
            //设置发件人
            msg.setFrom(new InternetAddress(from));
            //设置收件人,to为收件人,cc为抄送,bcc为密送
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(to, false));
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to, false));
            msg.setSubject(title);
            Map<String,Object> param=new HashMap<>();
            param.put("user",to);
            Template template = configurer.getConfiguration().getTemplate("message.ftl");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, param);
            msg.setContent(text,"text/html;charset=utf-8");
            //设置发送的日期
            msg.setSentDate(new Date());

            //调用Transport的send方法去发送邮件
            Transport.send(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
