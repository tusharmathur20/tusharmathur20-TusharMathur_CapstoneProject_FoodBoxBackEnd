package com.foodbox.Service;

import com.foodbox.entity.User;
import com.foodbox.entity.two.BillingAddress;
import com.foodbox.entity.two.Order;
import com.foodbox.entity.two.Payment;
import com.foodbox.entity.two.ShippingAddress;
import com.foodbox.entity.two.ShoppingCart;

public interface OrderService {

	Order createOrder(
			ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user
	);
}
