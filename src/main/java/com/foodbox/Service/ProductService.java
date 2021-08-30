package com.foodbox.Service;

import java.util.List;
import java.util.Optional;

import com.foodbox.entity.two.Product;

public interface ProductService {

	List<Product>findAll();
	Optional<Product> findOne(Long id);
	
	Product save(Product product);
	
	List<Product>blurrySearch (String name);
	
	void removeOne(Long id);
	
}
