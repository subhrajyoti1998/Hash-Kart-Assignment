package hashkart.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import hashkart.user.dto.ProductDTO;
import hashkart.user.entity.CartEntity;
import hashkart.user.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	CartRepository repo;
	
	public List<CartEntity> getCartDetailsForUser(Integer userId) {
		return repo.findByUserId(userId);
	}
	
	public void updateCart(CartEntity cartItem) {
		try {
			ProductDTO product = template.getForObject("lb://PRODUCT-SERVICE/products/"
								 + cartItem.getProductId(), ProductDTO.class, cartItem.getProductId());
			if (product != null) {
				CartEntity existingCart = repo.findByUserIdAndProductId(cartItem.getUserId(),
											cartItem.getProductId());
				if (existingCart != null) {
					Integer quantity = existingCart.getQuantity() + cartItem.getQuantity();
					existingCart.setQuantity(quantity);
					this.create(existingCart);
				}
				else {
					this.create(cartItem);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void create(CartEntity cartItem) {
		repo.save(cartItem);
	}
	
	public void delete(Integer cartId) {
		repo.deleteById(cartId);
	}
}