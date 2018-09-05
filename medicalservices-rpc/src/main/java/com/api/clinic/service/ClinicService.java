package com.api.clinic.service;


import com.api.dto.clinic.ClinicAccountDto;
import com.api.dto.clinic.ClinicBillDetailQueryDto;
import com.api.dto.clinic.ClinicBillInfoQueryDto;
import com.api.dto.clinic.ClinicPreAccountDto;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;


/**
 * 门诊缴费模块service
 */
public interface ClinicService {

    /**
     * 进行门诊缴费信息查询
     * @param dto
     * @return
     */
    ResultBody queryClinicBillInfo(ClinicBillInfoQueryDto dto) throws GlobalErrorInfoException;
    /**
     * 进行门诊缴费明细信息查询
     * @param dto
     * @return
     */
    ResultBody queryClinicBillDetail(ClinicBillDetailQueryDto dto) throws GlobalErrorInfoException;
    /**
     * 门诊缴费预算
     * @param dto
     * @return
     */
    ResultBody clinicPreAccount(ClinicPreAccountDto dto) throws GlobalErrorInfoException;
    /**
     * 门诊缴费结算
     * @param dto
     * @return
     */
    ResultBody clinicAccount(ClinicAccountDto dto) throws GlobalErrorInfoException;

}
