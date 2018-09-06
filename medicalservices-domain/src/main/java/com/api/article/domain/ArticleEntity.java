/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: fade </p>
 */
package com.api.article.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 咨询内容
 */
public class ArticleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	/**自增ID*/
	private Integer articleId;
	
	/**文章分类ID*/
	private Integer cateId;
	
	/**文章标题*/
	private String title;
	
	/**关键词、字*/
	private String keywords;
	
	/**文章摘自于*/
	private String fromto;
	
	/**外部链接地址*/
	private String link;
	
	/**文章摘要*/
	private String abs;
	
	/**文章内容*/
	private String content;
	
	/**排序的数字编号 大在前小在后*/
	private Integer sortOrder;
	
	/**文章状态： 4无效1审核中2通过审核3已删除（逻辑删除）*/
	private Integer status;
	
	/**点击次数*/
	private Integer count;
	
	/**发布时间*/
	private String publishTime;
	
	/**文章添加时间*/
	private String addTime;
	
	/**发布人*/
	private String addUsername;
	
	/**文章置顶状态0否1是|主要用于首页文章读取判断*/
	private Integer articleTop;
	
	/**附件地址*/
	private String fileUrl;
	

	/**{@linkplain #articleId}*/
	public ArticleEntity setArticleId(Integer articleId) {
		this.articleId = articleId;
		return this;
	}
		
	/**{@linkplain #articleId}*/
	public Integer getArticleId() {
		return this.articleId;
	}

	/**{@linkplain #cateId}*/
	public ArticleEntity setCateId(Integer cateId) {
		this.cateId = cateId;
		return this;
	}
		
	/**{@linkplain #cateId}*/
	public Integer getCateId() {
		return this.cateId;
	}

	/**{@linkplain #title}*/
	public ArticleEntity setTitle(String title) {
		this.title = title;
		return this;
	}
		
	/**{@linkplain #title}*/
	public String getTitle() {
		return this.title;
	}

	/**{@linkplain #keywords}*/
	public ArticleEntity setKeywords(String keywords) {
		this.keywords = keywords;
		return this;
	}
		
	/**{@linkplain #keywords}*/
	public String getKeywords() {
		return this.keywords;
	}

	/**{@linkplain #fromto}*/
	public ArticleEntity setFromto(String fromto) {
		this.fromto = fromto;
		return this;
	}
		
	/**{@linkplain #fromto}*/
	public String getFromto() {
		return this.fromto;
	}

	/**{@linkplain #link}*/
	public ArticleEntity setLink(String link) {
		this.link = link;
		return this;
	}
		
	/**{@linkplain #link}*/
	public String getLink() {
		return this.link;
	}

	public String getAbs() {
		return abs;
	}

	public void setAbs(String abs) {
		this.abs = abs;
	}

	/**{@linkplain #content}*/
	public ArticleEntity setContent(String content) {
		this.content = content;
		return this;
	}
		
	/**{@linkplain #content}*/
	public String getContent() {
		return this.content;
	}

	/**{@linkplain #sortOrder}*/
	public ArticleEntity setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}
		
	/**{@linkplain #sortOrder}*/
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	/**{@linkplain #status}*/
	public ArticleEntity setStatus(Integer status) {
		this.status = status;
		return this;
	}
		
	/**{@linkplain #status}*/
	public Integer getStatus() {
		return this.status;
	}

	/**{@linkplain #count}*/
	public ArticleEntity setCount(Integer count) {
		this.count = count;
		return this;
	}
		
	/**{@linkplain #count}*/
	public Integer getCount() {
		return this.count;
	}

	/**{@linkplain #publishTime}*/
	public ArticleEntity setPublishTime(String publishTime) {
		this.publishTime = publishTime;
		return this;
	}
		
	/**{@linkplain #publishTime}*/
	public String getPublishTime() {
		return this.publishTime;
	}

	/**{@linkplain #addTime}*/
	public ArticleEntity setAddTime(String addTime) {
		this.addTime = addTime;
		return this;
	}
		
	/**{@linkplain #addTime}*/
	public String getAddTime() {
		return this.addTime;
	}

	/**{@linkplain #addUsername}*/
	public ArticleEntity setAddUsername(String addUsername) {
		this.addUsername = addUsername;
		return this;
	}
		
	/**{@linkplain #addUsername}*/
	public String getAddUsername() {
		return this.addUsername;
	}

	/**{@linkplain #articleTop}*/
	public ArticleEntity setArticleTop(Integer articleTop) {
		this.articleTop = articleTop;
		return this;
	}
		
	/**{@linkplain #articleTop}*/
	public Integer getArticleTop() {
		return this.articleTop;
	}

	/**{@linkplain #fileUrl}*/
	public ArticleEntity setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
		return this;
	}
		
	/**{@linkplain #fileUrl}*/
	public String getFileUrl() {
		return this.fileUrl;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ArticleEntity [");
  	    sb.append("articleId=");
  	    sb.append(articleId).append(",");
  	    sb.append(",cateId=");
  	    sb.append(cateId).append(",");
  	    sb.append(",title=");
  	    sb.append(title).append(",");
  	    sb.append(",keywords=");
  	    sb.append(keywords).append(",");
  	    sb.append(",fromto=");
  	    sb.append(fromto).append(",");
  	    sb.append(",link=");
  	    sb.append(link).append(",");
  	    sb.append(",abs=");
  	    sb.append(abs).append(",");
  	    sb.append(",content=");
  	    sb.append(content).append(",");
  	    sb.append(",sortOrder=");
  	    sb.append(sortOrder).append(",");
  	    sb.append(",status=");
  	    sb.append(status).append(",");
  	    sb.append(",count=");
  	    sb.append(count).append(",");
  	    sb.append(",publishTime=");
  	    sb.append(publishTime).append(",");
  	    sb.append(",addTime=");
  	    sb.append(addTime).append(",");
  	    sb.append(",addUsername=");
  	    sb.append(addUsername).append(",");
  	    sb.append(",articleTop=");
  	    sb.append(articleTop).append(",");
  	    sb.append(",fileUrl=");
  	    sb.append(fileUrl).append(",");
        sb.append(']');
        return sb.toString();
	}

}