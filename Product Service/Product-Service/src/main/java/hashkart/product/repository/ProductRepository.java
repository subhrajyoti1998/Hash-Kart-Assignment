package hashkart.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hashkart.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}