package hashkart.user.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hashkart.user.entity.UserEntity;
import hashkart.user.exception.UserNotFoundException;
import hashkart.user.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repo;
	
	public void createUser(UserEntity newUser) {
		repo.save(newUser);
	}
	
	public UserEntity getUserById(Integer userId) {
		return repo.findById(userId)
					.orElseThrow(() -> new UserNotFoundException("User with User Id - "
																 + userId + " is not Found"));
	}
	
	public List<UserEntity> getAllUsers() {
		return repo.findAll();
	}
	
	public String updateUserByUserId(Integer userId, UserEntity toUpdate) {
		String response;
		Optional<UserEntity> user = repo.findById(userId);
		if (user.isPresent()) {
			if (!user.get().equals(toUpdate)) {
				toUpdate = new UserEntity(userId, toUpdate.getUserName(), toUpdate.getPassword(),
						   toUpdate.getEmail(), toUpdate.getPhoneNo(), user.get().getCartId(),
						   toUpdate.getBankId());
				repo.save(toUpdate);
				response = "User with User Id -> " + userId + " has been Updated successfully.";
			} else {
				response = "There is no Update for User with User Id -> " + userId + ".";
			}
		} else {
			response = "User with User Id -> " + userId + " has not been found.";
		}
		return response;
	}
}