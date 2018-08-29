/**
 * 
 */
package com.api.registered.dao;

import com.api.registered.domain.OrderEntity;

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
public interface OrderMapper {

	/**可选插入返回主键*/
	void insertSelective(OrderEntity entity);

	/**批量插入返回影响记录数*/
//	int insertRecords(@Param("records") List<OrderEntity> records);
	
	OrderEntity queryLimitOne(OrderEntity entity);

	/**批量主键查询*/
//	List<OrderEntity> queryByOrderIds(@Param("keys") List<String> orderIds);

	/**条件查询*/
	List<OrderEntity> queryByCond(OrderEntity entity);

	/**主键查询*/
//	OrderEntity queryByOrderId (@Param("orderId") String orderId);
	
	/**主键更新*/
	int updateOrderByOrderId(OrderEntity entity);
	
	/**主键删除*/
//	int deleteOrderByOrderId (@Param("orderId") String orderId);
	
}