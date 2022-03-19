package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.entity.PurchaseItem; 

public interface PurchaseItemRepository extends JpaRepository <PurchaseItem, Long> {
	
}
