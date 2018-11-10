package test.project.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import test.project.domain.BootsDTO;

public interface BootsSrv {

	void create(BootsDTO dto, String authToken);
	
	void update(BootsDTO dto);
	
	void delete(String bootsId);
	
	List<BootsDTO> findAll();
	
	List<BootsDTO> findAllBoots(Pageable pageable);
	
	List<BootsDTO> findBySize(float size);
	
	BootsDTO findByBootsId(String bootsId);
	
	void uploadImage(MultipartFile file, String bootsId);
	
}
