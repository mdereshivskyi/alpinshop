package test.project.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import test.project.domain.BackpackDTO;

public interface BackpackSrv {

	void create(BackpackDTO dto, String authToken);
	
	void update(BackpackDTO dto);
	
	void delete(String backpackId);
	
	List<BackpackDTO> findAll();
	
	List<BackpackDTO> findAllBackpacks(Pageable pageable);
	
	List<BackpackDTO> findByVolume(Long volume);
	
	BackpackDTO findByBackpackId(String backpackId);
	
	void uploadImage(MultipartFile file, String backpackId);
}
