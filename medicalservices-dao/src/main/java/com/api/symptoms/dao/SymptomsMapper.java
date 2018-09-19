package com.api.symptoms.dao;

import java.util.List;
import com.api.symptoms.domain.SymptomsEntity;

/**
 * 智能导诊记录查询
 */
public interface SymptomsMapper {
	
	SymptomsEntity queryLimitOne(SymptomsEntity entity);

	/**条件查询*/
	List<SymptomsEntity> queryByCond(SymptomsEntity entity);
	
}