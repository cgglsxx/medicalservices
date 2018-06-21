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
    CARD_UNBIND_NODATA("-1004","解绑失败,该用户未绑定就诊卡"),
    CARD_UNBIND_DATA_ERROR("-1005","解绑失败,数据异常");

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
