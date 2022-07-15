package hashkart.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hashkart.user.entity.CartEntity;
import hashkart.user.entity.OrderEntity;
import hashkart.user.entity.UserEntity;
import hashkart.user.service.CartService;
import hashkart.user.service.OrderService;
import hashkart.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody UserEntity newUser) {
		userService.createUser(newUser);
		return new ResponseEntity<String>("User created Successfully.", HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserEntity>> getAllUser() {
		return new ResponseEntity<List<UserEntity>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserEntity> getUserByUserId(@PathVariable Integer userId) {
		return new ResponseEntity<UserEntity>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable Integer userId,
											 @RequestBody UserEntity existingUser) {
		String response = userService.updateUserByUserId(userId, existingUser);
		return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{userId}/cart")
	public ResponseEntity<List<CartEntity>> getCartDetailsForUser(@PathVariable Integer userId) {
		return new ResponseEntity<List<CartEntity>>(cartService
								 .getCartDetailsForUser(userId), HttpStatus.OK);
	}
	
	@PutMapping("/{userId}/updateCart/{action}")
	public ResponseEntity<String> addOrUpdateCart(@PathVariable Integer userId,
													@PathVariable String action,
													@RequestBody CartEntity cartItem) {
		if ("Update".equalsIgnoreCase(action) || "Add".equalsIgnoreCase(action)) {
			cartService.updateCart(cartItem);
			return new ResponseEntity<String>("Cart " + action + "ed Successfully.", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Bad Request.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteCart/{cartId}")
	public ResponseEntity<String> deleteCart(@PathVariable Integer cartId) {
		cartService.delete(cartId);
		return new ResponseEntity<String>("Cart deleted Successfully.", HttpStatus.OK);
	}
	
	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<OrderEntity>> getOrdersForUser(@PathVariable Integer userId) {
		return new ResponseEntity<List<OrderEntity>>(orderService.fetchAllOrdersForUser(userId),
													 HttpStatus.OK);
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<OrderEntity> getSpecificOrderByOrderId(@PathVariable Long orderId) {
		return new ResponseEntity<OrderEntity>(orderService.getOrderByOrderId(orderId),
												HttpStatus.OK);
	}
	
	@PutMapping("/addOrders")
	public ResponseEntity<String> addOrUpdateOrder(@RequestBody OrderEntity entity) {
		orderService.addOrder(entity);
		return new ResponseEntity<String>("Order added Successfully.", HttpStatus.OK);
	}
}