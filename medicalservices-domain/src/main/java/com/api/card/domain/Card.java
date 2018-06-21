package com.api.card.domain;

import java.util.Date;

public class Card extends CardKey {
    private String patid;

    private String aac003;

    private String yae098;

    private String address;

    private String cardno;

    private String status;

    private String type;

    private String relationship;

    private Date bindTime;

    private Date lastUpdatetime;

    public String getPatid() {
        return patid;
    }

    public void setPatid(String patid) {
        this.patid = patid == null ? null : patid.trim();
    }

    public String getAac003() {
        return aac003;
    }

    public void setAac003(String aac003) {
        this.aac003 = aac003 == null ? null : aac003.trim();
    }

    public String getYae098() {
        return yae098;
    }

    public void setYae098(String yae098) {
        this.yae098 = yae098 == null ? null : yae098.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship == null ? null : relationship.trim();
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Date getLastUpdatetime() {
        return lastUpdatetime;
    }

    public void setLastUpdatetime(Date lastUpdatetime) {
        this.lastUpdatetime = lastUpdatetime;
    }
}