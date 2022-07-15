package hashkart.payment.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hashkart.payment.entity.PaymentEntity;
import hashkart.payment.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository repo;
	
	public PaymentEntity getPaymentDetailsByPaymentId(Long paymentId) {
		return repo.findById(paymentId).orElse(null);
	}
	
	public List<PaymentEntity> getPaymentsByUserId(Integer userId) {
		return repo.findByUserId(userId);
	}
	
	public void doPayment(PaymentEntity payment) {
		repo.save(payment);
	}
}