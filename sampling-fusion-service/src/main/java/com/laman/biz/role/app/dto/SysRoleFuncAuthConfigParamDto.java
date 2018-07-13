package com.laman.biz.role.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
* @Title: SysRoleFuncAuthConfig
* @Description:  角色功能权限配置
* @Author: Away
* @Date: 2018/6/1 16:55
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class SysRoleFuncAuthConfigParamDto extends BaseDto{

    private List<SysRoleFuncAuthConfigDto> source;

}
