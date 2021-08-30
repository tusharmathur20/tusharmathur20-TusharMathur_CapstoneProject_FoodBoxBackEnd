package com.foodbox.Service;

import java.util.List;
import java.util.Optional;

import com.foodbox.entity.User;
import com.foodbox.entity.two.CartItem;
import com.foodbox.entity.two.Product;
import com.foodbox.entity.two.ShoppingCart;

public interface CartItemService {
CartItem addBookToCartItem(Product product, User user, int qty);
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
//	List<CartItem> findByOrder(Order order);
	
	CartItem updateCartItem(CartItem cartItem);
	
	void removeCartItem(CartItem cartItem);
	
	Optional<CartItem> findById(Long id);
	
	CartItem save(CartItem cartItem);
}
