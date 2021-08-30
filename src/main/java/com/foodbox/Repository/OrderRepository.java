package com.foodbox.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.User;
import com.foodbox.entity.two.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
	List<Order> findByUser(User user);
}
