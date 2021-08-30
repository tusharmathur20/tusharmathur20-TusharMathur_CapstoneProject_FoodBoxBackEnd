package com.foodbox.Service;

import java.util.Optional;

import com.foodbox.entity.two.UserShipping;

public interface UserShippingService {
Optional<UserShipping> findById(Long id);
	
	void removeById(Long id);

}