package com.foodbox.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.Repository.ProductRepository;
import com.foodbox.Service.ProductService;
import com.foodbox.entity.two.Product;

@Service
public class ProductServiceImpl implements ProductService {

@Autowired
private ProductRepository productRepository;

@Override
public List<Product> findAll() {
	List<Product>productList=(List<Product>)productRepository.findAll();

	List<Product>activeProductList=new ArrayList<>();
	
	for(Product product:productList) {
		if(product.isActive()) {
		activeProductList.add(product);	
		}
	}
	return activeProductList;

}

@Override
public Optional<Product> findOne(Long id) {

	return productRepository.findById(id);
}

@Override
public Product save(Product product) {
	return productRepository.save(product);
}

@Override
public List<Product> blurrySearch(String keyword) {
List<Product>productList=productRepository.findByNameContaining(keyword);
List<Product>activeProductList=new ArrayList<>();

for(Product product:productList) {
	if(product.isActive()) {
	activeProductList.add(product);	
	}
}
return activeProductList;
}

@Override
public void removeOne(Long id) {
	productRepository.deleteById(id);
	
}


	
}
