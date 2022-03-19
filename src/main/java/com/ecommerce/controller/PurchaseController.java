package com.ecommerce.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.service.*;
import com.ecommerce.entity.*;

@RestController
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;

		@GetMapping(value = "/memberpurchases")
	    public ModelAndView memberpurchases() 
	    {
			 ModelAndView model = new ModelAndView("purchases");

		  List<Purchase> list = purchaseService.getAllItems();
		  
		  BigDecimal total = new BigDecimal(0.0);
		  // map purchase items to each purchase for display
		  HashMap<Long, String> mapItems = new HashMap<Long, String>();
		  
		  for(Purchase purchase: list) {
			  
			  total = total.add(purchase.getTotal());

			  StringBuilder sb = new StringBuilder("");
			  
			  mapItems.put(purchase.getID(), sb.toString());
		  }
		  model.addObject("totalAmount", total);
		  model.addObject("list", list);
		  model.addObject("mapItems", mapItems);
		  model.addObject("pageTitle", "SPORTY SHOES - YOUR ORDERS");
		  
	       return model; 
	    }		  
}
