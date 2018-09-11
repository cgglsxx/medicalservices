/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: fade </p>
 */
package com.api.registered.domain;

import com.api.card.domain.Card;
import com.api.dto.clinic.ClinicPreAccountDto;
import com.api.dto.inpatient.InpatientPrePaymentOrderDto;
import com.api.dto.register.RegOrderSaveDto;
import com.api.util.DateUtil;

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
public class OrderSettlementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	/**挂号订单编号*/
	private String orderId;
	
	/**病人编号*/
	private String patid;
	
	/**患者姓名*/
	private String patName;
	
	/**身份证*/
	private String idcard;
	
	/**卡类型*/
	private String cardtype;
	
	/**就诊卡号*/
	private String cardno;
	
	/**1-预约 2-挂号(此字段仅对于挂号有意义)*/
	private String preregflag;
	
	/**1-专家，2-专病，3-普通，4-特需门诊(此字段仅对于挂号有意义)*/
	private String regtype;
	
	/**排班ID(此字段仅对于挂号有意义)*/
	private String tscid;
	
	/**号源序号(此字段仅对于挂号有意义)*/
	private String seqnum;
	
	/**挂号日期(此字段仅对于挂号有意义)*/
	private Date tscdate;
	
	/**上下午标志 0-全天，1-上午，2-下午，3-夜间(此字段仅对于挂号有意义)*/
	private String daysection;
	
	/**是否扣院内账户 0-不从院内账户走，1-走院内账户*/
	private String whetherded;
	
	/**是否自费结算 0-根据病人医保代码结算，1自费结算*/
	private String whetherset;
	
	/**院内卡号 (当选择院内卡支付时，卡号不能为空)*/
	private String hospitalcardno;
	
	/**院内支付密码*/
	private String password;
	
	/**挂号单唯一编号(此字段仅对于挂号有意义)*/
	private String regid;
	
	/**科室代码（科室编号或ID，无特殊字符）(此字段仅对于挂号有意义)*/
	private String deptid;
	
	/**科室名称(此字段仅对于挂号有意义)*/
	private String deptname;
	
	/**医生代码(此字段仅对于挂号有意义)*/
	private String drid;
	
	/**医生姓名(此字段仅对于挂号有意义)*/
	private String drname;
	
	/**挂号费(此字段仅对于挂号有意义)*/
	private BigDecimal regfee;
	
	/**医院优惠金额*/
	private BigDecimal discountsamt;
	
	/**自付金额*/
	private BigDecimal personamt;
	
	/**收据号（本地挂号费用结算对应的his唯一流水号，请妥善保存，用于对账）如果是诊间缴费对应statementNo字段(文档)*/
	private String receiptno;
	
	/**账户支付（院内预付费账户支付）*/
	private BigDecimal accountpayment;
	
	/**账户余额（院内预付费账户的余额）*/
	private BigDecimal accountbalance;
	
	/**诊疗费(此字段仅对于挂号有意义)*/
	private BigDecimal treatfee;
	
	/**挂号总金额  如果是诊间缴费对应chargeTamt字段 （文档中）*/
	private BigDecimal regamt;
	
	/**结算时实际支付金额*/
	private BigDecimal paymoney;
	
	/**支付方式（0-无第三方支付（即个人支付金额为0），1-支付宝，2-微信支付，3-银联卡支付，4-院内支付）*/
	private String payway;
	
	/**支付渠道*/
	private String paychannel;
	
	/**支付流水号*/
	private String paytradeno;
	
	/**挂号收据号(此字段仅对于挂号有意义)*/
	private String regreceipt;
	
	/**1 预算 2 结算*/
	private String status;
	
	/**单据列表（或处方号）多个编号用“|”分隔 (根据HIS情况可以传空)(此字段仅对于诊间缴费有意义)*/
	private String receiptlist;
	
	/**费用类型列表 多个类别用“|”分隔，与单据列表对应 (根据HIS情况可以传空)(此字段仅对于诊间缴费有意义)*/
	private String chargetypelist;
	
	/**自付金额列表(此字段仅对于诊间缴费有意义)*/
	private String personamtlist;
	
	/**配药窗口集合(此字段仅对于诊间缴费有意义)*/
	private String pyckjh;
	
	/**发药窗口集合(此字段仅对于诊间缴费有意义)*/
	private String fyckjh;
	
	/**缴费单号合集(此字段仅对于诊间缴费有意义)*/
	private String sjhinfo;
	
	/**his结算时间*/
	private Date accountdate;
	
	/**添加时间*/
	private Date addTime;
	
	/**最后更新时间*/
	private Date lastUpdate;

	public OrderSettlementEntity(String orderid, Card card, RegOrderSaveDto dto, Map preInfo,String status){
		this.orderId = orderid;
		this.patid = card.getPatid();
		this.patName = card.getPat_name();
		this.idcard = card.getIdcard_no();
		this.cardtype = card.getType();
		this.cardno = card.getCardno();
		this.preregflag = dto.getPreregFlag();
		this.regtype = dto.getRegType();
		this.tscid = dto.getTscid();
		this.seqnum = preInfo.get("seqnum") == null?null:preInfo.get("seqnum").toString();
		this.tscdate = DateUtil.formatStringToDate(dto.getTscdate(),DateUtil.FORMAT_DEFAULT);
		this.daysection = dto.getDaySection();
		this.whetherded =dto.getWhetherDed();
		this.whetherset = dto.getWhetherSet();
		this.hospitalcardno = dto.getHospitalcardNo();
		this.password = dto.getPassword();
		this.regid = preInfo.get("regId") == null?null:preInfo.get("regId").toString();
		this.deptid = dto.getDeptId();
		this.deptname = preInfo.get("deptName") == null?null:preInfo.get("deptName").toString();
		this.drid = dto.getDrId();
		this.drname = preInfo.get("drName") == null?null:preInfo.get("drName").toString();
		this.regfee = preInfo.get("regFee")==null||"".equals(preInfo.get("regFee").toString())?new BigDecimal("0.00"):new BigDecimal(preInfo.get("regFee").toString());
		this.discountsamt = preInfo.get("discountsAmt")==null||"".equals(preInfo.get("discountsAmt").toString())?new BigDecimal("0.00"):new BigDecimal(preInfo.get("discountsAmt").toString());
		this.personamt = preInfo.get("personAmt")==null||"".equals(preInfo.get("personAmt").toString())?new BigDecimal("0.00"):new BigDecimal(preInfo.get("personAmt").toString());
		this.receiptno = preInfo.get("receiptNo") == null?null:preInfo.get("receiptNo").toString();
		this.accountpayment = preInfo.get("accountPayment")==null||"".equals(preInfo.get("accountPayment").toString())?new BigDecimal("0.00"):new BigDecimal(preInfo.get("accountPayment").toString());
		this.accountbalance = preInfo.get("accountBalance")==null||"".equals(preInfo.get("accountBalance").toString())?new BigDecimal("0.00"):new BigDecimal(preInfo.get("accountBalance").toString());
		this.treatfee = preInfo.get("treatFee")==null||"".equals(preInfo.get("treatFee").toString())?new BigDecimal("0.00"):new BigDecimal(preInfo.get("treatFee").toString());
		this.regamt = preInfo.get("regAmt")==null||"".equals(preInfo.get("regAmt").toString())?new BigDecimal("0.00"):new BigDecimal(preInfo.get("regAmt").toString());
		this.status = status;

	}
	public OrderSettlementEntity(String orderid, Card card, ClinicPreAccountDto dto, Map preInfo, String status){
		this.orderId = orderid;
		this.patid = card.getPatid();
		this.patName = card.getPat_name();
		this.idcard = card.getIdcard_no();
		this.cardtype = card.getType();
		this.cardno = card.getCardno();
		this.whetherded =dto.getWhetherDed();
		this.whetherset = dto.getWhetherSet();
		this.hospitalcardno = dto.getHospitalcardNo();
		this.password = dto.getPassword();
		this.regid = dto.getRegId();
		this.discountsamt = preInfo.get("discountsAmt")==null||"".equals(preInfo.get("discountsAmt").toString())?new BigDecimal("0.00"):new BigDecimal(preInfo.get("discountsAmt").toString());
		this.receiptno = preInfo.get("statementNo") == null?null:preInfo.get("statementNo").toString();
		this.accountpayment = preInfo.get("accountPayment")==null||"".equals(preInfo.get("accountPayment").toString())?new BigDecimal("0.00"):new BigDecimal(preInfo.get("accountPayment").toString());
		this.accountbalance = preInfo.get("accountBalance")==null||"".equals(preInfo.get("accountBalance").toString())?new BigDecimal("0.00"):new BigDecimal(preInfo.get("accountBalance").toString());
		this.regamt = preInfo.get("chargeTamt")==null||"".equals(preInfo.get("chargeTamt").toString())?new BigDecimal("0.00"):new BigDecimal(preInfo.get("chargeTamt").toString());
		this.receiptlist = dto.getReceiptList();
		this.chargetypelist = dto.getChargeTypeList();
		this.personamtlist = preInfo.get("personAmtList") == null?null:preInfo.get("personAmtList").toString();
		this.status = status;

	}
	public OrderSettlementEntity(String orderid, Card card, InpatientPrePaymentOrderDto dto,String status){
		this.orderId = orderid;
		this.patid = card.getPatid();
		this.patName = card.getPat_name();
		this.idcard = card.getIdcard_no();
		this.cardtype = card.getType();
		this.cardno = card.getCardno();
		this.receiptno = dto.getInterid();
		this.personamt = new BigDecimal(dto.getPreAmt());
		this.regamt = new BigDecimal(dto.getPreAmt());
		this.discountsamt = new BigDecimal("0.00");
		this.status = status;

	}
	public OrderSettlementEntity(){

	}

	/**{@linkplain #orderId}*/
	public OrderSettlementEntity setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
		
	/**{@linkplain #orderId}*/
	public String getOrderId() {
		return this.orderId;
	}

	/**{@linkplain #patid}*/
	public OrderSettlementEntity setPatid(String patid) {
		this.patid = patid;
		return this;
	}
		
	/**{@linkplain #patid}*/
	public String getPatid() {
		return this.patid;
	}

	/**{@linkplain #patName}*/
	public OrderSettlementEntity setPatName(String patName) {
		this.patName = patName;
		return this;
	}
		
	/**{@linkplain #patName}*/
	public String getPatName() {
		return this.patName;
	}

	/**{@linkplain #idcard}*/
	public OrderSettlementEntity setIdcard(String idcard) {
		this.idcard = idcard;
		return this;
	}
		
	/**{@linkplain #idcard}*/
	public String getIdcard() {
		return this.idcard;
	}

	/**{@linkplain #cardtype}*/
	public OrderSettlementEntity setCardtype(String cardtype) {
		this.cardtype = cardtype;
		return this;
	}
		
	/**{@linkplain #cardtype}*/
	public String getCardtype() {
		return this.cardtype;
	}

	/**{@linkplain #cardno}*/
	public OrderSettlementEntity setCardno(String cardno) {
		this.cardno = cardno;
		return this;
	}
		
	/**{@linkplain #cardno}*/
	public String getCardno() {
		return this.cardno;
	}

	/**{@linkplain #preregflag}*/
	public OrderSettlementEntity setPreregflag(String preregflag) {
		this.preregflag = preregflag;
		return this;
	}
		
	/**{@linkplain #preregflag}*/
	public String getPreregflag() {
		return this.preregflag;
	}

	/**{@linkplain #regtype}*/
	public OrderSettlementEntity setRegtype(String regtype) {
		this.regtype = regtype;
		return this;
	}
		
	/**{@linkplain #regtype}*/
	public String getRegtype() {
		return this.regtype;
	}

	/**{@linkplain #tscid}*/
	public OrderSettlementEntity setTscid(String tscid) {
		this.tscid = tscid;
		return this;
	}
		
	/**{@linkplain #tscid}*/
	public String getTscid() {
		return this.tscid;
	}

	/**{@linkplain #seqnum}*/
	public OrderSettlementEntity setSeqnum(String seqnum) {
		this.seqnum = seqnum;
		return this;
	}
		
	/**{@linkplain #seqnum}*/
	public String getSeqnum() {
		return this.seqnum;
	}

	/**{@linkplain #tscdate}*/
	public OrderSettlementEntity setTscdate(Date tscdate) {
		this.tscdate = tscdate;
		return this;
	}
		
	/**{@linkplain #tscdate}*/
	public Date getTscdate() {
		return this.tscdate;
	}

	/**{@linkplain #daysection}*/
	public OrderSettlementEntity setDaysection(String daysection) {
		this.daysection = daysection;
		return this;
	}
		
	/**{@linkplain #daysection}*/
	public String getDaysection() {
		return this.daysection;
	}

	/**{@linkplain #whetherded}*/
	public OrderSettlementEntity setWhetherded(String whetherded) {
		this.whetherded = whetherded;
		return this;
	}
		
	/**{@linkplain #whetherded}*/
	public String getWhetherded() {
		return this.whetherded;
	}

	/**{@linkplain #whetherset}*/
	public OrderSettlementEntity setWhetherset(String whetherset) {
		this.whetherset = whetherset;
		return this;
	}
		
	/**{@linkplain #whetherset}*/
	public String getWhetherset() {
		return this.whetherset;
	}

	/**{@linkplain #hospitalcardno}*/
	public OrderSettlementEntity setHospitalcardno(String hospitalcardno) {
		this.hospitalcardno = hospitalcardno;
		return this;
	}
		
	/**{@linkplain #hospitalcardno}*/
	public String getHospitalcardno() {
		return this.hospitalcardno;
	}

	/**{@linkplain #password}*/
	public OrderSettlementEntity setPassword(String password) {
		this.password = password;
		return this;
	}
		
	/**{@linkplain #password}*/
	public String getPassword() {
		return this.password;
	}

	/**{@linkplain #regid}*/
	public OrderSettlementEntity setRegid(String regid) {
		this.regid = regid;
		return this;
	}
		
	/**{@linkplain #regid}*/
	public String getRegid() {
		return this.regid;
	}

	/**{@linkplain #deptid}*/
	public OrderSettlementEntity setDeptid(String deptid) {
		this.deptid = deptid;
		return this;
	}
		
	/**{@linkplain #deptid}*/
	public String getDeptid() {
		return this.deptid;
	}

	/**{@linkplain #deptname}*/
	public OrderSettlementEntity setDeptname(String deptname) {
		this.deptname = deptname;
		return this;
	}
		
	/**{@linkplain #deptname}*/
	public String getDeptname() {
		return this.deptname;
	}

	/**{@linkplain #drid}*/
	public OrderSettlementEntity setDrid(String drid) {
		this.drid = drid;
		return this;
	}
		
	/**{@linkplain #drid}*/
	public String getDrid() {
		return this.drid;
	}

	/**{@linkplain #drname}*/
	public OrderSettlementEntity setDrname(String drname) {
		this.drname = drname;
		return this;
	}
		
	/**{@linkplain #drname}*/
	public String getDrname() {
		return this.drname;
	}

	/**{@linkplain #regfee}*/
	public OrderSettlementEntity setRegfee(BigDecimal regfee) {
		this.regfee = regfee;
		return this;
	}
		
	/**{@linkplain #regfee}*/
	public BigDecimal getRegfee() {
		return this.regfee;
	}

	/**{@linkplain #discountsamt}*/
	public OrderSettlementEntity setDiscountsamt(BigDecimal discountsamt) {
		this.discountsamt = discountsamt;
		return this;
	}
		
	/**{@linkplain #discountsamt}*/
	public BigDecimal getDiscountsamt() {
		return this.discountsamt;
	}

	/**{@linkplain #personamt}*/
	public OrderSettlementEntity setPersonamt(BigDecimal personamt) {
		this.personamt = personamt;
		return this;
	}
		
	/**{@linkplain #personamt}*/
	public BigDecimal getPersonamt() {
		return this.personamt;
	}

	/**{@linkplain #receiptno}*/
	public OrderSettlementEntity setReceiptno(String receiptno) {
		this.receiptno = receiptno;
		return this;
	}
		
	/**{@linkplain #receiptno}*/
	public String getReceiptno() {
		return this.receiptno;
	}

	/**{@linkplain #accountpayment}*/
	public OrderSettlementEntity setAccountpayment(BigDecimal accountpayment) {
		this.accountpayment = accountpayment;
		return this;
	}
		
	/**{@linkplain #accountpayment}*/
	public BigDecimal getAccountpayment() {
		return this.accountpayment;
	}

	/**{@linkplain #accountbalance}*/
	public OrderSettlementEntity setAccountbalance(BigDecimal accountbalance) {
		this.accountbalance = accountbalance;
		return this;
	}
		
	/**{@linkplain #accountbalance}*/
	public BigDecimal getAccountbalance() {
		return this.accountbalance;
	}

	/**{@linkplain #treatfee}*/
	public OrderSettlementEntity setTreatfee(BigDecimal treatfee) {
		this.treatfee = treatfee;
		return this;
	}
		
	/**{@linkplain #treatfee}*/
	public BigDecimal getTreatfee() {
		return this.treatfee;
	}

	/**{@linkplain #regamt}*/
	public OrderSettlementEntity setRegamt(BigDecimal regamt) {
		this.regamt = regamt;
		return this;
	}
		
	/**{@linkplain #regamt}*/
	public BigDecimal getRegamt() {
		return this.regamt;
	}

	/**{@linkplain #paymoney}*/
	public OrderSettlementEntity setPaymoney(BigDecimal paymoney) {
		this.paymoney = paymoney;
		return this;
	}
		
	/**{@linkplain #paymoney}*/
	public BigDecimal getPaymoney() {
		return this.paymoney;
	}

	/**{@linkplain #payway}*/
	public OrderSettlementEntity setPayway(String payway) {
		this.payway = payway;
		return this;
	}
		
	/**{@linkplain #payway}*/
	public String getPayway() {
		return this.payway;
	}

	/**{@linkplain #paychannel}*/
	public OrderSettlementEntity setPaychannel(String paychannel) {
		this.paychannel = paychannel;
		return this;
	}
		
	/**{@linkplain #paychannel}*/
	public String getPaychannel() {
		return this.paychannel;
	}

	/**{@linkplain #paytradeno}*/
	public OrderSettlementEntity setPaytradeno(String paytradeno) {
		this.paytradeno = paytradeno;
		return this;
	}
		
	/**{@linkplain #paytradeno}*/
	public String getPaytradeno() {
		return this.paytradeno;
	}

	/**{@linkplain #regreceipt}*/
	public OrderSettlementEntity setRegreceipt(String regreceipt) {
		this.regreceipt = regreceipt;
		return this;
	}
		
	/**{@linkplain #regreceipt}*/
	public String getRegreceipt() {
		return this.regreceipt;
	}

	/**{@linkplain #status}*/
	public OrderSettlementEntity setStatus(String status) {
		this.status = status;
		return this;
	}
		
	/**{@linkplain #status}*/
	public String getStatus() {
		return this.status;
	}

	/**{@linkplain #receiptlist}*/
	public OrderSettlementEntity setReceiptlist(String receiptlist) {
		this.receiptlist = receiptlist;
		return this;
	}
		
	/**{@linkplain #receiptlist}*/
	public String getReceiptlist() {
		return this.receiptlist;
	}

	/**{@linkplain #chargetypelist}*/
	public OrderSettlementEntity setChargetypelist(String chargetypelist) {
		this.chargetypelist = chargetypelist;
		return this;
	}
		
	/**{@linkplain #chargetypelist}*/
	public String getChargetypelist() {
		return this.chargetypelist;
	}

	/**{@linkplain #personamtlist}*/
	public OrderSettlementEntity setPersonamtlist(String personamtlist) {
		this.personamtlist = personamtlist;
		return this;
	}
		
	/**{@linkplain #personamtlist}*/
	public String getPersonamtlist() {
		return this.personamtlist;
	}

	/**{@linkplain #pyckjh}*/
	public OrderSettlementEntity setPyckjh(String pyckjh) {
		this.pyckjh = pyckjh;
		return this;
	}
		
	/**{@linkplain #pyckjh}*/
	public String getPyckjh() {
		return this.pyckjh;
	}

	/**{@linkplain #fyckjh}*/
	public OrderSettlementEntity setFyckjh(String fyckjh) {
		this.fyckjh = fyckjh;
		return this;
	}
		
	/**{@linkplain #fyckjh}*/
	public String getFyckjh() {
		return this.fyckjh;
	}

	/**{@linkplain #sjhinfo}*/
	public OrderSettlementEntity setSjhinfo(String sjhinfo) {
		this.sjhinfo = sjhinfo;
		return this;
	}
		
	/**{@linkplain #sjhinfo}*/
	public String getSjhinfo() {
		return this.sjhinfo;
	}

	/**{@linkplain #accountdate}*/
	public OrderSettlementEntity setAccountdate(Date accountdate) {
		this.accountdate = accountdate;
		return this;
	}
		
	/**{@linkplain #accountdate}*/
	public Date getAccountdate() {
		return this.accountdate;
	}

	/**{@linkplain #addTime}*/
	public OrderSettlementEntity setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}
		
	/**{@linkplain #addTime}*/
	public Date getAddTime() {
		return this.addTime;
	}

	/**{@linkplain #lastUpdate}*/
	public OrderSettlementEntity setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
		return this;
	}
		
	/**{@linkplain #lastUpdate}*/
	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("OrderSettlementEntity [");
  	    sb.append("orderId=");
  	    sb.append(orderId).append(",");
  	    sb.append(",patid=");
  	    sb.append(patid).append(",");
  	    sb.append(",patName=");
  	    sb.append(patName).append(",");
  	    sb.append(",idcard=");
  	    sb.append(idcard).append(",");
  	    sb.append(",cardtype=");
  	    sb.append(cardtype).append(",");
  	    sb.append(",cardno=");
  	    sb.append(cardno).append(",");
  	    sb.append(",preregflag=");
  	    sb.append(preregflag).append(",");
  	    sb.append(",regtype=");
  	    sb.append(regtype).append(",");
  	    sb.append(",tscid=");
  	    sb.append(tscid).append(",");
  	    sb.append(",seqnum=");
  	    sb.append(seqnum).append(",");
  	    sb.append(",tscdate=");
  	    sb.append(tscdate).append(",");
  	    sb.append(",daysection=");
  	    sb.append(daysection).append(",");
  	    sb.append(",whetherded=");
  	    sb.append(whetherded).append(",");
  	    sb.append(",whetherset=");
  	    sb.append(whetherset).append(",");
  	    sb.append(",hospitalcardno=");
  	    sb.append(hospitalcardno).append(",");
  	    sb.append(",password=");
  	    sb.append(password).append(",");
  	    sb.append(",regid=");
  	    sb.append(regid).append(",");
  	    sb.append(",deptid=");
  	    sb.append(deptid).append(",");
  	    sb.append(",deptname=");
  	    sb.append(deptname).append(",");
  	    sb.append(",drid=");
  	    sb.append(drid).append(",");
  	    sb.append(",drname=");
  	    sb.append(drname).append(",");
  	    sb.append(",regfee=");
  	    sb.append(regfee).append(",");
  	    sb.append(",discountsamt=");
  	    sb.append(discountsamt).append(",");
  	    sb.append(",personamt=");
  	    sb.append(personamt).append(",");
  	    sb.append(",receiptno=");
  	    sb.append(receiptno).append(",");
  	    sb.append(",accountpayment=");
  	    sb.append(accountpayment).append(",");
  	    sb.append(",accountbalance=");
  	    sb.append(accountbalance).append(",");
  	    sb.append(",treatfee=");
  	    sb.append(treatfee).append(",");
  	    sb.append(",regamt=");
  	    sb.append(regamt).append(",");
  	    sb.append(",paymoney=");
  	    sb.append(paymoney).append(",");
  	    sb.append(",payway=");
  	    sb.append(payway).append(",");
  	    sb.append(",paychannel=");
  	    sb.append(paychannel).append(",");
  	    sb.append(",paytradeno=");
  	    sb.append(paytradeno).append(",");
  	    sb.append(",regreceipt=");
  	    sb.append(regreceipt).append(",");
  	    sb.append(",status=");
  	    sb.append(status).append(",");
  	    sb.append(",receiptlist=");
  	    sb.append(receiptlist).append(",");
  	    sb.append(",chargetypelist=");
  	    sb.append(chargetypelist).append(",");
  	    sb.append(",personamtlist=");
  	    sb.append(personamtlist).append(",");
  	    sb.append(",pyckjh=");
  	    sb.append(pyckjh).append(",");
  	    sb.append(",fyckjh=");
  	    sb.append(fyckjh).append(",");
  	    sb.append(",sjhinfo=");
  	    sb.append(sjhinfo).append(",");
  	    sb.append(",accountdate=");
  	    sb.append(accountdate).append(",");
  	    sb.append(",addTime=");
  	    sb.append(addTime).append(",");
  	    sb.append(",lastUpdate=");
  	    sb.append(lastUpdate).append(",");
        sb.append(']');
        return sb.toString();
	}

}