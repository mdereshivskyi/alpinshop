package test.project.service.impl;

import java.util.List;

import static test.project.constants.ErrorMessage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import test.project.domain.EquipmentDTO;
import test.project.entity.BagpackEnt;
import test.project.exceptions.CategoryNotFoundException;
import test.project.exceptions.CategoryServiceException;
import test.project.repository.EquipmentRepos;
import test.project.service.CategorySrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;
@Service
public class CategorySrvImpl implements CategorySrv{

	@Autowired
	private EquipmentRepos categoryRepos;
	
	@Autowired
	private ObjectMapperUtils modelMapper;

	@Override
	public void saveCategory(EquipmentDTO dto) {
		BagpackEnt entity = modelMapper.map(dto, BagpackEnt.class);
		categoryRepos.save(entity);
	}

	@Override
	public List<EquipmentDTO> findAllCategories() {
		List<BagpackEnt> entity = categoryRepos.findAll();
		List<EquipmentDTO> dto = modelMapper.mapAll(entity, EquipmentDTO.class);
		return dto;
	}

	@Override
	public EquipmentDTO findById(Long id) {
		BagpackEnt entity = categoryRepos.findById(id).get();
		return modelMapper.map(entity, EquipmentDTO.class);
	}

	@Override
	public void updateCategory(EquipmentDTO dto) {
		categoryRepos.save(modelMapper.map(dto, BagpackEnt.class));
		
	}

	@Override
	public void delete(Long id) {
		categoryRepos.deleteById(id);
		
	}
	

}
