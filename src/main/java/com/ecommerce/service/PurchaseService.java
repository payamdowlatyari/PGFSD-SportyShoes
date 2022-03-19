package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.entity.Purchase;

public interface PurchaseService {

	Purchase getPurchaseById(long id);

	List<Purchase> getAllItems();

	Optional<Purchase> getAllItemsByUserId(long userId);

	long updatePurchase(Purchase purchase);

	void deletePurchase(long id);	
}
