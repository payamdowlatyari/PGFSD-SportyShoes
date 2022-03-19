package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.entity.Purchase;

public interface PurchaseRepository extends JpaRepository <Purchase, Long>{

}
