package com.api.result.messageenum;


import com.api.result.ErrorInfoInterface;

/**
 * 住院业务错误码
 *
 */
public enum InpatientErrorInfoEnum implements ErrorInfoInterface {
    PARAMS_NO_COMPLETE("1","params no complete"),
    PARAMS_TRANS_ERROR("2","参数转换异常"),
    INPATIENT_ACCOUNT_NOORDER("-105","未查询到挂号预算记录"),
    INPATIENT_ACCOUNT_ERROR("-106","结算失败"),
    INPATIENT_PAYRECORD_ERROR("-107","支付结果记录失败"),
    INPATIENT_PAYRECORD_COUNTERROR("-108","该订单his已结算"),
    INPATIENT_PAYRECORD_DATAERROR("-109","该订单数据异常,请联系管理员"),
    INPATIENT_ORDER_NODATA("-110","未查询到订单相关信息"),
    INPATIENT_ORDER_CANCELERROR("-111","HIS取消结算失败");

    private String code;

    private String message;

    InpatientErrorInfoEnum(String code, String message) {
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
