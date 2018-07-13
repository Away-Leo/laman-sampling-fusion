package com.laman.web.front.controller.user;

import com.laman.biz.user.app.dto.*;
import com.laman.biz.user.app.service.UserAppService;
import com.laman.biz.user.app.service.UserInfoAppService;
import com.laman.fusion.base.dtos.DataTablesPage;
import com.laman.fusion.base.page.DataTablesPageRequest;
import com.laman.web.common.dto.CPViewResultInfo;
import com.laman.web.front.controller.AbstractFrontController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: CwUserController.java
 * @Description:  用户个人信息
 * @Author: Away
 * @Date: 2018/4/12 18:06
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Slf4j
@RestController
public class CwUserController extends AbstractFrontController {

    private final UserAppService userAppService;

    private final UserInfoAppService userInfoAppService;


    @Autowired
    public CwUserController(UserAppService userAppService,UserInfoAppService userInfoAppService) {
        this.userAppService = userAppService;
        this.userInfoAppService=userInfoAppService;
    }

    /**
     * @Method:  getUsersPageWithoutUser
     * @Author: Away
     * @Version: v1.0
     * @See: 获取管理员以及以上用户
     * @Param: dataTablesPage
     * @Param: info
     * @Param: condition
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 17:52
     */
    @PostMapping(value = "/userInfo/getUsersPageWithoutUser.json",name = "用户-获得用户列表(排除一般用户)")
    public CPViewResultInfo getUsersPageWithoutUser(DataTablesPage dataTablesPage, CPViewResultInfo info, UserDto condition){
        try{
            info.newSuccess(this.userAppService.findManageUserPage(new DataTablesPageRequest(dataTablesPage),condition));
            info.setDraw(dataTablesPage.getDraw());
        }catch (Exception e){
            info.newFalse(e);
            log.error("获得用户原始数据列表出错",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 新增或修改用户基本信息
     * @Param: info
     * @Param: userInfoDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 14:24
     */
    @PostMapping(value = "/userInfo/saveOrUpdateUserInfo.json",name = "个人信息-新增或修改用户基本信息")
    public CPViewResultInfo saveOrUpdateUserInfo(CPViewResultInfo info,@RequestBody UserInfoDto userInfoDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserInfo(userInfoDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-新增或修改用户基本信息",e);
        }
        return info;
    }

    /**
     * @Method:  getCurrentUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 获取当前登录用户的基本信息
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 14:34
     */
    @GetMapping(value = "/userInfo/getCurrentUserInfo.json",name = "个人信息-获取当前登录用户的基本信息")
    public CPViewResultInfo getCurrentUserInfo(CPViewResultInfo info){
        try{
            info.newSuccess(this.userAppService.findCurrentUserInfo());
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-获取当前登录用户的基本信息",e);
        }
        return info;
    }

    /**
     * @Method:  getCurrentUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 根据ID获取用户基本信息
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 14:34
     */
    @GetMapping(value = "/userInfo/getUserInfoByUserId.json",name = "个人信息-根据ID获取用户基本信息")
    public CPViewResultInfo getUserInfoByUserId(CPViewResultInfo info,Long userId){
        try{
            info.newSuccess(this.userAppService.findUserInfoByUserId(userId));
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-获取当前登录用户的基本信息",e);
        }
        return info;
    }


    /**
     * @Method:  getPageAllUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 用户管理-获得所有用户分页数据
     * @Param: info
     * @Param: pageRequest
     * @Param: conditions
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/4 17:22
     */
    @PostMapping(value = "/user/getUserInfoPage.json",name = "用户管理-获得所有用户分页数据")
    public CPViewResultInfo getPageAllUserInfo(CPViewResultInfo info,@RequestBody UserInfoDto conditions){
        try{
            info.newSuccess(this.userInfoAppService.findPageByConditions(new PageRequest(conditions.getPage(),conditions.getSize()), conditions));
        }catch (Exception e){
            info.newFalse(e);
            log.error("用户管理-获得所有用户分页数据",e);
        }
        return info;
    }
    /**
     * @param info
     * @param conditions
     * @return 修改密码 
     **/
    @PostMapping(value = "/user/updatePassword.json",name = "用户管理-修改密码数据")
    public CPViewResultInfo updateUserInfo(CPViewResultInfo info,
    		@RequestBody UserInfoDto conditions){
    	try{
    		userAppService.updatePassword(conditions);
            info.newSuccess(true);
        }catch (Exception e){
            info.newFalse(e);
            log.error("用户管理-修改密码",e);
        }
        return info;
    }
    
    /**
     * @return 设置签名
     **/
    @PostMapping(value = "/user/setSignature.json",name = "用户管理-设置签名数据")
    public CPViewResultInfo setSignature(CPViewResultInfo info,
    		@RequestBody UserInfoDto conditions){
    	try{
    		userAppService.setSingture(conditions);
            info.newSuccess(true);
        }catch (Exception e){
            info.newFalse(e);
            log.error("用户管理-设置签名",e);
        }
        return info;
    }
    
}
