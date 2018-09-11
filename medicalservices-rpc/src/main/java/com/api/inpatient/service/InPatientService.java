package com.api.inpatient.service;


import com.api.dto.inpatient.*;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;


/**
 * 住院服务模块service
 */
public interface InPatientService {

    /**
     * 在住院或出院情况下，都能查询住院病人基本信息
     * @param dto
     * @return
     */
    ResultBody queryInpatientInfo(InpatientInfoQueryDto dto) throws GlobalErrorInfoException;
    /**
     * 查询住院已结账的费用清单
     * @param dto
     * @return
     */
    ResultBody queryInpCost(InpatientCostQueryDto dto) throws GlobalErrorInfoException;
    /**
     * 在住院或出院情况下，都能查询住院日缴款记录
     * @param dto
     * @return
     */
    ResultBody queryPrePayment(InpatientPrePaymentQueryDto dto) throws GlobalErrorInfoException;
    /**
     * 在住院或出院情况下，都能查询住院历史记录
     * @param dto
     * @return
     */
    ResultBody inpHistoryRecord(InpatientRecordsQueryDto dto) throws GlobalErrorInfoException;
    /**
     * 住院预交金订单
     * @param dto
     * @return
     */
    ResultBody createPrePayment(InpatientPrePaymentOrderDto dto) throws GlobalErrorInfoException;
    /**
     * 住院预交金缴纳
     * @param dto
     * @return
     */
    ResultBody inpPrePayment(InpatientPrePaymentDto dto) throws GlobalErrorInfoException;

}
