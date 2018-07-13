
package com.laman.fusion.base;

import com.laman.fusion.base.util.ObjectHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 *
 */
public class CPContext {
    private static final ThreadLocal<SeUserInfo> LOCAL = ThreadLocal.withInitial(() -> new SeUserInfo());

    private static final Logger LOGGER= LoggerFactory.getLogger(CPContext.class);

    private static SeUserInfo seUserInfo;

    private CPContext() {

    }

    public static SeUserInfo getContext() {
        LOGGER.info(LOCAL.get().toString());
        return LOCAL.get();
    }

    public static void putContext(SeUserInfo cpContext) {
        LOCAL.set(cpContext);
    }

    public static void removeContext() {
        LOCAL.remove();
    }

    public SeUserInfo getSeUserInfo() {
        return seUserInfo;
    }

    public void setSeUserInfo(SeUserInfo seUserInfo) {
        this.seUserInfo = seUserInfo;
    }

    /**
     * @Author: Away
     * @Title: copyVlueAndSetSeUserInfo
     * @Description: 为当前用户赋值
     * @Param: object
     * @Return: void
     * @Date: 2018/4/17 10:11
     * @Version: 2018/4/17 10:11
     */
    public static void copyValueAndSetSeUserInfo(Object object){
        if(ObjectHelper.isNotEmpty(object)){
            if(ObjectHelper.isEmpty(getContext())){
                LOCAL.set(new SeUserInfo());
            }
            BeanUtils.copyProperties(object,getContext());
        }
    }

    /**
     * 用户信息
     */
    public static class SeUserInfo implements Serializable {
        private Long id;
        private String userName;
        private String displayName;
        private Long rId;//关联id
        private String type;
        private String phone;
        /**用户邮箱**/
        private String emailAddress;
        /**
         * 具有权限的接口
         **/
        private String authUrls;
        /**平台编号**/
        private String platformCode;

        /**所属角色编号**/
        private String roles;;

        /**私有密钥**/
        private String privateKey;

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }

        public String getPlatformCode() {
            return platformCode;
        }

        public void setPlatformCode(String platformCode) {
            this.platformCode = platformCode;
        }

        public String getAuthUrls() {
            return authUrls;
        }

        public void setAuthUrls(String authUrls) {
            this.authUrls = authUrls;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Long getrId() {
            return rId;
        }

        public void setrId(Long rId) {
            this.rId = rId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
