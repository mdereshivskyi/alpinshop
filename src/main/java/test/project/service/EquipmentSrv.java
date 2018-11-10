package test.project.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import test.project.domain.EquipmentDTO;

public interface EquipmentSrv {

	void create(EquipmentDTO dto, String authToken);
	
	void update(EquipmentDTO dto);
	
	void delete(String equipmentId);
	
	List<EquipmentDTO> findAll();
	
	List<EquipmentDTO> findAllEquipments(Pageable pageable);
	
	List<EquipmentDTO> findByType(String type);
	
	EquipmentDTO findByEquipmentId(String equipmentId);
	
	void uploadImage(MultipartFile file, String equipmentId);
}
