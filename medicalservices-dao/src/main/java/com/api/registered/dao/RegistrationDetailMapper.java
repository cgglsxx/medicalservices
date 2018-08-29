/**
 * 
 */
package com.api.registered.dao;

import com.api.registered.domain.RegistrationDetailEntity;

import java.util.List;

//import org.apache.ibatis.annotations.Param;

/**
 * Description:  Dao<br/>
 *
 * @author cjh
 * @version 1.0
 * @date: 2018-08-28 14:52:17
 * @since JDK 1.8
 */
public interface RegistrationDetailMapper {

	/**可选插入返回主键*/
	void insertSelective(RegistrationDetailEntity entity);
	
	/**批量插入返回影响记录数*/
//	int insertRecords(@Param("records") List<RegistrationDetailEntity> records);
	
	RegistrationDetailEntity queryLimitOne(RegistrationDetailEntity entity);
	
	/**批量主键查询*/
//	List<RegistrationDetailEntity> queryByOrderIds(@Param("keys") List<String> orderIds);

	/**条件查询*/
	List<RegistrationDetailEntity> queryByCond(RegistrationDetailEntity entity);
	
	/**主键查询*/
//	RegistrationDetailEntity queryByOrderId (@Param("orderId") String orderId);
	
	/**主键更新*/
	int updateRegistrationDetailByOrderId(RegistrationDetailEntity entity);
	
	/**主键删除*/
//	int deleteRegistrationDetailByOrderId (@Param("orderId") String orderId);
	
}