package hashkart.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	Integer productId;
	String productName;
	Double price;
	double rating;
	String category;
	String brandName;
	Integer availableQuantity;
	String productDescription;
}