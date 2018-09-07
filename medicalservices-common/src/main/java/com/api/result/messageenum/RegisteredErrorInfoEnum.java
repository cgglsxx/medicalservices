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
    REG_PREACCOUNT_ERROR("4","挂号预算失败"),
    REG_ACCOUNT_NOORDER("5","未查询到挂号预算记录"),
    REG_ACCOUNT_ERROR("6","结算失败"),
    REG_PAYRECORD_ERROR("7","支付结果记录失败"),
    REG_PAYRECORD_COUNTERROR("8","该订单his已结算"),
    REG_PAYRECORD_DATAERROR("9","该订单数据异常,请联系管理员"),
    REG_ORDER_NODATA("10","未查询到订单相关信息"),
    REG_ORDER_CANCELERROR("11","HIS取消结算失败"),
    REG_NODATA_ERROR("12","未查询到挂号相关记录"),
    REG_DATA_ERROR("13","挂号数据缺失");

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
