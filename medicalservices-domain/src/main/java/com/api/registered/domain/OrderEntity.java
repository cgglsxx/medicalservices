/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: fade </p>
 */
package com.api.registered.domain;

import com.api.card.domain.Card;
import com.api.dto.register.RegOrderSaveDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Description: <br/>
 *
 * @author cjh
 * @version 1.0
 * @date: 2018-08-28 14:52:17
 * @since JDK 1.8
 */
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	/**订单编号*/
	private String orderId;
	
	/**交易流水号*/
	private String paytradeno;
	
	/**交易生成时间*/
	private Date orderAddTime;
	
	/**交易描述*/
	private String tradeDesc;
	
	/**交易金额*/
	private BigDecimal tradeBalance;
	
	/**业务类型 1 挂号 2 门诊*/
	private String consumeType;
	
	/**第三方个人ID*/
	private String payAccount;
	
	/**收款账户（医疗机构编码）*/
	private String receiveAccount;
	
	/**订单支付状态  01 待支付 02 已支付 03 支付失败04已退单*/
	private String payResult;
	
	/**完成时间*/
	private Date finishTime;
	
	/**状态 0 无效 1 有效*/
	private String status;
	
	/**备注信息*/
	private String remarks;
	
	/**医疗机构编码*/
	private String agencyNum;
	
	/**医疗机构名称*/
	private String agencyName;
	
	/**病人编号*/
	private String patid;
	
	/**医疗机构就诊卡号*/
	private String cardno;
	
	/**姓名*/
	private String patName;
	
	/**身份证*/
	private String idcard;
	
	/**his订单状态*/
	private String hisResult;
	
	/**商户号*/
	private String merchant;
	
	/**渠道*/
	private String paychannel;
	
	/**0-无第三方支付（即个人支付金额为0），1-支付宝，2-微信支付，3-银联卡支付，4-院内支付*/
	private String payway;
	
	/**1 线上支付 2 当面付*/
	private String payType;
	
	public OrderEntity(String orderid, Card card, RegOrderSaveDto dto, Map preInfo, String status){
		this.orderId = orderid;
		this.tradeBalance = preInfo.get("regAmt")==null?new BigDecimal("0.00"):new BigDecimal(preInfo.get("regAmt").toString());
		this.payAccount = dto.getOut_platform_id();
		this.receiveAccount = dto.getOrgCode();
		this.status = "1";
		this.agencyNum = dto.getOrgCode();
		this.agencyName = dto.getHospital();
		this.patid = card.getPatid();
		this.patName = card.getPat_name();
		this.idcard = card.getIdcard_no();
		this.cardno = card.getCardno();
		this.paychannel = dto.getChannel();
		this.payway = dto.getPayway();
		this.payType = dto.getPayType();
	}
	/**{@linkplain #orderId}*/
	public OrderEntity setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
		
	/**{@linkplain #orderId}*/
	public String getOrderId() {
		return this.orderId;
	}

	/**{@linkplain #paytradeno}*/
	public OrderEntity setPaytradeno(String paytradeno) {
		this.paytradeno = paytradeno;
		return this;
	}
		
	/**{@linkplain #paytradeno}*/
	public String getPaytradeno() {
		return this.paytradeno;
	}

	/**{@linkplain #orderAddTime}*/
	public OrderEntity setOrderAddTime(Date orderAddTime) {
		this.orderAddTime = orderAddTime;
		return this;
	}
		
	/**{@linkplain #orderAddTime}*/
	public Date getOrderAddTime() {
		return this.orderAddTime;
	}

	/**{@linkplain #tradeDesc}*/
	public OrderEntity setTradeDesc(String tradeDesc) {
		this.tradeDesc = tradeDesc;
		return this;
	}
		
	/**{@linkplain #tradeDesc}*/
	public String getTradeDesc() {
		return this.tradeDesc;
	}

	/**{@linkplain #tradeBalance}*/
	public OrderEntity setTradeBalance(BigDecimal tradeBalance) {
		this.tradeBalance = tradeBalance;
		return this;
	}
		
	/**{@linkplain #tradeBalance}*/
	public BigDecimal getTradeBalance() {
		return this.tradeBalance;
	}

	/**{@linkplain #consumeType}*/
	public OrderEntity setConsumeType(String consumeType) {
		this.consumeType = consumeType;
		return this;
	}
		
	/**{@linkplain #consumeType}*/
	public String getConsumeType() {
		return this.consumeType;
	}

	/**{@linkplain #payAccount}*/
	public OrderEntity setPayAccount(String payAccount) {
		this.payAccount = payAccount;
		return this;
	}
		
	/**{@linkplain #payAccount}*/
	public String getPayAccount() {
		return this.payAccount;
	}

	/**{@linkplain #receiveAccount}*/
	public OrderEntity setReceiveAccount(String receiveAccount) {
		this.receiveAccount = receiveAccount;
		return this;
	}
		
	/**{@linkplain #receiveAccount}*/
	public String getReceiveAccount() {
		return this.receiveAccount;
	}

	/**{@linkplain #payResult}*/
	public OrderEntity setPayResult(String payResult) {
		this.payResult = payResult;
		return this;
	}
		
	/**{@linkplain #payResult}*/
	public String getPayResult() {
		return this.payResult;
	}

	/**{@linkplain #finishTime}*/
	public OrderEntity setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
		return this;
	}
		
	/**{@linkplain #finishTime}*/
	public Date getFinishTime() {
		return this.finishTime;
	}

	/**{@linkplain #status}*/
	public OrderEntity setStatus(String status) {
		this.status = status;
		return this;
	}
		
	/**{@linkplain #status}*/
	public String getStatus() {
		return this.status;
	}

	/**{@linkplain #remarks}*/
	public OrderEntity setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}
		
	/**{@linkplain #remarks}*/
	public String getRemarks() {
		return this.remarks;
	}

	/**{@linkplain #agencyNum}*/
	public OrderEntity setAgencyNum(String agencyNum) {
		this.agencyNum = agencyNum;
		return this;
	}
		
	/**{@linkplain #agencyNum}*/
	public String getAgencyNum() {
		return this.agencyNum;
	}

	/**{@linkplain #agencyName}*/
	public OrderEntity setAgencyName(String agencyName) {
		this.agencyName = agencyName;
		return this;
	}
		
	/**{@linkplain #agencyName}*/
	public String getAgencyName() {
		return this.agencyName;
	}

	/**{@linkplain #patid}*/
	public OrderEntity setPatid(String patid) {
		this.patid = patid;
		return this;
	}
		
	/**{@linkplain #patid}*/
	public String getPatid() {
		return this.patid;
	}

	/**{@linkplain #cardno}*/
	public OrderEntity setCardno(String cardno) {
		this.cardno = cardno;
		return this;
	}
		
	/**{@linkplain #cardno}*/
	public String getCardno() {
		return this.cardno;
	}

	/**{@linkplain #patName}*/
	public OrderEntity setPatName(String patName) {
		this.patName = patName;
		return this;
	}
		
	/**{@linkplain #patName}*/
	public String getPatName() {
		return this.patName;
	}

	/**{@linkplain #idcard}*/
	public OrderEntity setIdcard(String idcard) {
		this.idcard = idcard;
		return this;
	}
		
	/**{@linkplain #idcard}*/
	public String getIdcard() {
		return this.idcard;
	}

	/**{@linkplain #hisResult}*/
	public OrderEntity setHisResult(String hisResult) {
		this.hisResult = hisResult;
		return this;
	}
		
	/**{@linkplain #hisResult}*/
	public String getHisResult() {
		return this.hisResult;
	}

	/**{@linkplain #merchant}*/
	public OrderEntity setMerchant(String merchant) {
		this.merchant = merchant;
		return this;
	}
		
	/**{@linkplain #merchant}*/
	public String getMerchant() {
		return this.merchant;
	}

	/**{@linkplain #paychannel}*/
	public OrderEntity setPaychannel(String paychannel) {
		this.paychannel = paychannel;
		return this;
	}
		
	/**{@linkplain #paychannel}*/
	public String getPaychannel() {
		return this.paychannel;
	}

	/**{@linkplain #payway}*/
	public OrderEntity setPayway(String payway) {
		this.payway = payway;
		return this;
	}
		
	/**{@linkplain #payway}*/
	public String getPayway() {
		return this.payway;
	}

	/**{@linkplain #payType}*/
	public OrderEntity setPayType(String payType) {
		this.payType = payType;
		return this;
	}
		
	/**{@linkplain #payType}*/
	public String getPayType() {
		return this.payType;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("OrderEntity [");
  	    sb.append("orderId=");
  	    sb.append(orderId).append(",");
  	    sb.append(",paytradeno=");
  	    sb.append(paytradeno).append(",");
  	    sb.append(",orderAddTime=");
  	    sb.append(orderAddTime).append(",");
  	    sb.append(",tradeDesc=");
  	    sb.append(tradeDesc).append(",");
  	    sb.append(",tradeBalance=");
  	    sb.append(tradeBalance).append(",");
  	    sb.append(",consumeType=");
  	    sb.append(consumeType).append(",");
  	    sb.append(",payAccount=");
  	    sb.append(payAccount).append(",");
  	    sb.append(",receiveAccount=");
  	    sb.append(receiveAccount).append(",");
  	    sb.append(",payResult=");
  	    sb.append(payResult).append(",");
  	    sb.append(",finishTime=");
  	    sb.append(finishTime).append(",");
  	    sb.append(",status=");
  	    sb.append(status).append(",");
  	    sb.append(",remarks=");
  	    sb.append(remarks).append(",");
  	    sb.append(",agencyNum=");
  	    sb.append(agencyNum).append(",");
  	    sb.append(",agencyName=");
  	    sb.append(agencyName).append(",");
  	    sb.append(",patid=");
  	    sb.append(patid).append(",");
  	    sb.append(",cardno=");
  	    sb.append(cardno).append(",");
  	    sb.append(",patName=");
  	    sb.append(patName).append(",");
  	    sb.append(",idcard=");
  	    sb.append(idcard).append(",");
  	    sb.append(",hisResult=");
  	    sb.append(hisResult).append(",");
  	    sb.append(",merchant=");
  	    sb.append(merchant).append(",");
  	    sb.append(",paychannel=");
  	    sb.append(paychannel).append(",");
  	    sb.append(",payway=");
  	    sb.append(payway).append(",");
  	    sb.append(",payType=");
  	    sb.append(payType).append(",");
        sb.append(']');
        return sb.toString();
	}

}