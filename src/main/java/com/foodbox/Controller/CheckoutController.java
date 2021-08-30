package com.foodbox.Controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodbox.Service.CartItemService;
import com.foodbox.Service.OrderService;
import com.foodbox.Service.ShoppingCartService;
import com.foodbox.Service.UserService;
import com.foodbox.entity.User;
import com.foodbox.entity.two.BillingAddress;
import com.foodbox.entity.two.CartItem;
import com.foodbox.entity.two.Order;
import com.foodbox.entity.two.Payment;
import com.foodbox.entity.two.ShippingAddress;
import com.foodbox.entity.two.ShoppingCart;


@RestController
@CrossOrigin("*")
@RequestMapping("/checkout")
public class CheckoutController {
private Order order = new Order();
	

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	
	@RequestMapping(value = "/checkout", method=RequestMethod.POST)
	public Order checkoutPost(
				@RequestBody HashMap<String, Object> mapper,
				Principal principal
			){
		ObjectMapper om = new ObjectMapper();
		
		ShippingAddress shippingAddress = om.convertValue(mapper.get("shippingAddress"), ShippingAddress.class);
		BillingAddress billingAddress = om.convertValue(mapper.get("billingAddress"), BillingAddress.class);
		Payment payment = om.convertValue(mapper.get("payment"), Payment.class);
		String shippingMethod = (String) mapper.get("shippingMethod");
		
		ShoppingCart shoppingCart = userService.getUser(principal.getName()).getShoppingCart();
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		User user = userService.getUser(principal.getName());
		Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);
		

		
		shoppingCartService.clearShoppingCart(shoppingCart);
		
		LocalDate today = LocalDate.now();
		LocalTime time=LocalTime.now();
		LocalTime estimatedDeliveryTime;
		if (shippingMethod.equals("groundShipping")) {
			estimatedDeliveryTime=time.plusMinutes(50);
		} else {
			estimatedDeliveryTime=time.plusMinutes(15);
		}
		
		this.order = order;
		
		return order;
		
	}
}
