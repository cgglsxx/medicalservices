package com.api.card.domain;

public class CardKey {
    private String yad901;

    private String yad961;

    private String akb020;

    private String aac002;

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

    public String getAkb020() {
        return akb020;
    }

    public void setAkb020(String akb020) {
        this.akb020 = akb020 == null ? null : akb020.trim();
    }

    public String getAac002() {
        return aac002;
    }

    public void setAac002(String aac002) {
        this.aac002 = aac002 == null ? null : aac002.trim();
    }
}