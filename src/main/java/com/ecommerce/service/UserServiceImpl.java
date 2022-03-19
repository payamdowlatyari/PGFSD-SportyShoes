package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	 @Autowired
	 private UserRepository userRepository;

	 @Override
		public List<User> get() {
			return userRepository.findAll();
		}
		
	 @Override
		public User authenticate(String adminId, String pwd) {
			
		List <User> user = userRepository.findAll();
		
		for(User item: user)
			
		 if (item.getEmail().equals(adminId) && item.getPwd().equals(pwd)) {	
			 
	            return item;
			 }   
		
			return null;
		}
	 
	 @Override
		public User getUserById(long id) {
		 	return userRepository.getById(id);
		}

	 @Override
		public User getUserByEmailId(String emailId) {
			List <User> user = userRepository.findAll();
			for(User item: user) {
				
				 if (item.getEmail().equals(emailId)) {				
			            return item;
				}   
			}
			return null;
		}
		
	 @Override
		public void updateUser(User user) {
		  	userRepository.save(user);			
		}

		public List<User> getAllUsers() {
		 return userRepository.findAll();
		}	 
}
