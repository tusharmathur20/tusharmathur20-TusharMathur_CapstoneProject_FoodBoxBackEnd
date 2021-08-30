package com.foodbox.Service;

import java.util.Optional;
import java.util.Set;

import com.foodbox.entity.User;
import com.foodbox.entity.UserRole;
import com.foodbox.entity.two.UserBilling;
import com.foodbox.entity.two.UserPayment;
import com.foodbox.entity.two.UserShipping;

public interface UserService {

	public User createUser(User user,Set<UserRole>userRoles) throws Exception; 
	
	
	//Get user by username
	public User getUser(String username);
	
//delete user by username
	public void deleteUser(Long userId);
	//
	User findByEmail(String email);
	Optional<User> findById(Long id);

User save(User user);
	
	
	
	void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user);
	
	void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);
	
	void setUserDefaultPayment(Long userPaymentId, User user);
	
	void updateUserShipping(UserShipping userShipping, User user);

	void setUserDefaultShipping(Long userShippingId, User user);
	
}

