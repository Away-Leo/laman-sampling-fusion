package com.laman.fusion.base.enums;

/**
* @Title: ENUM_PROPERTY_TYPE
* @Description:  表格属性类型
* @Author: Away
* @Date: 2018/7/11 16:39
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public enum ENUM_PROPERTY_TYPE {

    IMG("img","图片"),
    RADIO("radio","单选框"),
    CHECKBOX("checkbox","复选框");

    public final String msg;
    public final String code;

    private ENUM_PROPERTY_TYPE(String code, String msg){
        this.msg=msg;
        this.code=code;
    }


}
