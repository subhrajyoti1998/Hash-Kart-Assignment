package hashkart.product.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hashkart.product.entity.Product;
import hashkart.product.repository.ProductRepository;
import hashkart.product.service.ProductServiceImpl;

@SpringBootTest
public class ProductControllerTest {
	
	@InjectMocks
	ProductController controller;
	
	@Mock
	ProductServiceImpl service;
	
	@Mock
	ProductRepository repo;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testPostApiToCreateNewProduct() {
		Product newProduct = new Product(5, "Mung Dal", 150.00, 6, "Groceries", "Ashirbad", 300, "Mung Dal");
		ResponseEntity<String> response = controller.addNewProduct(newProduct);
		verify(service).addNewProduct(newProduct);
		
		assertEquals(response.getBody(), "Product Added Successfully.");
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}
	
	@Test
	public void testGetAllProductAPI() {
		/*List<Product> list = new LinkedList<Product>();
		list.add(new Product(1, "Rice", 80.00, 6, "Groceries", "Tata", 500, "Basamati Rice for Biriyani"));
		list.add(new Product(2, "Mustard Oil", 255.50, 7, "Groceries", "Emami", 250, "Mustard Oil for cooking"));
		list.add(new Product(3, "Knife", 250.00, 8, "Kitchen Accessories", "Godrej", 100, "Kitchen Knive for Cutting"));
		list.add(new Product(4, "Biscuit", 35.00, 7, "Groceries", "Priya", 2000, "Suger Free Buiscuit"));
		when(repo.findAll()).thenReturn(list);*/
		
		ResponseEntity<List<Product>> response = controller.getAllProducts();
		verify(service).getAllProducts();
		
		//assertEquals(response.getBody().size(), 4);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}