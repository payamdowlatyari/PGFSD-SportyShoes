package com.ecommerce.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "eproduct")  
public class Product { 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "date_added")
	private Date dateAdded;  

	@Column(name = "category_id")
	private long categoryId;  
	
	public Product() {}

	public long getID() {return this.ID; }  
	public String getName() { return this.name;} 
	public BigDecimal getPrice() { return this.price;} 
	public long getCategoryId() { return this.categoryId;}
	public Date getDateAdded() { return this.dateAdded;}

	
	public void setID(long id) { this.ID = id;}
	public void setName(String value) { this.name = value;}
	public void setPrice(BigDecimal value) { this.price = value;}
	public void setCategoryId(long value) { this.categoryId = value;}
	public void setDateAdded(Date date) { this.dateAdded = date;}
	
	@Override
	public String toString() {
		return "ID=" + this.ID + ", name=" + this.name + ", price=" + this.price;
	}
}
