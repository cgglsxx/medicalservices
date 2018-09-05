package com.api.result.messageenum;


import com.api.result.ErrorInfoInterface;

/**
 * 挂号业务错误码
 *
 */
public enum ClinicErrorInfoEnum implements ErrorInfoInterface {
    PARAMS_NO_COMPLETE("1","params no complete"),
    PARAMS_TRANS_ERROR("2","参数转换异常"),
    CLINIC_LOCK_ERROR("103","锁号失败"),
    CLINIC_PREACCOUNT_ERROR("104","挂号预算失败"),
    CLINIC_ACCOUNT_NOORDER("105","未查询到挂号预算记录"),
    CLINIC_ACCOUNT_ERROR("106","结算失败"),
    CLINIC_PAYRECORD_ERROR("107","支付结果记录失败"),
    CLINIC_PAYRECORD_COUNTERROR("108","该订单his已结算"),
    CLINIC_PAYRECORD_DATAERROR("109","该订单数据异常,请联系管理员"),
    CLINIC_ORDER_NODATA("110","未查询到订单相关信息"),
    CLINIC_ORDER_CANCELERROR("111","HIS取消结算失败");

    private String code;

    private String message;

    ClinicErrorInfoEnum(String code, String message) {
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
