package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.User;


public interface UserService {

	List<User> get();

	User authenticate(String adminId, String pwd);

	User getUserById(long id);

	void updateUser(User user);

	User getUserByEmailId(String emailId);

	
}
