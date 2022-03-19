package com.ecommerce.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.entity.*;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

@RestController
public class HomeController {
	
	@Autowired
	private CategoryService categoryService; 
	
	@Autowired
	private ProductService productService; 
	
		@GetMapping(value = {"/","home"})
	    public ModelAndView home() 	    
	    {
			 ModelAndView model = new ModelAndView("index");
			
			 List<Product> list = productService.findAll();
			 model.addObject("list", list);
			 System.out.println(list); 
			HashMap<Long, String> mapCats = new HashMap<Long, String>();
			for(Product product: list) {
				 
				  Category category = categoryService.get(product.getCategoryId());
				  if (category != null)
					  
					  mapCats.put(product.getID(), category.getName());
			}		
			 System.out.println(mapCats); 
			
			 model.addObject("mapCats", mapCats);
			 model.addObject("pageTitle", "SPORTY SHOES - HOMEPAGE"); 
	        return model; 
	    }	 
}
