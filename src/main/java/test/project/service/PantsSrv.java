package test.project.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import test.project.domain.PantsDTO;

public interface PantsSrv {

	void create(PantsDTO dto, String authToken);
	
	void update(PantsDTO dto);
	
	void delete(String pantsId);
	
	List<PantsDTO> findAll();
	
	List<PantsDTO> findAllPants(Pageable pageable);
	
	PantsDTO findByPantsId(String pantsId);
	
	void uploadImage(MultipartFile file, String pantsId);
}
