package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Product;

public interface ProductService{

	List<Product> findAll();

	void deleteById(Long id);

	Product get(Long id);

	Product update(Product product);

	void save(Product product);
	
	
}