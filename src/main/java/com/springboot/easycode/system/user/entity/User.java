package com.springboot.easycode.system.user.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author hyhong
 * @since 2020-05-17 16:14:46
 */
public class User implements Serializable {
    private static final long serialVersionUID = 812168646740906119L;
    //用户ID
    private Long userId;
    //登录名
    private String userName;
    //登录密码
    private String userPwd;
    //加密盐
    private String salt;
    //真是姓名
    private String realName;
    //电话
    private String phone;
    //手机
    private String mobile;
    //QQ
    private String qq;
    //微信
    private String wechat;
    //邮箱
    private String email;
    //是否有效(0无效,1有效)
    private Boolean status;
    //用户类型(0管理员,1后台用户,2微信用户)
    private Integer type;
    //用户微信ID
    private Long userWxId;
    //创建人ID
    private Long createrId;
    //创建人名称
    private String createrName;
    //创建时间
    private Date createTime;
    //更新人ID
    private Long updaterId;
    //更新人名称
    private String updaterName;
    //更新时间
    private Date updateTime;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getUserWxId() {
        return userWxId;
    }

    public void setUserWxId(Long userWxId) {
        this.userWxId = userWxId;
    }

    public Long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}