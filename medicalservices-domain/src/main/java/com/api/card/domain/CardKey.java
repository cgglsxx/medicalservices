package com.api.card.domain;

import java.io.Serializable;

public class CardKey implements Serializable {
    private String out_platform_id;

    private String channel;

    private String agency_num;

    private String idcard_no;

    public String getOut_platform_id() {
        return out_platform_id;
    }

    public void setOut_platform_id(String out_platform_id) {
        this.out_platform_id = out_platform_id == null ? null : out_platform_id.trim();;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }
    public String getAgency_num() {
        return agency_num;
    }

    public void setAgency_num(String agency_num) {
        this.agency_num = agency_num == null ? null : agency_num.trim();
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no == null ? null : idcard_no.trim();
    }
}