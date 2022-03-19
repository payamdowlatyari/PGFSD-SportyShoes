package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public void save(Product product) {
		productRepository.save(product);
	}
	
	@Override
	public Product update(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public Product get(Long id) {
		return productRepository.getById(id);
	}
	
	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
	
	@Override
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
}
