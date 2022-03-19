package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Category;

public interface CategoryService {

	public Category get(long id);

	void updateCategory(Category category);

	void deleteCategory(long id);

	List<Category> getAllCategories();

	String getCategoriesDropDown(long id);

		 
}
