package com.api.card.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.api.dto.card.BindCardDto;
import com.api.dto.card.BookingDto;
import com.api.dto.card.QueryCardDto;
import com.api.dto.card.UnBindCardDto;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import org.springframework.stereotype.Component;


/**
 * 就诊卡服务实现
 */
@Component
public class CardServiceImpl implements CardService {
    @Reference
    CardService cardService;

    @Override
    public ResultBody saveBooking(BookingDto dto) throws GlobalErrorInfoException {
        return cardService.saveBooking(dto);
    }

    @Override
    public ResultBody saveBindCard(BindCardDto dto) throws GlobalErrorInfoException {
        return cardService.saveBindCard(dto);
    }

    @Override
    public ResultBody updateUnBindCard(UnBindCardDto dto) throws GlobalErrorInfoException {
        return cardService.updateUnBindCard(dto);
    }

    @Override
    public ResultBody queryCardInfo(QueryCardDto dto) throws GlobalErrorInfoException {
        return cardService.queryCardInfo(dto);
    }
}
