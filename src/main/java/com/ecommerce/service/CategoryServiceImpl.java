package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Category;
import com.ecommerce.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	 @Autowired
	 private CategoryRepository categoryRepository;
	 
	 	@Override
		public Category get(long id) throws EntityNotFoundException {
	        Optional<Category> category = categoryRepository.findById(id);
	        if (category.isPresent()) {
	            return category.get();
	        } else {
	            throw new EntityNotFoundException("No category record exist for given id");
	        }
		}
		
	 	@Override
		public void updateCategory(Category category) {
			categoryRepository.save(category);
		}

	 	@Override
		public void deleteCategory(long id) {
			categoryRepository.deleteById(id);
		}

	 	@Override
		public List<Category> getAllCategories() {
			return categoryRepository.findAll();
		}
		
	 	@Override
		public String getCategoriesDropDown(long id) {
			StringBuilder sb = new StringBuilder("");
			List<Category> list = categoryRepository.findAll();
			for(Category cat: list) {
				if (cat.getID() == id)
					sb.append("<option value=" + String.valueOf(cat.getID()) + " selected>" + cat.getName() + "</option>");
				else
					sb.append("<option value=" + String.valueOf(cat.getID()) + ">" + cat.getName() + "</option>");
				 
			}
				return sb.toString();
		}
		
}
