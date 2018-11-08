package test.project.service;

import java.util.List;

import test.project.domain.CategoryDTO;

public interface CategorySrv {

	void saveCategory(CategoryDTO dto);
	
	List<CategoryDTO> findAllCategories();
	
	CategoryDTO findById(Long id);
	
	void updateCategory(CategoryDTO dto);
	
	void delete (Long id);
}
