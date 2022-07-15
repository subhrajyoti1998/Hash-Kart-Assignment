package hashkart.product.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hashkart.product.entity.Product;
import hashkart.product.service.ProductServiceImpl;

@RestController
@RequestMapping("products")
public class ProductController {
	@Autowired
	ProductServiceImpl service;
	
	@PostMapping("/add")
	public ResponseEntity<String> addNewProduct(@RequestBody Product newProduct) {
		service.addNewProduct(newProduct);
		return new ResponseEntity<String>("Product Added Successfully.", HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getAllProducts(@PathVariable Integer productId) {
		System.out.println("\n Inside get Product by Product ID API. \n");
		return new ResponseEntity<Product>(service.getProductById(productId), HttpStatus.OK);
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<String> updateProduct(@PathVariable Integer productId,
												@RequestBody Product product) {
		Product toUpdate = service.getProductById(productId);
		if (toUpdate != null && !toUpdate.equals(product)) {
			service.updateProduct(product);
		}
		return new ResponseEntity<String>("Product with Prouct Id - " + productId + " updated Successfully.",
											HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
		Product product = service.getProductById(productId);
		if (product != null) {
			service.deleteProduct(productId);
		}
		return new ResponseEntity<>("Product with Prouct Id - " + productId + " deleted Successfully.",
									HttpStatus.ACCEPTED);
	}
}