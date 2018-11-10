package test.project.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import test.project.domain.JacketDTO;

public interface JacketSrv {

	void create(JacketDTO dto, String authToken);
	
	void update(JacketDTO dto);
	
	void delete(String jacketId);
	
	List<JacketDTO> findAll();
	
	List<JacketDTO> findAllJackets(Pageable pageable);
	
	JacketDTO findByJacketId(String jacketId);
	
	void uploadImage(MultipartFile file, String jacketId);
}
