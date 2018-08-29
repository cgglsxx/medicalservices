package com.api.card.service;


import com.api.dto.card.*;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;


/**
 * 就诊卡建档模块模块service
 */
public interface CardForPatService {

    /**
     * 无卡注册
     * @param dto
     * @return
     */
    ResultBody saveBooking(BookingDto dto) throws GlobalErrorInfoException;

    /**
     * 绑定就诊卡
     * @param dto
     * @return
     */
    ResultBody saveBindCard(BindCardDto dto) throws GlobalErrorInfoException;

    /**
     * 解绑就诊卡
     * @param dto
     * @return
     */
    ResultBody updateUnBindCard(UnBindCardDto dto) throws GlobalErrorInfoException;

    /**
     * 查询就诊卡
     * @param dto
     * @return
     * @throws GlobalErrorInfoException
     */
    ResultBody queryCardInfo(QueryCardDto dto) ;

    /**
     * 查询患者是否绑定就诊卡
     * @param dto
     * @return
     * @throws GlobalErrorInfoException
     */
    ResultBody queryCardInfoForPerson(QueryCardForPersonDto dto) throws GlobalErrorInfoException;
}
