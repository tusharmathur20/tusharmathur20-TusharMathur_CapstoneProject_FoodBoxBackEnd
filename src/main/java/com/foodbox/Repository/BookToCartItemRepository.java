package com.foodbox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.two.BookToCartItem;
import com.foodbox.entity.two.CartItem;

public interface BookToCartItemRepository extends JpaRepository<BookToCartItem, Long> {
	void deleteByCartItem(CartItem cartItem);
}
