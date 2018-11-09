package test.project.service;

import java.util.List;

import test.project.domain.EquipmentDTO;

public interface CategorySrv {

	void saveCategory(EquipmentDTO dto);
	
	List<EquipmentDTO> findAllCategories();
	
	EquipmentDTO findById(Long id);
	
	void updateCategory(EquipmentDTO dto);
	
	void delete (Long id);
}
