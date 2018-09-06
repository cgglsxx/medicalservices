
package com.api.article.dao;

import com.api.article.domain.ArticleCategoryEntity;

import java.util.List;

/**
 * 分类
 */
public interface ArticleCategoryMapper {

	
	ArticleCategoryEntity queryLimitOne(ArticleCategoryEntity entity);


	/**条件查询*/
	List<ArticleCategoryEntity> queryByCond(ArticleCategoryEntity entity);

	
}