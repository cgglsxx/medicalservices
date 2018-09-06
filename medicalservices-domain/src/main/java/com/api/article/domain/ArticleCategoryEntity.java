/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: fade </p>
 */
package com.api.article.domain;

import java.io.Serializable;

/**
 * 文章资讯、导购资讯 分类
 */
public class ArticleCategoryEntity implements Serializable {

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
	
	/**文章类型： 1.新闻公告  2. 招聘 3. 服务支持 */
	private Integer cateType;
	

	/**{@linkplain #cateId}*/
	public ArticleCategoryEntity setCateId(Integer cateId) {
		this.cateId = cateId;
		return this;
	}
		
	/**{@linkplain #cateId}*/
	public Integer getCateId() {
		return this.cateId;
	}

	/**{@linkplain #cateName}*/
	public ArticleCategoryEntity setCateName(String cateName) {
		this.cateName = cateName;
		return this;
	}
		
	/**{@linkplain #cateName}*/
	public String getCateName() {
		return this.cateName;
	}

	/**{@linkplain #parentId}*/
	public ArticleCategoryEntity setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}
		
	/**{@linkplain #parentId}*/
	public String getParentId() {
		return this.parentId;
	}

	/**{@linkplain #status}*/
	public ArticleCategoryEntity setStatus(Integer status) {
		this.status = status;
		return this;
	}
		
	/**{@linkplain #status}*/
	public Integer getStatus() {
		return this.status;
	}

	/**{@linkplain #sortOrder}*/
	public ArticleCategoryEntity setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}
		
	/**{@linkplain #sortOrder}*/
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	/**{@linkplain #cateType}*/
	public ArticleCategoryEntity setCateType(Integer cateType) {
		this.cateType = cateType;
		return this;
	}
		
	/**{@linkplain #cateType}*/
	public Integer getCateType() {
		return this.cateType;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ArticleCategoryEntity [");
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