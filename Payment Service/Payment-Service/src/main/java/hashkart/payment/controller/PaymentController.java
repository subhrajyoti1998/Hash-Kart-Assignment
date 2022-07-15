package hashkart.payment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hashkart.payment.entity.PaymentEntity;
import hashkart.payment.service.DiscountService;
import hashkart.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService service;
	
	@Autowired
	DiscountService discountService;
	
	@GetMapping("/{paymentId}")
	public ResponseEntity<Object> getPaymentDetailsById(@PathVariable Long paymentId) {
		PaymentEntity paymentDtls = service.getPaymentDetailsByPaymentId(paymentId);
		if (paymentDtls != null) {
			return new ResponseEntity<>(paymentDtls, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Payment found with Payment Id - " + paymentId
										 , HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<PaymentEntity>> getPaymentDetailsById(@PathVariable Integer userId) {
		return new ResponseEntity<>(service.getPaymentsByUserId(userId), HttpStatus.OK);
	}
	
	@PutMapping(value = "/addPayment", params = "discountId")
	public ResponseEntity<String> addPayment(@RequestBody PaymentEntity payment,
											 @RequestParam(value = "discountId",
											 				required = false,
											 				defaultValue = "0.0") String discountId) {
		if ( ! payment.getPaymentStatus().equalsIgnoreCase("N")) {
			if (discountId != null) {
				Double discount = discountService.getDiscountAmountForPayment(discountId, payment.getPaymentAmount());
				Double discountedAmount = payment.getPaymentAmount() - discount;
				payment.setPaymentAmount(discountedAmount > 0.0D ? discountedAmount : 0.0D);
			}
			
			service.doPayment(payment);
			return new ResponseEntity<>("Payment done Successfully.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Payment is unsuccessful.", HttpStatus.NOT_FOUND);
	}
}
