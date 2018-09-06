
package com.api.article.dao;

import com.api.article.domain.ArticleEntity;

import java.util.List;

/**
 *
 */
public interface ArticleMapper {

	
	ArticleEntity queryLimitOne(ArticleEntity entity);


	/**条件查询*/
	List<ArticleEntity> queryByCond(ArticleEntity entity);

	
}