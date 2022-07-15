package hashkart.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hashkart.user.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {
	
	public List<CartEntity> findByUserId(Integer userId);
	
	public CartEntity findByUserIdAndProductId(Integer userId, Integer productId);
}