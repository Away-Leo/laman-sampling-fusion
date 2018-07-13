package com.laman.biz.original.app.dto;

import com.laman.fusion.base.dtos.BaseDto;


/**
* @Title: DetectionItem
* @Description:  检测项数据字典
* @Author: Away
* @Date: 2018/7/5 16:43
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public class DetectionItemDto extends BaseDto {

    /**检测项唯一编码**/
    private String itemCode;

    /**检测项名称**/
    private String itemName;

    /**检测项类型**/
    private String itemType;

    /**状态**/
    private String state;

    /**不知**/
    private String letter;

    /**不知**/
    private String node;

    /**父节点**/
    private String parentNode;

    /**itemOld**/
    private String itemOld;

    /**oldCode**/
    private String oldCode;

    /**re_code**/
    private String reCode;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public String getItemOld() {
        return itemOld;
    }

    public void setItemOld(String itemOld) {
        this.itemOld = itemOld;
    }

    public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }

    public String getReCode() {
        return reCode;
    }

    public void setReCode(String reCode) {
        this.reCode = reCode;
    }
}