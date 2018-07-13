package com.laman.web.common.component;

import com.laman.biz.user.app.dto.LoginModel;
import com.laman.biz.user.app.dto.UserDto;
import com.laman.biz.user.app.service.UserAppService;
import com.laman.biz.user.app.service.UserPrivateKeyAppService;
import com.laman.fusion.base.CPContext;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.mail.app.service.EmailLogAppService;
import com.laman.fusion.base.mail.model.Message;
import com.laman.fusion.base.mail.service.MailService;
import com.laman.fusion.base.util.ObjectHelper;
import com.laman.fusion.base.util.ObjectProperUtil;
import com.laman.fusion.base.util.RandomImgCodeUtil;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

/**
 * @Title: LoginComponent.java
 * @Description: 登录组件service
 * @Author: Away
 * @Date: 2018/4/11 9:50
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Service
@Transactional
public class LoginComponent {

    private final UserAppService userAppService;

    private final MailService mailService;

    private final UserPrivateKeyAppService userPrivateKeyAppService;

    private final EmailLogAppService emailLogAppService;

    @Autowired
    public LoginComponent(UserAppService userAppService, MailService mailService, UserPrivateKeyAppService userPrivateKeyAppService, EmailLogAppService emailLogAppService) {
        this.userAppService = userAppService;
        this.mailService=mailService;
        this.userPrivateKeyAppService=userPrivateKeyAppService;
        this.emailLogAppService = emailLogAppService;
    }

    /**
     * @Author: Away
     * @Title: register
     * @Description: 用户注册
     * @Param: httpServletRequest 请求
     * @Param: loginModel 登录数据模型
     * @Return: com.laman.biz.user.app.dto.UserDto
     * @Date: 2018/4/12 15:49
     * @Version: 2018/4/12 15:49
     */
    public UserDto register(HttpSession session,LoginModel loginModel) throws Exception {
        //比较验证码
        if(ObjectHelper.isEmpty(session.getAttribute(RandomImgCodeUtil.RANDOMCODEKEY)))throw new BusinessException(ENUM_EXCEPTION.E10027.code,ENUM_EXCEPTION.E10027.msg);
        String random =session.getAttribute(RandomImgCodeUtil.RANDOMCODEKEY).toString();
        if(ObjectHelper.isNotEmpty(random)&&random.equalsIgnoreCase(loginModel.getValidateCode())){
            UserDto returnData=this.userAppService.userRegister(loginModel);
            this.mailService.sendSSLEmil(loginModel.getEmailAddress(),"注册成功","注册成功");
            return returnData;
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10025.code,ENUM_EXCEPTION.E10025.msg);
        }
    }

//    public UserDto changePassWord(LoginModel loginModel) throws Exception{
//
//    }

    /**
     * @Method:  loginOut
     * @Author: Away
     * @Version: v1.0
     * @See: 退出登录
     * @Param:
     * @Return: void
     * @Date: 2018/6/5 10:32
     */
    public void loginOut(Long userId,String platformCode) throws Exception{
        UserDto userDto=new UserDto();
        userDto.setId(userId);
        userDto.setPlatformCode(platformCode);
        this.userPrivateKeyAppService.writeData(userDto);
    }

}
