package com.api.card.service.impl;

import com.api.account.dao.AccountMapper;
import com.api.account.domain.Account;
import com.api.account.domain.AccountKey;
import com.api.card.dao.CardMapper;
import com.api.card.domain.Card;
import com.api.card.domain.CardKey;
import com.api.card.service.CardService;
import com.api.dto.card.BindCardDto;
import com.api.dto.card.BookingDto;
import com.api.dto.card.QueryCardDto;
import com.api.dto.card.UnBindCardDto;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.CardErrorInfoEnum;
import com.api.result.messageenum.GlobalErrorInfoEnum;
import com.api.setting.HisSetting;
import com.api.util.RedissLockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 就诊卡服务实现
 */
@Service("cardService")
public class CardServiceImpl implements CardService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    CardMapper cardMapper;
    @Autowired
    HisSetting setting;
    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public ResultBody saveBooking(BookingDto dto) throws GlobalErrorInfoException {
        String key = setting.getRedisLockCardPre()+dto.getYad901()+dto.getYad961();
        try{
            //此处存在线程安全问题 锁第三方id的此次无卡操作（该接口锁和绑卡锁互斥 限制用户同一时刻只能做其中一个操作）
            RedissLockUtil.lock(key, TimeUnit.MINUTES,5);
            //检查绑卡条件是否满足
            String yad961 = dto.getYad961();
            String yad901 = dto.getYad901();
            String akb020 = dto.getAkb020();
            String aac002 = dto.getAac002();
            ResultBody resultBody = checkBindCondition(yad901,yad961,aac002,akb020);
            if(!resultBody.getCode().equals(GlobalErrorInfoEnum.SUCCESS.getCode())){
                return resultBody;
            }
            //todo 调用his无卡注册 获取用户的注册信息封装写入卡管理
            //ResultBody hisResult = HISTransAdapterContainer.getAdapter(setting.getKey()).sendMsg(ReflectMapUtil.beanToMap(dto), IConst.GETKSDEPTINFO);
            Card cardInsert = new Card();
            cardInsert.setAac003(dto.getAac003());
            cardInsert.setAddress(dto.getAddress());
            cardInsert.setCardno(dto.getAac002());
            cardInsert.setPatid(dto.getAac002());
            cardInsert.setRelationship(dto.getRelationship());
            cardInsert.setStatus("1");
            cardInsert.setType("1");
            cardInsert.setYae098(dto.getYae098());
            cardInsert.setAac002(dto.getAac002());
            cardInsert.setAkb020(dto.getAkb020());
            cardInsert.setYad901(dto.getYad901());
            cardInsert.setYad961(dto.getYad961());
            cardMapper.insert(cardInsert);
            return new ResultBody();
        }catch (Exception e){
            logger.error(e.toString());
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.DB_OPERATION_ERROR);
        }finally {
            RedissLockUtil.unlock(key);
        }
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public ResultBody saveBindCard(BindCardDto dto) throws GlobalErrorInfoException {
        String key = setting.getRedisLockCardPre()+dto.getYad901()+dto.getYad961();
        try{
            //此处存在线程安全问题 锁第三方id的此次绑卡操作(该接口锁和无卡注册锁互斥 限制用户同一时刻只能做其中一个操作)
            RedissLockUtil.lock(key, TimeUnit.MINUTES,5);
            //检查绑卡条件是否满足
            String yad961 = dto.getYad961();
            String yad901 = dto.getYad901();
            String akb020 = dto.getAkb020();
            String aac002 = dto.getAac002();
            ResultBody resultBody = checkBindCondition(yad901,yad961,aac002,akb020);
            if(!resultBody.getCode().equals(GlobalErrorInfoEnum.SUCCESS.getCode())){
                return resultBody;
            }
            //todo 调用his绑卡查询 获取用户绑卡信息对比验证后写入卡管理
            //ResultBody hisResult = HISTransAdapterContainer.getAdapter(setting.getKey()).sendMsg(ReflectMapUtil.beanToMap(dto), IConst.GETKSDEPTINFO);
            Card cardInsert = new Card();
            cardInsert.setAac003(dto.getAac003());
            cardInsert.setAddress(dto.getAddress());
            cardInsert.setCardno(dto.getYac005());
            cardInsert.setPatid(aac002);
            cardInsert.setRelationship(dto.getRelationship());
            cardInsert.setStatus("1");
            cardInsert.setType(dto.getCardType());
            cardInsert.setYae098(dto.getYae098());
            cardInsert.setAac002(aac002);
            cardInsert.setAkb020(akb020);
            cardInsert.setYad901(yad901);
            cardInsert.setYad961(yad961);
            cardMapper.insert(cardInsert);
            return new ResultBody();
        }catch (Exception e){
            logger.error(e.toString());
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.DB_OPERATION_ERROR);
        }finally {
            RedissLockUtil.unlock(key);
        }
    }
    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public ResultBody updateUnBindCard(UnBindCardDto dto) throws GlobalErrorInfoException {
        CardKey cardDelete = new CardKey();
        cardDelete.setAac002(dto.getAac002());
        cardDelete.setAkb020(dto.getAkb020());
        cardDelete.setYad901(dto.getYad901());
        cardDelete.setYad961(dto.getYad961());
        int count = cardMapper.deleteByPrimaryKey(cardDelete);
        if(count == 0){
            return new ResultBody(CardErrorInfoEnum.CARD_UNBIND_NODATA);
        }else if(count == 1){
            return new ResultBody();
        }else{
            throw new GlobalErrorInfoException(CardErrorInfoEnum.CARD_UNBIND_DATA_ERROR);
        }
    }

    @Override
    public ResultBody queryCardInfo(QueryCardDto dto) throws GlobalErrorInfoException {
        CardKey cardQuery = new CardKey();
        cardQuery.setAkb020(dto.getAkb020());
        cardQuery.setYad901(dto.getYad901());
        cardQuery.setYad961(dto.getYad961());
        List<Card> cards = cardMapper.selectByAccount(cardQuery);
        return new ResultBody(cards);
    }
    private ResultBody checkBindCondition(String yad901,String yad961,String aac002,String akb020){
        //step1 先判断该账号是否绑定就诊卡超过5条
        CardKey cardQuery = new CardKey();
        cardQuery.setYad961(yad961);
        cardQuery.setAkb020(akb020);
        cardQuery.setYad901(yad901);
        List<Card> cards = cardMapper.selectByAccount(cardQuery);
        if(cards != null && cards.size() >= 5){
            return new ResultBody(CardErrorInfoEnum.CARD_BIND_CEILING);
        }
        //step2 判断该用户是否绑定就诊卡
        for(Object card:cards){
            Card cardObj = (Card) card;
            if(cardObj.getAac002().equals(aac002)){
                //表示该用户已经绑定就诊卡
                return new ResultBody(CardErrorInfoEnum.CARD_REGISTERED_REPEAT);
            }
        }
        //step3 判断该三方账号是否在平台中存在
        AccountKey accuntQuery = new AccountKey();
        accuntQuery.setYad901(yad901);
        accuntQuery.setYad961(yad961);
        Account account = accountMapper.selectByPrimaryKey(accuntQuery);
        if(account == null){
            //插入该账号信息
            Account accuntInsert = new Account();
            accuntInsert.setYad961(yad961);
            accuntInsert.setYad901(yad901);
            accountMapper.insert(accuntInsert);
        }
        return new ResultBody();
    }
}
