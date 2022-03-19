package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.entity.*;
import com.ecommerce.service.*;
 
@RestController
public class MemberController {

	@Autowired
	private UserService userService; 
	
		@GetMapping(value = "/login")
	    public ModelAndView login() 
	    {
			 ModelAndView model = new ModelAndView("login");
			 model.addObject("pageTitle", "SPORTY SHOES - MEMBER LOGIN");
			 
			 return model; 
	    }	
	  
		@PostMapping(value = "/loginaction")
	    public String loginAction(
	    		@RequestParam(value="email_id", required=true) String emailId,
	    		 @RequestParam(value="pwd", required=true) String pwd) 
	    {
			
		  User user = userService.authenticate(emailId, pwd);
		  
		  ModelAndView model = new ModelAndView("login");
		  model.addObject("user_id", user.getID());
		  
		  return "redirect:dashboard"; 
	    }		  
	  
	  	  
		@GetMapping(value = "/signup")
	    public ModelAndView signup(ModelMap map) 
	    {
			 ModelAndView model = new ModelAndView("register");
			 model.addObject("pageTitle", "SPORTY SHOES - MEMBER REGISTRATION");
			 
	         return model; 
	    }	
	  
		@PostMapping(value = "/signupaction")
	    public String signupAction(
	    		@RequestParam(value="email_id", required=true) String emailId,
	    		 @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2,
	    		 @RequestParam(value="fname", required=true) String fname,
	    		 @RequestParam(value="lname", required=true) String lname,
	    		 @RequestParam(value="age", required=true) String age,
	    		 @RequestParam(value="address", required=true) String address) 
	    {
			
			 ModelAndView model = new ModelAndView();
		 
		  if (emailId == null || emailId.equals("")) {
			  model.addObject("error", "Email id is required.");
			  return "register";
		  }

		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  model.addObject("error", "Error , Incomplete passwords submitted.");
			  return "register";
		  }
		  
		  if (!pwd.equals(pwd2)) {
			  model.addObject("error", "Error , Passwords do not match.");
			  return "register";
		  }
		  
		  if (fname == null || fname.equals("")) {
			  model.addObject("error", "First name is required.");
			  return "register";
		  }

		  if (lname == null || lname.equals("")) {
			  model.addObject("error", "Last name is required.");
			  return "register";
		  }
		  if (age == null || age.equals("")) {
			  age = "0";
		  }
		  
		  
		  User user = userService.getUserByEmailId(emailId);
		  if (user != null) { 
			  model.addObject("error", "This email id already exists.");
			  return "register";
		  }
		  user = new User();
		  user.setID(0);
		  user.setEmail(emailId);
		  user.setFname(fname);
		  user.setLname(lname);
		  user.setAge(Integer.parseInt(age));
		  user.setAddress(address);
		  user.setPwd(pwd);
		  
		  userService.updateUser(user);
		    
	        return "redirect:registerconfirm"; 
	    }
	  
	  
		@GetMapping(value = "/registerconfirm")
	    public String registerConfirm() 
	    {
	        return "register-confirm"; 
	    }	
		
		@GetMapping(value = "/editprofile")
	    public ModelAndView editProfile(@RequestParam(value="ID", required=true) String id) 
	    {
			ModelAndView model = new ModelAndView("edit-profile");
			
			long idValue = Long.parseLong(id);
		
			User user = userService.getUserById(idValue);

			model.addObject("pageTitle", "SPORTY SHOES - MEMBER EDIT PROFILE");
			model.addObject("user", user);

	        return model; 
	    }		 	  


	    @PostMapping(value = "/editprofileaction")
	    public String editProfileAction(
	    		@RequestParam(value="user_id", required=true) String userId,
	    		 @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2,
	    		 @RequestParam(value="fname", required=true) String fname,
	    		 @RequestParam(value="lname", required=true) String lname,
	    		 @RequestParam(value="age", required=true) String age,
	    		 @RequestParam(value="address", required=true) String address) 
	    {
		  
	    	ModelAndView model = new ModelAndView("login");
		
	    	long idValue = Long.parseLong(userId);
	    	
		  User user = userService.getUserById(idValue);
		  model.addObject("user", user);
		  
		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  model.addObject("error", "Error , Incomplete passwords submitted.");
			  return "edit-profile";
		  }
		  
		  if (!pwd.equals(pwd2)) {
			  model.addObject("error", "Error , Passwords do not match.");
			  return "edit-profile";
		  }
		  
		  if (fname == null || fname.equals("")) {
			  model.addObject("error", "First name is required.");
			  return "edit-profile";
		  }

		  if (lname == null || lname.equals("")) {
			  model.addObject("error", "Last name is required.");
			  return "edit-profile";
		  }
		  if (age == null || age.equals("")) {
			  age = "0";
		  }

		  user.setFname(fname);
		  user.setLname(lname);
		  user.setAge(Integer.parseInt(age));
		  user.setAddress(address);
		  user.setPwd(pwd);
		  
		  userService.updateUser(user);
		  
	        return "redirect:dashboard"; 
	    }

	    @GetMapping(value = "/logout")
	    public String logout() 
	    {
	        return "redirect:home"; 
	    }
}
