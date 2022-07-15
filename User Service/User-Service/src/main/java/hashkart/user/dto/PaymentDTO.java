package hashkart.user.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
	Long paymentId;
	Integer userId;
	Long orderId;
	Integer productId;
	Integer quantity;
	Double paymentAmount;
	String paymentStatus;
	Date paymentDate;
}