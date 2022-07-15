package hashkart.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import hashkart.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_details")
public class UserEntity {
	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer userId;
	
	@Column(name = "user_name")
	String userName;
	
	@Column(name = "password")
	String password;
	
	@Column(name = "email", unique = true)
	String email;
	
	@Column(name = "contact_number", unique = true)
	Long phoneNo;
	
	@Column(name = "cart_id", unique = true)
	Integer cartId;
	
	@Column(name = "bank_id", unique = true)
	Long bankId;
}