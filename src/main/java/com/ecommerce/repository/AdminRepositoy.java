package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.entity.Admin; 

public interface AdminRepositoy extends JpaRepository<Admin, Long> {
	
}

