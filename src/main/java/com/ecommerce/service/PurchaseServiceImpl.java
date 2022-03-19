package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Purchase;
import com.ecommerce.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	 @Autowired
	 private PurchaseRepository purchaseRepository;

	 @Override
		public Purchase getPurchaseById(long id) {
			return purchaseRepository.getById(id);
		}
		
	 @Override
		public List<Purchase> getAllItems() {
			return purchaseRepository.findAll();
		}			
		
	 @Override
		public Optional<Purchase> getAllItemsByUserId(long userId) {
			return purchaseRepository.findById(userId);
		}	
		
	 @Override
		public long updatePurchase(Purchase purchase) {
			return purchaseRepository.save(purchase).getID();
		}
		
	 @Override
		public void deletePurchase(long id) {
			purchaseRepository.deleteById(id);
		}

	 
}
