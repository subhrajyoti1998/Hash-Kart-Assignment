package hashkart.user.entity;

import java.util.Date;
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
@Table(name = "order_details")
public class OrderEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long orderId;
	
	@Column(name = "user_id")
	Integer userId;
	
	@Column(name = "product_id")
	Long productId;
	
	@Column(name = "quantity")
	Integer quantity;
	
	@Column(name = "total_order_amount")
	Double totalOrderAmount;
	
	@Column(name = "order_date")
	Date orderDate;
	
	@Column(name = "transaction_status")
	String transactionStatus;
	
	@Column(name = "discounts_availed")
	String discountAvailed;
}