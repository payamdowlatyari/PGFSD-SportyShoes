package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.PurchaseItem;
import com.ecommerce.repository.PurchaseItemRepository;

@Service
public class PurchaseItemServiceImpl implements PurchaseItemService{

	 @Autowired
	 private PurchaseItemRepository purchaseItemRepository;

	 @Override
		public PurchaseItem getItemById(long id) {
		 return purchaseItemRepository.getById(id);
		}
	 
	 @Override
		public Optional<PurchaseItem> getAllItemsByPurchaseId(long purchaseId) {
			return purchaseItemRepository.findById(purchaseId);			
		}	
		
	 @Override
		public void updateItem(PurchaseItem item) {
			purchaseItemRepository.save(item);
		}
		
	 @Override
		public void deleteItem(long id) {
			purchaseItemRepository.deleteById(id);
		}

	 @Override
		public void deleteAllItemsForPurchaseId(long purchaseId) {
			purchaseItemRepository.deleteById(purchaseId);
		}

	 @Override
	 	public void save(PurchaseItem item) {
		 	purchaseItemRepository.save(item);
	 	} 
}
