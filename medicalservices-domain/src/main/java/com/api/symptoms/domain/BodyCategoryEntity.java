/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: fade </p>
 */
package com.api.symptoms.domain;

import java.io.Serializable;

/**
 * 身体部位实体类
 */
public class BodyCategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	/**自编号*/
	private Integer cateId;
	
	/**分类名*/
	private String cateName;
	
	/**父级ID 为0时为顶级*/
	private String parentId;
	
	/**状态 1--启用，0--注销*/
	private Integer status;
	
	/**排序*/
	private Integer sortOrder;
	
	/**类型： 1.特殊部位  2. 非特殊部位*/
	private String cateType;
	

	/**{@linkplain #cateId}*/
	public BodyCategoryEntity setCateId(Integer cateId) {
		this.cateId = cateId;
		return this;
	}
		
	/**{@linkplain #cateId}*/
	public Integer getCateId() {
		return this.cateId;
	}

	/**{@linkplain #cateName}*/
	public BodyCategoryEntity setCateName(String cateName) {
		this.cateName = cateName;
		return this;
	}
		
	/**{@linkplain #cateName}*/
	public String getCateName() {
		return this.cateName;
	}

	/**{@linkplain #parentId}*/
	public BodyCategoryEntity setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}
		
	/**{@linkplain #parentId}*/
	public String getParentId() {
		return this.parentId;
	}

	/**{@linkplain #status}*/
	public BodyCategoryEntity setStatus(Integer status) {
		this.status = status;
		return this;
	}
		
	/**{@linkplain #status}*/
	public Integer getStatus() {
		return this.status;
	}

	/**{@linkplain #sortOrder}*/
	public BodyCategoryEntity setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}
		
	/**{@linkplain #sortOrder}*/
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	/**{@linkplain #cateType}*/
	public BodyCategoryEntity setCateType(String cateType) {
		this.cateType = cateType;
		return this;
	}
		
	/**{@linkplain #cateType}*/
	public String getCateType() {
		return this.cateType;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("BodyCategoryEntity [");
  	    sb.append("cateId=");
  	    sb.append(cateId).append(",");
  	    sb.append(",cateName=");
  	    sb.append(cateName).append(",");
  	    sb.append(",parentId=");
  	    sb.append(parentId).append(",");
  	    sb.append(",status=");
  	    sb.append(status).append(",");
  	    sb.append(",sortOrder=");
  	    sb.append(sortOrder).append(",");
  	    sb.append(",cateType=");
  	    sb.append(cateType).append(",");
        sb.append(']');
        return sb.toString();
	}

}