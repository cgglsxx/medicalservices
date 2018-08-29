/**
 * 
 */
package com.api.registered.dao;

import com.api.registered.domain.OrderSettlementEntity;

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
public interface OrderSettlementMapper {

	/**可选插入返回主键*/
	void insertSelective(OrderSettlementEntity entity);
	
	/**批量插入返回影响记录数*/
//	int insertRecords(@Param("records") List<OrderSettlementEntity> records);
	
	OrderSettlementEntity queryLimitOne(OrderSettlementEntity entity);
	
	/**批量主键查询*/
//	List<OrderSettlementEntity> queryByOrderIds(@Param("keys") List<String> orderIds);

	/**条件查询*/
	List<OrderSettlementEntity> queryByCond(OrderSettlementEntity entity);
	
	/**主键查询*/
//	OrderSettlementEntity queryByOrderId (@Param("orderId") String orderId);
	
	/**主键更新*/
	int updateOrderSettlementByOrderId(OrderSettlementEntity entity);
	
	/**主键删除*/
//	int deleteOrderSettlementByOrderId (@Param("orderId") String orderId);
	
}