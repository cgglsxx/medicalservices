package com.api.account.domain;

import java.io.Serializable;

public class AccountKey implements Serializable {
    private String yad901;

    private String yad961;

    public String getYad901() {
        return yad901;
    }

    public void setYad901(String yad901) {
        this.yad901 = yad901 == null ? null : yad901.trim();
    }

    public String getYad961() {
        return yad961;
    }

    public void setYad961(String yad961) {
        this.yad961 = yad961 == null ? null : yad961.trim();
    }
}