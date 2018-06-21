package com.api.card.dao;


import com.api.card.domain.Card;
import com.api.card.domain.CardKey;

import java.util.List;

public interface CardMapper {
    int deleteByPrimaryKey(CardKey key);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(CardKey key);

    List<Card> selectByAccount(CardKey key);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);
}