package com.foodbox.Service.Impl;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.Repository.BillingAddressRepository;
import com.foodbox.Repository.OrderRepository;
import com.foodbox.Repository.PaymentRepository;
import com.foodbox.Repository.ShippingAddressRepository;
import com.foodbox.Service.CartItemService;
import com.foodbox.Service.OrderService;
import com.foodbox.Service.ProductService;
import com.foodbox.entity.User;
import com.foodbox.entity.two.BillingAddress;
import com.foodbox.entity.two.CartItem;
import com.foodbox.entity.two.Order;
import com.foodbox.entity.two.Payment;
import com.foodbox.entity.two.Product;
import com.foodbox.entity.two.ShippingAddress;
import com.foodbox.entity.two.ShoppingCart;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private BillingAddressRepository billingAddressRepository;
	
	@Autowired
	private ShippingAddressRepository shippingAddressRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ProductService bookService;
	

	
	public synchronized  Order createOrder(
			ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user
			){
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for (CartItem cartItem : cartItemList) {
			Product product = cartItem.getProduct();
			cartItem.setOrder(order);
			product.setInStock(product.getInStock()-cartItem.getQty());
		}
		
		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}
	
	public Optional<Order> findOne(Long id) {
		return orderRepository.findById(id);
	}

}