package hashkart.payment.entity;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_details")
public class PaymentEntity {
	@Id
	@Column(name = "payid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long paymentId;
	
	@Column(name = "uid")
	Integer userId;
	
	@Column(name = "oid")
	Long orderId;
	
	@Column(name = "product_id")
	Integer productId;
	
	@Column(name = "quantity")
	Integer quantity;
	
	@Column(name = "payment_amount")
	Double paymentAmount;
	
	@Column(name = "payment_status")
	String paymentStatus;
	
	@Column(name = "payment_date")
	Date paymentDate;
}