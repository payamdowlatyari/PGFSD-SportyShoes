package com.ecommerce.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.entity.*;
import com.ecommerce.service.*;

@RestController
public class AdminController {
	
	@Autowired
    private AdminService adminService; 
	
	@Autowired
	private CategoryService categoryService; 

	@Autowired
	private ProductService productService; 

	@Autowired
	private PurchaseService purchaseService; 
	
	@Autowired
	private UserService userService; 
	
		@GetMapping(value = "/adminlogin")
	    public ModelAndView login() 
	    {
		  ModelAndView model = new ModelAndView("admin/login");
	        return model; 
	    }
	  
		@PostMapping(value = "/adminloginaction")
	    public ModelAndView loginAction(
	    		 @RequestParam(value="admin_id", required=true) String adminId,
	    		 @RequestParam(value="admin_pwd", required=true) String pwd) 
	    {
	  		ModelAndView model = new ModelAndView("admin/dashboard"); 
	  		ModelAndView loginView = new ModelAndView("admin/login"); 
		  Admin admin =  adminService.authenticate(adminId, pwd);
		  if (admin == null) { 
			  loginView.addObject("error", "Admin login failed");
			  return loginView;
		  }		  
	        return model; 
	    }	  
	    
		@GetMapping(value = "/admindashboard")
	    public ModelAndView dashboard() 
	    {
		  ModelAndView adminActionView = new ModelAndView("admin/dashboard"); 
		  ModelAndView loginView = new ModelAndView("admin/login");
		  if (loginView.getViewName() != "admin_id") {
			  return loginView;
		  }
		   
		  loginView.addObject("pageTitle", "ADMIN DASHBOARD");
	        return adminActionView; 
	    }
	   
	  @GetMapping(value = "/adminchangepassword")
	    public ModelAndView changePwd()
	    {
		  ModelAndView adminActionView = new ModelAndView("admin/change-password"); 
		 		  
		  Admin admin = adminService.getAdminById(1);
		  
		  adminActionView.addObject("admin", admin);
		  adminActionView.addObject("pageTitle", "ADMIN CHANGE PASSWORD");
	        return adminActionView; 
	    }
	  
	  @PostMapping(value = "/adminchangepwdaction")
	    public ModelAndView updatePassword(@RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2) throws SQLException 	    		
	    {

		
		 ModelAndView adminActionView = new ModelAndView("admin/change-password"); 
		  
		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  adminActionView.addObject("error", "Error , Incomplete passwords submitted.");
			  return adminActionView;
		  }
		  if (!pwd.equals(pwd2)) {
			  adminActionView.addObject("error", "Error , Passwords do not match.");
			  return adminActionView;
		  }
		  
		  ModelAndView adminDashboard = new ModelAndView("admin/dashboard"); 
		  Admin admin = adminService.getAdminById(1);  
		  admin.setAdminPwd(pwd2);
		  adminService.updatePwd(admin);
		  
	        return adminDashboard;  
	    }		  
	  
	  @GetMapping(value = "/adminproducts")
	    public ModelAndView products() 
	    {
		  ModelAndView products = new ModelAndView("admin/products"); 

		  List<Product> list = productService.findAll();
		  // use a MAP to link category names to each product in list  
		  HashMap<Long, String> mapCats = new HashMap<Long, String>();
		  
		  for(Product product: list) {
			  Category category = categoryService.get(product.getCategoryId());
			  if (category != null)
				  mapCats.put(product.getID(), category.getName());
		  }
		  products.addObject("list", list);
		  products.addObject("mapCats", mapCats);

		  products.addObject("pageTitle", "ADMIN SETUP PRODUCTS");
	        return products; 
	    }
	  
	  @GetMapping(value = "/admincategories")
	    public ModelAndView categories() 
	    {

		  ModelAndView model = new ModelAndView("admin/categories");
		  
		  List<Category> list = categoryService.getAllCategories();
		  model.addObject("list", list);
		  model.addObject("pageTitle", "ADMIN SETUP PRODUCT CATEGORIES");
	        return model; 
	    }	  
	  
	  @GetMapping(value = "/adminmembers")
	    public ModelAndView members() 
	    {
		  ModelAndView model = new ModelAndView("admin/members");
		  List<User> list = userService.get();
		  
		  model.addObject("list", list);
		  model.addObject("pageTitle", "ADMIN BROWSE MEMBERS");
	        return model; 
	    }
	  
	  @GetMapping(value = "/adminpurchases")
	    public ModelAndView purchases() 
	    {

		  ModelAndView model = new ModelAndView("admin/purchases");
		  
		  List<Purchase> list = purchaseService.getAllItems();
		  
		  BigDecimal total = new BigDecimal(0.0);
		  
		  for(Purchase purchase: list) {
			  total = total.add(purchase.getTotal());
		  }
		  
		  // use MAPs to mape users to each purchase and item names to each purchase item row
		  HashMap<Long, String> mapItems = new HashMap<Long, String>();
		  HashMap<Long, String> mapUsers = new HashMap<Long, String>();
		  
		  for(Purchase purchase: list) {
			  total = total.add(purchase.getTotal());
			  User user = userService.getUserById(purchase.getUserId());
			  if (user != null)
				  mapUsers.put(purchase.getID(), user.getFname() + " " + user.getLname());
			  		
			  StringBuilder sb = new StringBuilder(""); 

			  mapItems.put(purchase.getID(), sb.toString());
		  }		  
		  model.addObject("totalAmount", total); 
		  model.addObject("list", list);
		  model.addObject("mapItems", mapItems);
		  model.addObject("mapUsers", mapUsers);
		  model.addObject("pageTitle", "ADMIN PURCHASES REPORT");
		  
	        return model; 
	    }	  

	  @GetMapping(value = "/admindeletecat")
	    public String deleteCategory(@RequestParam(value="id", required=true) String id)
	    {
		 
		  long idValue = Long.parseLong(id);
		  if (idValue > 0) {
			  categoryService.deleteCategory(idValue);
		  }
		  return "redirect:admincategories";
	    }		  
	  
	  @GetMapping(value = "/admineditcat")
	    public ModelAndView editCategory(@RequestParam(value="id", required=true) String id)
	    {
		  ModelAndView model = new ModelAndView("admin/edit-category");
		  long idValue = Long.parseLong(id);
		  Category category = new Category();
		  if (idValue > 0) {
			  category = categoryService.get(idValue);
		  } else {
			  category.setID(0);
		  }
		  model.addObject("category", category);
		  model.addObject("pageTitle", "ADMIN EDIT PRODUCT CATEGORY");
	        return model; 
	    }		  
	  
	  @PostMapping(value = "/admineditcataction")
	    public String updateCategory(@RequestParam(value="id", required=true) String id,
	    		 @RequestParam(value="name", required=true) String name)
	    {
		  long idValue = Long.parseLong(id); 
		  
		  	Category category = new Category();
		  	category.setID(idValue); 
		  	category.setName(name);
		  	
		  	categoryService.updateCategory(category); 
		  	
	        return "redirect:admincategories";  
	    }
	  
	  @GetMapping(value = "/admindeleteproduct")
	    public String deleteProduct(@RequestParam(value="id", required=true) String id)		
	    {
		  long idValue = Long.parseLong(id); 

			  productService.deleteById(idValue);

		  return "redirect:adminproducts";
	    }		  
	  
	  @GetMapping(value = "/admineditproduct")
	    public ModelAndView editProduct(@RequestParam(value="id") String id)
	    		
	    {
		  ModelAndView model = new ModelAndView("admin/edit-product");
		  long idValue = Long.parseLong(id); 
		  Product product = productService.get(idValue);
		  
		  productService.update(product);
		  model.addObject("product", product);
		  model.addObject("pageTitle", "ADMIN EDIT PRODUCT");
		  
	        return model; 
	    }	
	  
	  	@PostMapping(value = "/admineditproductaction")
	    public String updateProduct(
	    		 @RequestParam(value="id", required=true) String id,
	    		 @RequestParam(value="name", required=true) String name,
	    		 @RequestParam(value="price", required=true) String price,
	    		 @RequestParam(value="category", required=true) String categoryId) 
	    {
		  long idValue = Long.parseLong(id); 
	
		  BigDecimal priceValue = new BigDecimal(0.0);
		  	  
		  	Product product = new Product();
		  	product.setID(idValue); 
		  	product.setCategoryId(Long.parseLong(categoryId));
		  	product.setName(name);
		  	product.setPrice(priceValue);
		  	
		  	productService.update(product); 		  	
	        return "redirect:adminproducts"; 
	    }	
	   
	  
	  @GetMapping(value = "/adminlogout")
	    public ModelAndView logout() 
	    {
		 return new ModelAndView("admin/login");
	    }
}

