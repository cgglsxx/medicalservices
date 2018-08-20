package com.api.result.messageenum;


import com.api.result.ErrorInfoInterface;

/**
 * 挂号业务错误码
 *
 */
public enum CardErrorInfoEnum implements ErrorInfoInterface {
    CARD_REGISTERED_REPEAT("-1001","已绑定就诊卡,无卡注册失败"),
    CARD_ALREADY_BIND("-1002","已绑定就诊卡,请解绑后重新绑定"),
    CARD_BIND_CEILING("-1003","就诊卡绑定超过上限"),
    CARD_UNBIND_NODATA("-1004","解绑失败,该患者未绑定就诊卡"),
    CARD_UNBIND_DATA_ERROR("-1005","解绑失败,数据异常"),
    CARD_PATINFO_QUERYERROR("-1006","患者信息查询返回数据格式异常"),
    CARD_PATINFO_EXIST("-1007","患者已建档,请直接在平台进行绑定操作"),
    CARD_NO_PATINFO_EXIST("-1008","患者未建档,请先建档"),
    CARD_INFO_ERROR("-1009","患者信息验证失败");

    private String code;

    private String message;

    CardErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
