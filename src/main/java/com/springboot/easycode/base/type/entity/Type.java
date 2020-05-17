package com.springboot.easycode.base.type.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Type)实体类
 *
 * @author hyhong
 * @since 2020-05-17 16:15:06
 */
public class Type implements Serializable {
    private static final long serialVersionUID = 642646072385715794L;
    //类别ID
    private Long typeId;
    //类别名称
    private String typeName;
    //类别编码
    private String typeCode;
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


    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
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