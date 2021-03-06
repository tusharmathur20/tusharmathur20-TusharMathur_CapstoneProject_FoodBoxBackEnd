package com.foodbox.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.Repository.UserPaymentRepository;
import com.foodbox.Service.UserPaymentService;
import com.foodbox.entity.two.UserPayment;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	public Optional<UserPayment> findById(Long id) {
		return userPaymentRepository.findById(id);
	}
	
	public void removeById(Long id) {
		userPaymentRepository.deleteById(id);
	}
	
}