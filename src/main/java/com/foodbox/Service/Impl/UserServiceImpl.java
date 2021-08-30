package com.foodbox.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.Repository.RoleRepository;
import com.foodbox.Repository.UserBillingRepository;
import com.foodbox.Repository.UserPaymentRepository;
import com.foodbox.Repository.UserRepository;
import com.foodbox.Repository.UserShippingRepository;
import com.foodbox.Service.UserService;
import com.foodbox.entity.User;
import com.foodbox.entity.UserRole;
import com.foodbox.entity.two.ShoppingCart;
import com.foodbox.entity.two.UserBilling;
import com.foodbox.entity.two.UserPayment;
import com.foodbox.entity.two.UserShipping;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private RoleRepository rolerepo;
	
	@Autowired
	private UserBillingRepository userBillingRepository;
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	
	
	//Creating User
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
User local=this.userrepo.findByUsername(user.getUsername());
if(local!=null) {
	System.out.println("User is already there !!");
	throw new Exception("User Already exist");
}else {
	//user create
	for(UserRole ur:userRoles) {
		rolerepo.save(ur.getRole());
	}
	
	user.getUserRoles().addAll(userRoles);
	
	ShoppingCart shoppingCart = new ShoppingCart();
	shoppingCart.setUser(user);
	user.setShoppingCart(shoppingCart);
	
	user.setUserPaymentList(new ArrayList<UserPayment>());
	user.setUserShippingList(new ArrayList<UserShipping>());
	local = this.userrepo.save(user);
}
		return local;
	}

	
	//Get user by username

	@Override
	public User getUser(String username) {
		return this.userrepo.findByUsername(username);
	}


	@Override
	public void deleteUser(Long userId) {
		this.userrepo.deleteById(userId);
		
	}


	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userrepo.findByEmail(email);
	}


	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return userrepo.findById(id);
	}


	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userrepo.save(user);
	}


	@Override
	public void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user) {
		save(user);
		userBillingRepository.save(userBilling);
		userPaymentRepository.save(userPayment);
	
		
	}


	@Override
	public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		save(user);
	}
	


	@Override
	public void setUserDefaultPayment(Long userPaymentId, User user) {
List<UserPayment> userPaymentList = (List<UserPayment>) userPaymentRepository.findAll();
		
		for (UserPayment userPayment : userPaymentList) {
			if(userPayment.getId() == userPaymentId) {
				userPayment.setDefaultPayment(true);
				userPaymentRepository.save(userPayment);
			} else {
				userPayment.setDefaultPayment(false);
				userPaymentRepository.save(userPayment);
			}
		}
	}
	@Override
	public void updateUserShipping(UserShipping userShipping, User user) {
		userShipping.setUser(user);
		userShipping.setUserShippingDefault(true);
		user.getUserShippingList().add(userShipping);
		save(user);
	}


	
	
	@Override
	public void setUserDefaultShipping(Long userShippingId, User user) {
		List<UserShipping> userShippingList = (List<UserShipping>) userShippingRepository.findAll();
		
		for (UserShipping userShipping : userShippingList) {
			if(userShipping.getId() == userShippingId) {
				userShipping.setUserShippingDefault(true);
				userShippingRepository.save(userShipping);
			} else {
				userShipping.setUserShippingDefault(false);
				userShippingRepository.save(userShipping);
			}
		}
	}

}
