package hashkart.product.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hashkart.product.entity.Product;
import hashkart.product.exception.ExceptionHandler;
import hashkart.product.repository.ProductRepository;

@Service
public class ProductServiceImpl {
	@Autowired
	ProductRepository repo;
	
	public void addNewProduct(Product newProduct) {
		repo.save(newProduct);
	}
	
	public List<Product> getAllProducts() {
		return repo.findAll();
	}
	
	public Product getProductById(Integer productId) {
		System.out.println("\n Inside get Product service. \n");
		return repo.findById(productId)
					.orElseThrow(() -> new ExceptionHandler("Product with Product Id - "
															 + productId + " is not Found"));
	}
	
	public void updateProduct(Product existingProduct) {
		repo.save(existingProduct);
	}
	
	public void deleteProduct(Integer productId) {
		repo.deleteById(productId);
	}
}