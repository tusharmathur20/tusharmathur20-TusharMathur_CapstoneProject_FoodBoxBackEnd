package com.foodbox.Service;

import java.util.Optional;

import com.foodbox.entity.two.UserPayment;

public interface UserPaymentService {
Optional<UserPayment> findById(Long id);
	
	void removeById(Long id);
}
