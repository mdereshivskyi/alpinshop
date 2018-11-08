package test.project.service.impl;

import java.util.List;

import static test.project.constants.ErrorMessage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import test.project.domain.CategoryDTO;
import test.project.entity.BagpackEnt;
import test.project.exceptions.CategoryNotFoundException;
import test.project.exceptions.CategoryServiceException;
import test.project.repository.CategoryRepos;
import test.project.service.CategorySrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;
@Service
public class CategorySrvImpl implements CategorySrv{

	@Autowired
	private CategoryRepos categoryRepos;
	
	@Autowired
	private ObjectMapperUtils modelMapper;

	@Override
	public void saveCategory(CategoryDTO dto) {
		BagpackEnt entity = modelMapper.map(dto, BagpackEnt.class);
		categoryRepos.save(entity);
	}

	@Override
	public List<CategoryDTO> findAllCategories() {
		List<BagpackEnt> entity = categoryRepos.findAll();
		List<CategoryDTO> dto = modelMapper.mapAll(entity, CategoryDTO.class);
		return dto;
	}

	@Override
	public CategoryDTO findById(Long id) {
		BagpackEnt entity = categoryRepos.findById(id).get();
		return modelMapper.map(entity, CategoryDTO.class);
	}

	@Override
	public void updateCategory(CategoryDTO dto) {
		categoryRepos.save(modelMapper.map(dto, BagpackEnt.class));
		
	}

	@Override
	public void delete(Long id) {
		categoryRepos.deleteById(id);
		
	}
	

}
