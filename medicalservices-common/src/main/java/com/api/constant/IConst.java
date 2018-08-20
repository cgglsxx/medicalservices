package com.api.constant;

public interface IConst {
    /**his成功或肯定结果码**/
    String HIS_SUCCESS = "200";
    /**his否定结果码**/
    String HIS_UNSURE = "1";
    /**his错误结果码**/
    String HIS_ERROR = "-1";


    /**his就诊卡类型**/
    /**his就诊卡**/
    String ATTENDANCE_CARD = "1";
    /**医保卡**/
    String MEDICAL_INSURANCE_CARD = "2";
    /**居民健康卡**/
    String RESIDENT_HEALTH_CARD = "3";
    /**身份证**/
    String ID_CARD = "4";
    /**病人唯一标识**/
    String ONLY_CARD = "5";


}
