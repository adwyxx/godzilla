package com.adwyxx.godzilla.oauth.entity;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {

    private static final long serialVersionUID = 2689740856661492758L;
    private Integer id;

    private String roleName;

    private Integer creatorId;

    private String creatorName;

    private Date createDate;

    private Double orderNo;

    private Boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Double orderNo) {
        this.orderNo = orderNo;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}