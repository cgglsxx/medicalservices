/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: fade </p>
 */
package com.api.registered.domain;

import com.api.card.domain.Card;
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
public class RegistrationDetailEntity implements Serializable {

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
	
	/**排班ID*/
	private String tscid;
	
	/**号源序号*/
	private String seqnum;
	
	/**挂号日期*/
	private Date tscdate;
	
	/**预约登记号*/
	private String yydjh;
	
	/**科室代码（科室编号或ID，无特殊字符）*/
	private String deptid;
	
	/**科室名称*/
	private String deptname;
	
	/**医生代码*/
	private String drid;
	
	/**医生姓名*/
	private String drname;
	
	/**医生职称*/
	private String drtitle;
	
	/**挂号费*/
	private BigDecimal regfee;
	
	/**诊疗费*/
	private BigDecimal treatfee;
	
	/**挂号总金额*/
	private BigDecimal regamt;
	
	/**1 已登记 2 取消登记*/
	private String status;
	
	/**添加时间*/
	private Date addTime;
	
	/**最后更新时间*/
	private Date lastUpdate;
	
	public RegistrationDetailEntity(String orderId,Card card, RegOrderSaveDto dto, Map param,String status){
		this.setOrderId(orderId);
		this.setPatid(card.getPatid());
		this.setPatName(card.getPat_name());
		this.setIdcard(card.getIdcard_no());
		this.setCardno(card.getCardno());
		this.setTscid(dto.getTscid());
		this.setSeqnum(param.get("seqnum")==null?null:param.get("seqnum").toString());
		this.setTscdate(DateUtil.formatStringToDate(dto.getTscdate(),DateUtil.FORMAT_DEFAULT));
		this.setYydjh(param.get("yydjh").toString());
		this.setDeptid(param.get("deptId")==null?"":param.get("deptId").toString());
		this.setDeptname(param.get("deptName")==null?"":param.get("deptName").toString());
		this.setDrid(param.get("drId")==null?"":param.get("drId").toString());
		this.setDrname(param.get("drName")==null?"":param.get("drName").toString());
		this.setDrtitle(param.get("drTitle")==null?"":param.get("drTitle").toString());
		this.setRegfee(param.get("regFee")==null||"".equals(param.get("regFee"))?new BigDecimal("0.00"):new BigDecimal(param.get("regFee").toString()));
		this.setTreatfee(param.get("treatFee")==null||"".equals(param.get("treatFee"))?new BigDecimal("0.00"):new BigDecimal(param.get("treatFee").toString()));
		this.setRegamt(param.get("regAmt")==null||"".equals(param.get("regAmt"))?new BigDecimal("0.00"):new BigDecimal(param.get("regAmt").toString()));
		this.setStatus(status);
	}
	public RegistrationDetailEntity(){

	}
	/**{@linkplain #orderId}*/
	public RegistrationDetailEntity setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
		
	/**{@linkplain #orderId}*/
	public String getOrderId() {
		return this.orderId;
	}

	/**{@linkplain #patid}*/
	public RegistrationDetailEntity setPatid(String patid) {
		this.patid = patid;
		return this;
	}
		
	/**{@linkplain #patid}*/
	public String getPatid() {
		return this.patid;
	}

	/**{@linkplain #patName}*/
	public RegistrationDetailEntity setPatName(String patName) {
		this.patName = patName;
		return this;
	}
		
	/**{@linkplain #patName}*/
	public String getPatName() {
		return this.patName;
	}

	/**{@linkplain #idcard}*/
	public RegistrationDetailEntity setIdcard(String idcard) {
		this.idcard = idcard;
		return this;
	}
		
	/**{@linkplain #idcard}*/
	public String getIdcard() {
		return this.idcard;
	}

	/**{@linkplain #cardno}*/
	public RegistrationDetailEntity setCardno(String cardno) {
		this.cardno = cardno;
		return this;
	}
		
	/**{@linkplain #cardno}*/
	public String getCardno() {
		return this.cardno;
	}

	/**{@linkplain #tscid}*/
	public RegistrationDetailEntity setTscid(String tscid) {
		this.tscid = tscid;
		return this;
	}
		
	/**{@linkplain #tscid}*/
	public String getTscid() {
		return this.tscid;
	}

	/**{@linkplain #seqnum}*/
	public RegistrationDetailEntity setSeqnum(String seqnum) {
		this.seqnum = seqnum;
		return this;
	}
		
	/**{@linkplain #seqnum}*/
	public String getSeqnum() {
		return this.seqnum;
	}

	/**{@linkplain #tscdate}*/
	public RegistrationDetailEntity setTscdate(Date tscdate) {
		this.tscdate = tscdate;
		return this;
	}
		
	/**{@linkplain #tscdate}*/
	public Date getTscdate() {
		return this.tscdate;
	}

	/**{@linkplain #yydjh}*/
	public RegistrationDetailEntity setYydjh(String yydjh) {
		this.yydjh = yydjh;
		return this;
	}
		
	/**{@linkplain #yydjh}*/
	public String getYydjh() {
		return this.yydjh;
	}

	/**{@linkplain #deptid}*/
	public RegistrationDetailEntity setDeptid(String deptid) {
		this.deptid = deptid;
		return this;
	}
		
	/**{@linkplain #deptid}*/
	public String getDeptid() {
		return this.deptid;
	}

	/**{@linkplain #deptname}*/
	public RegistrationDetailEntity setDeptname(String deptname) {
		this.deptname = deptname;
		return this;
	}
		
	/**{@linkplain #deptname}*/
	public String getDeptname() {
		return this.deptname;
	}

	/**{@linkplain #drid}*/
	public RegistrationDetailEntity setDrid(String drid) {
		this.drid = drid;
		return this;
	}
		
	/**{@linkplain #drid}*/
	public String getDrid() {
		return this.drid;
	}

	/**{@linkplain #drname}*/
	public RegistrationDetailEntity setDrname(String drname) {
		this.drname = drname;
		return this;
	}
		
	/**{@linkplain #drname}*/
	public String getDrname() {
		return this.drname;
	}

	/**{@linkplain #drtitle}*/
	public RegistrationDetailEntity setDrtitle(String drtitle) {
		this.drtitle = drtitle;
		return this;
	}
		
	/**{@linkplain #drtitle}*/
	public String getDrtitle() {
		return this.drtitle;
	}

	/**{@linkplain #regfee}*/
	public RegistrationDetailEntity setRegfee(BigDecimal regfee) {
		this.regfee = regfee;
		return this;
	}
		
	/**{@linkplain #regfee}*/
	public BigDecimal getRegfee() {
		return this.regfee;
	}

	/**{@linkplain #treatfee}*/
	public RegistrationDetailEntity setTreatfee(BigDecimal treatfee) {
		this.treatfee = treatfee;
		return this;
	}
		
	/**{@linkplain #treatfee}*/
	public BigDecimal getTreatfee() {
		return this.treatfee;
	}

	/**{@linkplain #regamt}*/
	public RegistrationDetailEntity setRegamt(BigDecimal regamt) {
		this.regamt = regamt;
		return this;
	}
		
	/**{@linkplain #regamt}*/
	public BigDecimal getRegamt() {
		return this.regamt;
	}

	/**{@linkplain #status}*/
	public RegistrationDetailEntity setStatus(String status) {
		this.status = status;
		return this;
	}
		
	/**{@linkplain #status}*/
	public String getStatus() {
		return this.status;
	}

	/**{@linkplain #addTime}*/
	public RegistrationDetailEntity setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}
		
	/**{@linkplain #addTime}*/
	public Date getAddTime() {
		return this.addTime;
	}

	/**{@linkplain #lastUpdate}*/
	public RegistrationDetailEntity setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
		return this;
	}
		
	/**{@linkplain #lastUpdate}*/
	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RegistrationDetailEntity [");
  	    sb.append("orderId=");
  	    sb.append(orderId).append(",");
  	    sb.append(",patid=");
  	    sb.append(patid).append(",");
  	    sb.append(",patName=");
  	    sb.append(patName).append(",");
  	    sb.append(",idcard=");
  	    sb.append(idcard).append(",");
  	    sb.append(",cardno=");
  	    sb.append(cardno).append(",");
  	    sb.append(",tscid=");
  	    sb.append(tscid).append(",");
  	    sb.append(",seqnum=");
  	    sb.append(seqnum).append(",");
  	    sb.append(",tscdate=");
  	    sb.append(tscdate).append(",");
  	    sb.append(",yydjh=");
  	    sb.append(yydjh).append(",");
  	    sb.append(",deptid=");
  	    sb.append(deptid).append(",");
  	    sb.append(",deptname=");
  	    sb.append(deptname).append(",");
  	    sb.append(",drid=");
  	    sb.append(drid).append(",");
  	    sb.append(",drname=");
  	    sb.append(drname).append(",");
  	    sb.append(",drtitle=");
  	    sb.append(drtitle).append(",");
  	    sb.append(",regfee=");
  	    sb.append(regfee).append(",");
  	    sb.append(",treatfee=");
  	    sb.append(treatfee).append(",");
  	    sb.append(",regamt=");
  	    sb.append(regamt).append(",");
  	    sb.append(",status=");
  	    sb.append(status).append(",");
  	    sb.append(",addTime=");
  	    sb.append(addTime).append(",");
  	    sb.append(",lastUpdate=");
  	    sb.append(lastUpdate).append(",");
        sb.append(']');
        return sb.toString();
	}

}