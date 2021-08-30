package com.foodbox.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.Service.UserService;
import com.foodbox.entity.User;
import com.foodbox.entity.two.Order;

@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {

	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getOrderList")
	public List<Order> getOrderList(Principal principal) {
		User user = userService.getUser(principal.getName());
		List<Order> orderList = user.getOrderList();
		
		return orderList;
	}
}
