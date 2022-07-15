package hashkart.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")

public class Product {
	@Id
	@Column(name = "pid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer productId;
	
	@Column(name = "name")
	String productName;
	
	@Column(name = "price")
	Double price;
	
	@Column(name = "rating")
	double rating;
	
	@Column(name = "category")
	String category;
	
	@Column(name = "brand")
	String brandName;
	
	@Column(name = "available_quantity")
	Integer availableQuantity;
	
	@Column(name = "description")
	String productDescription;
}