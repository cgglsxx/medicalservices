
package com.api.symptoms.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 症状实体类
 */
public class SymptomsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	/**自增ID*/
	private Integer symptomsId;
	
	/**身体部位*/
	private String cateId;
	
	/**症状名*/
	private String title;
	
	/**简介*/
	private String introduction;
	
	/**治疗*/
	private String treatment;
	
	/**诊断*/
	private String diagnosis;
	
	/**病因*/
	private String cause;
	
	/***/
	private String recommendDeptid;
	
	/**推荐科室名*/
	private String recommendDept;
	
	/**排序的数字编号 大在前小在后*/
	private String sortOrder;
	
	/**状态： 1 启用 2 禁用 3 已删除*/
	private String status;
	
	/**发布时间*/
	private String publishTime;
	
	/**文章添加时间*/
	private String addTime;
	
	/**发布人*/
	private String addUsername;
	

	/**{@linkplain #symptomsId}*/
	public SymptomsEntity setSymptomsId(Integer symptomsId) {
		this.symptomsId = symptomsId;
		return this;
	}
		
	/**{@linkplain #symptomsId}*/
	public Integer getSymptomsId() {
		return this.symptomsId;
	}

	/**{@linkplain #cateId}*/
	public SymptomsEntity setCateId(String cateId) {
		this.cateId = cateId;
		return this;
	}
		
	/**{@linkplain #cateId}*/
	public String getCateId() {
		return this.cateId;
	}

	/**{@linkplain #title}*/
	public SymptomsEntity setTitle(String title) {
		this.title = title;
		return this;
	}
		
	/**{@linkplain #title}*/
	public String getTitle() {
		return this.title;
	}

	/**{@linkplain #introduction}*/
	public SymptomsEntity setIntroduction(String introduction) {
		this.introduction = introduction;
		return this;
	}
		
	/**{@linkplain #introduction}*/
	public String getIntroduction() {
		return this.introduction;
	}

	/**{@linkplain #treatment}*/
	public SymptomsEntity setTreatment(String treatment) {
		this.treatment = treatment;
		return this;
	}
		
	/**{@linkplain #treatment}*/
	public String getTreatment() {
		return this.treatment;
	}

	/**{@linkplain #diagnosis}*/
	public SymptomsEntity setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
		return this;
	}
		
	/**{@linkplain #diagnosis}*/
	public String getDiagnosis() {
		return this.diagnosis;
	}

	/**{@linkplain #cause}*/
	public SymptomsEntity setCause(String cause) {
		this.cause = cause;
		return this;
	}
		
	/**{@linkplain #cause}*/
	public String getCause() {
		return this.cause;
	}

	/**{@linkplain #recommendDeptid}*/
	public SymptomsEntity setRecommendDeptid(String recommendDeptid) {
		this.recommendDeptid = recommendDeptid;
		return this;
	}
		
	/**{@linkplain #recommendDeptid}*/
	public String getRecommendDeptid() {
		return this.recommendDeptid;
	}

	/**{@linkplain #recommendDept}*/
	public SymptomsEntity setRecommendDept(String recommendDept) {
		this.recommendDept = recommendDept;
		return this;
	}
		
	/**{@linkplain #recommendDept}*/
	public String getRecommendDept() {
		return this.recommendDept;
	}

	/**{@linkplain #sortOrder}*/
	public SymptomsEntity setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}
		
	/**{@linkplain #sortOrder}*/
	public String getSortOrder() {
		return this.sortOrder;
	}

	/**{@linkplain #status}*/
	public SymptomsEntity setStatus(String status) {
		this.status = status;
		return this;
	}
		
	/**{@linkplain #status}*/
	public String getStatus() {
		return this.status;
	}

	/**{@linkplain #publishTime}*/
	public SymptomsEntity setPublishTime(String publishTime) {
		this.publishTime = publishTime;
		return this;
	}
		
	/**{@linkplain #publishTime}*/
	public String getPublishTime() {
		return this.publishTime;
	}

	/**{@linkplain #addTime}*/
	public SymptomsEntity setAddTime(String addTime) {
		this.addTime = addTime;
		return this;
	}
		
	/**{@linkplain #addTime}*/
	public String getAddTime() {
		return this.addTime;
	}

	/**{@linkplain #addUsername}*/
	public SymptomsEntity setAddUsername(String addUsername) {
		this.addUsername = addUsername;
		return this;
	}
		
	/**{@linkplain #addUsername}*/
	public String getAddUsername() {
		return this.addUsername;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("SymptomsEntity [");
  	    sb.append("symptomsId=");
  	    sb.append(symptomsId).append(",");
  	    sb.append(",cateId=");
  	    sb.append(cateId).append(",");
  	    sb.append(",title=");
  	    sb.append(title).append(",");
  	    sb.append(",introduction=");
  	    sb.append(introduction).append(",");
  	    sb.append(",treatment=");
  	    sb.append(treatment).append(",");
  	    sb.append(",diagnosis=");
  	    sb.append(diagnosis).append(",");
  	    sb.append(",cause=");
  	    sb.append(cause).append(",");
  	    sb.append(",recommendDeptid=");
  	    sb.append(recommendDeptid).append(",");
  	    sb.append(",recommendDept=");
  	    sb.append(recommendDept).append(",");
  	    sb.append(",sortOrder=");
  	    sb.append(sortOrder).append(",");
  	    sb.append(",status=");
  	    sb.append(status).append(",");
  	    sb.append(",publishTime=");
  	    sb.append(publishTime).append(",");
  	    sb.append(",addTime=");
  	    sb.append(addTime).append(",");
  	    sb.append(",addUsername=");
  	    sb.append(addUsername).append(",");
        sb.append(']');
        return sb.toString();
	}

}