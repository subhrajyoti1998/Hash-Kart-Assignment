package hashkart.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hashkart.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
}