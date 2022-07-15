package hashkart.payment.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hashkart.payment.entity.DiscountEntity;
import hashkart.payment.repository.DiscountRepository;

@Service
public class DiscountService {
	@Autowired
	DiscountRepository repo;
	
	public Double getDiscountAmountForPayment(String discountId, Double paymentAmount) {
		Double discountAvailed = 0.0D;
		Double maxAmount = 0.0D;
		String discountValue = "0.0";
		
		List<DiscountEntity> allDiscounts = repo.findAll();
		for (DiscountEntity discounts : allDiscounts) {
			if (discounts.getDiscountId().equalsIgnoreCase(discountId)
					 && ! discounts.getDiscountAvailable().equalsIgnoreCase("N")) {
				maxAmount = discounts.getMaxDiscountAmount() != null
							? discounts.getMaxDiscountAmount() : 0.0D;
				discountValue = discounts.getDiscountValue() != null
								? discounts.getDiscountValue() : "0.0";
				break;
			}
		}
		
		try {
			if (discountValue.endsWith("%")) {
				discountValue = discountValue.replace("%", "");
				discountAvailed = (paymentAmount * Double.parseDouble(discountValue)) / 100.00D;
			} else {
				discountAvailed = Double.parseDouble(discountValue);
			}
		} catch(NumberFormatException ex) {
			discountAvailed = 0.0D;
		}
		
		if (maxAmount != null && maxAmount > 0.0D) {
			discountAvailed = discountAvailed > maxAmount ? maxAmount : discountAvailed;
		}
		
		return discountAvailed;
	}
}