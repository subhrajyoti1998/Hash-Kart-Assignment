package hashkart.user.entity;

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
@Table(name = "cart_details")
public class CartEntity {
	@Id
	@Column(name = "cid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer cartId;
	
	@Column(name = "user_id", unique = true)
	Integer userId;
	
	@Column(name = "product_id", unique = true)
	Integer productId;
	
	@Column(name = "quantity")
	Integer quantity;
}