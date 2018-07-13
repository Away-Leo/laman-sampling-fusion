package com.laman.fusion.base.enums;

/**
 * @Title: ENUM_USER_TYPE.java
 * @Description: 用户类型
 * @Author: Away
 * @Date: 2018/4/12 12:01
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
public enum ENUM_IDENTIFY_TYPE {

    WAITTING(1,"待选"),
    CANCEL(2,"注销"),
    EXPERT(3,"专家");

    public final String name;
    public final Integer code;

    private ENUM_IDENTIFY_TYPE(Integer code, String name){
        this.name=name;
        this.code=code;
    }


}
