package com.foodbox.Controller;


import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.Service.CartItemService;
import com.foodbox.Service.ProductService;
import com.foodbox.Service.ShoppingCartService;
import com.foodbox.Service.UserService;
import com.foodbox.entity.User;
import com.foodbox.entity.two.CartItem;
import com.foodbox.entity.two.Product;
import com.foodbox.entity.two.ShoppingCart;


@RestController
@CrossOrigin
@RequestMapping("/cart")
public class ShoppingCartController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/add")
	public ResponseEntity<?> addItem (
			@RequestBody HashMap<String, String> mapper, Principal principal
			){
		String productId = (String) mapper.get("productId");
		String qty = (String) mapper.get("qty");
		
		User user = userService.getUser (principal.getName());
		Optional<Product> product1 = productService.findOne(Long.parseLong(productId));
		Product product=product1.get();
		if(Integer.parseInt(qty) > product.getInStock()) {
			return new ResponseEntity("Not Enough Stock!", HttpStatus.BAD_REQUEST);
		}
		
		CartItem cartItem = cartItemService.addBookToCartItem(product, user, Integer.parseInt(qty));
		
		return new ResponseEntity("Product Added Successfully!", HttpStatus.OK);
	}
	
	@RequestMapping("/getCartItemList")
	public List<CartItem> getCartItemList(Principal principal) {
		User user = userService.getUser(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		return cartItemList;
	}
	
	@RequestMapping("/getShoppingCart")
	public ShoppingCart getShoppingCart(Principal principal) {
		User user = userService.getUser(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		return shoppingCart;
	}
	
	@RequestMapping("/removeItem")
	public ResponseEntity<?> removeItem(@RequestBody String id) {
		
		cartItemService.removeCartItem(cartItemService.findById(Long.parseLong(id)).get());
		
		return new ResponseEntity("Cart Item Removed Successfully!", HttpStatus.OK);
	}
	
	@RequestMapping("/updateCartItem")
	public ResponseEntity updateCartItem(
			@RequestBody HashMap<String, String> mapper
			){
		String cartItemId = mapper.get("cartItemId");
		String qty = mapper.get("qty");
		
		Optional<CartItem> cartItem1 = cartItemService.findById(Long.parseLong(cartItemId));
	CartItem cartItem=cartItem1.get();
		cartItem.setQty(Integer.parseInt(qty));
		
		cartItemService.updateCartItem(cartItem);
		
		return new ResponseEntity("Cart Item Updated Successfully!", HttpStatus.OK);
	}
	
}