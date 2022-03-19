package com.ecommerce.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.service.*;
import com.ecommerce.entity.*;
 
@RestController
public class CartController {

	@Autowired
	private ProductService productService; 
	
	@Autowired
	private PurchaseItemService purchaseItemService; 
	
	@Autowired
	private UserService userService; 
	
	  
	  @GetMapping(value = "/cart")
	    public ModelAndView cart(@RequestParam("cart_items") int id) 
	    {
		  // check if user is logged in
		  ModelAndView modelAndView = new ModelAndView("cart");
		  
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  
			  // get total of all cart items
			  BigDecimal totalValue = getCartValue(cartItems);
			  modelAndView.addObject("cartValue", totalValue);
			  modelAndView.addObject("cartItems", cartItems);
 		  
		  
			  modelAndView.addObject("pageTitle", "SPORTY SHOES - YOUR CART");
	        return modelAndView; 
	    }
	  
	 
	   @GetMapping(value = "/cartadditem")
	    public ModelAndView cartAddItem(
	    		@RequestParam(value="id", required=true) String productId) 
	    {
		   ModelAndView modelAndView = new ModelAndView("cart");
		  	  
			  long idValue = Long.parseLong(productId);

			  List<CartItem> cartItems = new ArrayList<CartItem>();
	
				  Product product = productService.get(idValue);
				  CartItem item = new CartItem();
				  
				  item.setProductId(idValue);
				  item.setQty(1);
				  item.setRate(product.getPrice());
				  BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQty())); 
				  item.setPrice(dprice); 
				  item.setName(product.getName()); 
				  cartItems.add(item);
				  
				  modelAndView.addObject("cart_items", cartItems); 
		  
	        return modelAndView; 
	    }	  
	 
	   @GetMapping(value = "/cartdeleteitem")
	    public ModelAndView cartDeleteItem(
	    		@RequestParam(value="id", required=true) String id) 
	    {
		  // check if user is logged in
		   ModelAndView modelAndView = new ModelAndView("redirect:cart");
	
			  long idValue = Long.parseLong(id);
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  	  
			  for(CartItem item: cartItems) {
				  if (item.getProductId() == idValue) {
					  cartItems.remove(item);
					  modelAndView.addObject("cart_items", cartItems);
					  break;
				  }
			   }
		  
	        return modelAndView; 
	    }	

	 
	  @GetMapping(value = "/checkout")
	    public ModelAndView checkout() 
	    {
		  // check if user is logged in
		  ModelAndView modelAndView = new ModelAndView("redirect:cart");
		  
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			 
			  BigDecimal totalValue = getCartValue(cartItems);
			  modelAndView.addObject("cartValue", totalValue);
			  modelAndView.addObject("cartItems", cartItems);
		  
			  modelAndView.addObject("pageTitle", "SPORTY SHOES - CHECKOUT");
	        return modelAndView; 
	    }

	 
	  @GetMapping(value = "/completepurchase")
	    public ModelAndView completePurchase(@RequestParam(value="id", required=true) String id) 
	    {
		  // check if user is logged in
		  ModelAndView modelAndView = new ModelAndView("redirect:confirm");
		 
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			
			  BigDecimal totalValue = getCartValue(cartItems);
			  
			  User user = userService.getUserById(Long.parseLong(id));
			  
			  long userId =  user.getID();
			  
			  Purchase purchase = new Purchase();
			  purchase.setUserId(userId);
			  purchase.setDate(Calendar.getInstance().getTime());
			  purchase.setTotal(totalValue);
			  
			  for(CartItem item: cartItems) {
				  PurchaseItem pItem = new PurchaseItem();
				  pItem.setPurchaseId(purchase.getID());
				  pItem.setProductId(item.getProductId());
				  pItem.setUserId(userId);
				  pItem.setRate(item.getRate());
				  pItem.setQty(item.getQty());
				  pItem.setPrice(item.getPrice());
				  
				  purchaseItemService.save(pItem);
			  }
			  
			  modelAndView.addObject("cartValue", totalValue);
			  modelAndView.addObject("cartItems", cartItems);
		   
	        return modelAndView;  
	    }

	  
	  @GetMapping(value = "/gateway")
	    public ModelAndView gateway(@RequestParam(value="id", required=true) String id) 
	    {
		  ModelAndView modelAndView = new ModelAndView("gateway");
		 
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			
			  BigDecimal totalValue = getCartValue(cartItems);
			  modelAndView.addObject("cartValue", totalValue);
			  modelAndView.addObject("cartItems", cartItems);
		  
			  modelAndView.addObject("pageTitle", "SPORTY SHOES - PAYMENT GATEWAY");
	       return modelAndView; 
	    }
	  
	 
	  @GetMapping(value = "/confirm")
	    public ModelAndView confirm(Model map) 
	    {

		  ModelAndView modelAndView = new ModelAndView("confirm");
		
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			
			  BigDecimal totalValue = getCartValue(cartItems);
			  modelAndView.addObject("cartValue", totalValue);
		  
			  cartItems.clear();
			  modelAndView.addObject("cart_items", null);
		  
			  modelAndView.addObject("pageTitle", "SPORTY SHOES - PURCHASE CONFIRMATION");
			  
	        return modelAndView; 
	    }	  
	  
	  private BigDecimal getCartValue(List<CartItem> list) {
		  BigDecimal total = new BigDecimal(0.0);
		  
		  for(CartItem item: list) {
			  BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQty()));
			  total= total.add(dprice);
		   }
		  return total;
	  }

}
