package com.ecommerce.service;

import java.util.List;
import com.ecommerce.entity.Admin;

public interface AdminService {

	List<Admin> get();

	public Admin authenticate(String adminId, String pwd);
	
	public Admin getAdminById(long id);
	
	public Admin getAdminByAdminId(String adminId);
	
	public Admin getAdminByPwd(String pwd);
	
	public void updatePwd(Admin entity);
	
	
}
