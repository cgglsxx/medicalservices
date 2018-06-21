package com.api.result.messageenum;

import com.api.result.ErrorInfoInterface;

/**
 * 应用系统级别的响应码
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface{
    SUCCESS("0", "success"),
    SYS_ERROR("-1", "系统内部异常"),
    ADAPTER_ERROR("-2", "获取his适配器失败"),
    HIS_INTERACTION_ERROR("-3", "his系统交互异常"),
    XML_PARSING_ERROR("-4", "数据解析异常"),
    HIS_RESULT_ERROR("-5", "his返回数据异常"),
    DB_OPERATION_ERROR("-6", "db错误");

    private String code;

    private String message;

    GlobalErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
