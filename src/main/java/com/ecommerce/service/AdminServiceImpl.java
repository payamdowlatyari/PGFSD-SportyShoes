package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Admin;
import com.ecommerce.repository.AdminRepositoy;

@Service
public class AdminServiceImpl implements AdminService{

	 @Autowired
	 private AdminRepositoy adminRepositoy;
	 
	 public AdminServiceImpl(AdminRepositoy adminRepositoy) {
			super();
			this.adminRepositoy = adminRepositoy;
		}
	
		@Override
		public List<Admin> get() {
			return adminRepositoy.findAll();
		}
		
		@Override
		public Admin authenticate(String adminId, String pwd) {
			
		List <Admin> admin = adminRepositoy.findAll();
		
		for(Admin item: admin)
			
		if (item.getAdminId().equals(adminId) && item.getAdminPwd().equals(pwd)) {
			
				return item;
		}
		
			return null;		
		}
		
		@Override
		public Admin getAdminById(long id) throws EntityNotFoundException {
	        Optional<Admin> admin = adminRepositoy.findById(id);
	        if (admin.isPresent()) {
	            return admin.get();
	        } else {
	            throw new EntityNotFoundException("No admin record exist for given id");
	        }
		}		
		
		
		@Override
		public void updatePwd(Admin entity) {
			Optional<Admin> admin = adminRepositoy.findById(entity.getID());		
            if (admin.isPresent()) {
            	Admin newEntity = admin.get();
           	
            	if (newEntity.getAdminPwd().equals(entity.getAdminPwd()))
            	
                newEntity.setAdminPwd(entity.getAdminPwd());
                newEntity = adminRepositoy.save(newEntity);
            }
		}
		
		@Override
		public Admin getAdminByAdminId(String adminId) {
			List <Admin> admin = adminRepositoy.findAll();
			for(Admin item: admin) {
				
				if (item.getAdminId().equals(adminId)) {
					
						return item;
		        } 
			}
		           return null;
			
		}
		
		
		@Override
		public Admin getAdminByPwd(String pwd) {
			List <Admin> admin = adminRepositoy.findAll();
			for(Admin item: admin) {
				
				if (item.getAdminPwd().equals(pwd)) {
					
						return item;
		        } 
			}
		           return null;
		}
		
}
