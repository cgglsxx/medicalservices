package com.api.inpatient;


import com.api.dto.inpatient.InpatientCostQueryDto;
import com.api.dto.inpatient.InpatientInfoQueryDto;
import com.api.dto.inpatient.InpatientPrePaymentQueryDto;
import com.api.dto.inpatient.InpatientRecordsQueryDto;
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

}
