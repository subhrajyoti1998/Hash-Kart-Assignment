package hashkart.user.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import hashkart.user.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	
	List<OrderEntity> findByUserId(Integer userId);
	
	/*@Query(value = "select * from order_details o where o.user_id = :userId and o.product_id = :productId",
			nativeQuery = true)
	List<OrderEntity> fetchOrder(Integer userId, Integer productId);*/
}