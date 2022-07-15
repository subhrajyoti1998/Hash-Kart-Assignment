package hashkart.user.service;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import hashkart.user.dto.PaymentDTO;
import hashkart.user.entity.OrderEntity;
import hashkart.user.exception.OrderNotFoundException;
import hashkart.user.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository repo;
	
	@Autowired
	RestTemplate template;
	
	public void addOrder(OrderEntity order) {
		PaymentDTO pay = new PaymentDTO(new Random().nextLong(), order.getUserId(), order.getOrderId(),
						 order.getProductId().intValue(), order.getQuantity(), order.getTotalOrderAmount(),
						 order.getTransactionStatus(), order.getOrderDate());
		try {
			template.put("http://PAYMENT-SERVICE/payment/addPayment?discountId="
						 + order.getDiscountAvailed(), pay, PaymentDTO.class);
			repo.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public OrderEntity getOrderByOrderId(Long orderId) {
		return repo.findById(orderId)
					.orElseThrow(() -> new OrderNotFoundException("Order with Order ID : "
											+ orderId + " Not Found"));
	}
	
	public List<OrderEntity> fetchAllOrdersForUser(Integer userId) {
		return repo.findByUserId(userId);
	}
}