package com.ecommerce.service;

import java.util.Optional;

import com.ecommerce.entity.PurchaseItem;


public interface PurchaseItemService {

	void deleteAllItemsForPurchaseId(long purchaseId);

	void deleteItem(long id);

	void updateItem(PurchaseItem item);

	Optional<PurchaseItem> getAllItemsByPurchaseId(long purchaseId);

	PurchaseItem getItemById(long id);
	
	void save(PurchaseItem item);
	
}
