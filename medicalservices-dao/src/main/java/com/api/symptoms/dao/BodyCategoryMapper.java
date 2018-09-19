package com.api.symptoms.dao;

import java.util.List;
import com.api.symptoms.domain.BodyCategoryEntity;

/**
 * 智能导诊部位查询
 */
public interface BodyCategoryMapper {

	
	BodyCategoryEntity queryLimitOne(BodyCategoryEntity entity);

	/**条件查询*/
	List<BodyCategoryEntity> queryByCond(BodyCategoryEntity entity);

	
}