package com.helloworld.demoproject.services;
import java.util.List;

import com.helloworld.demoproject.entities.Category;



public interface CategoryService {
	
	public Category save(Category entity);

	public Category update(Category entity);

	public List<Category> selectAll();

	public List<Category> selectAll(String sortField, String sort);

	public void remove(Long id);

	public Category getById(Long id);

	public Category findOne(String paramName, Object paramValue);

	public Category findOne(String[] paramNames, Object[] paramValues);

	public int findCountBy(String paramName, String paramValue);

}