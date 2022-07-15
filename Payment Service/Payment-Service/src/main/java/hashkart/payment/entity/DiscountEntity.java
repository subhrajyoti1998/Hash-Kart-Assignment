package hashkart.payment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discount")
public class DiscountEntity {
	@Id
	@Column(name = "did")
	String discountId;
	
	@Column(name = "discount_value")
	String discountValue;
	
	@Column(name = "max_discount_amount")
	Double maxDiscountAmount;
	
	@Column(name = "discount_available")
	String discountAvailable;
}