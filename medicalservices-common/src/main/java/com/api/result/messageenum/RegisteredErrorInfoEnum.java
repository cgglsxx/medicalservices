package com.api.result.messageenum;


import com.api.result.ErrorInfoInterface;

/**
 * 挂号业务错误码
 *
 */
public enum RegisteredErrorInfoEnum implements ErrorInfoInterface {
    PARAMS_NO_COMPLETE("1","params no complete"),
    PARAMS_TRANS_ERROR("2","参数转换异常"),
    REG_LOCK_ERROR("3","锁号失败"),
    REG_PREACCOUNT_ERROR("4","挂号预算失败");

    private String code;

    private String message;

    RegisteredErrorInfoEnum(String code, String message) {
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
