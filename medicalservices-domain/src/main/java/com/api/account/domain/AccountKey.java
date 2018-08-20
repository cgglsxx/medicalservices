package com.api.account.domain;

import java.io.Serializable;

public class AccountKey implements Serializable {
    private String out_platform_id;

    private String channel;

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

}