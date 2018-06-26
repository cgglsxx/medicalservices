package com.api.account.domain;

import java.io.Serializable;
import java.util.Date;

public class Account extends AccountKey implements Serializable{
    private String des;

    private Date createTime;

    private Date updateTime;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}