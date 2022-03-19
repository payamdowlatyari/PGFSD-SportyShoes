package com.ecommerce.controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 
@RestController
public class DashboardController {

		@GetMapping(value = "/dashboard")
	    public String dashboard(ModelMap map) 
	    {
		  map.addAttribute("pageTitle", "SPORTY SHOES - DASHBOARD");
	        return "dashboard"; 
	    }		  
}
