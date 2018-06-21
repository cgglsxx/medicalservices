package com.api.account.dao;


import com.api.account.domain.Account;
import com.api.account.domain.AccountKey;

public interface AccountMapper {
    int deleteByPrimaryKey(AccountKey key);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(AccountKey key);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}