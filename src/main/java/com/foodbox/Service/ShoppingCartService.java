package com.foodbox.Service;

import com.foodbox.entity.two.ShoppingCart;

public interface ShoppingCartService {
ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	
	void clearShoppingCart(ShoppingCart shoppingCart);

}
