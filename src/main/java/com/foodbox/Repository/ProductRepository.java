package com.foodbox.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.foodbox.entity.two.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	
	List<Product> findByNameContaining(String keyword);
}
