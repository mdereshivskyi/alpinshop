package test.project.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import test.project.domain.HelmetDTO;

public interface HelmetSrv {
	
	void create(HelmetDTO dto, String authToken);
	
	void update(HelmetDTO dto);
	
	void delete(String helmetId);
	
	List<HelmetDTO> findAll();
	
	List<HelmetDTO> findAllHelmets(Pageable pageable);
	
	List<HelmetDTO> findBySize(String size);
	
	HelmetDTO findByHelmetId(String helmetId);
	
	void uploadImage(MultipartFile file, String helmetId);
}
