package hashkart.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import hashkart.product.entity.Product;
import hashkart.product.exception.ExceptionHandler;
import hashkart.product.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTest {
	
	@InjectMocks
	private ProductServiceImpl service;
	
	@Mock
	private ProductRepository repo;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllProductsTest() {
		List<Product> list = new LinkedList<Product>();
		list.add(new Product(1, "Rice", 80.00, 6, "Groceries", "Tata", 500, "Basamati Rice for Biriyani"));
		list.add(new Product(2, "Mustard Oil", 255.50, 7, "Groceries", "Emami", 250, "Mustard Oil for cooking"));
		list.add(new Product(3, "Knive", 250.00, 8, "Kitchen Accessories", "Godrej", 100, "Kitchen Knive for Cutting"));
		list.add(new Product(4, "Biscuit", 35.00, 7, "Groceries", "Priya", 2000, "Suger Free Buiscuit"));
		
		when(repo.findAll()).thenReturn(list);
		assertEquals(service.getAllProducts().size(), 4);
	}
	
	@Test
	public void getProductByProductIdTest() {
		Optional<Product> optional = Optional.of(new Product(1, "Rice", 80.00, 6, "Groceries",
															 "Tata", 500, "Basamati Rice for Biriyani"));
		when(repo.findById(1)).thenReturn(optional);
		assertEquals(service.getProductById(1).getProductName(), "Rice");
	}
	
	@Test
	public void getProductByProductIdExceptionTest() {
		boolean exceptionThrown = false;
		Optional<Product> optional = Optional.ofNullable(null);
		when(repo.findById(7)).thenReturn(optional);
		try {
			service.getProductById(7);
		} catch(ExceptionHandler e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	@Test
	public void addNewProductTest() {
		Product newProduct = new Product(8, "Masoor Dal", 180.00, 16, "Groceries", "Emami", 250, "Masoor Dal");
		service.addNewProduct(newProduct);
		verify(repo).save(newProduct);
	}
	
	@Test
	public void updateProductTest() {
		Product updateProduct = new Product(5, "Mung Dal", 150.00, 6, "Groceries", "Ashirbad", 300, "Mung Dal");
		service.updateProduct(updateProduct);
		verify(repo).save(updateProduct);
	}
}