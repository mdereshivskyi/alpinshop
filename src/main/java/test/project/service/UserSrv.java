package test.project.service;

import java.util.List;

import test.project.domain.UserDTO;

public interface UserSrv {

	void save(UserDTO dto);
	
	UserDTO findById(Long id);
	
	List<UserDTO> findAll();
	
	boolean existsByUsername(String username);
	
	UserDTO findByUsername(String username);

	boolean existsByEmail(String email);
	
	String signin(String username, String password);
	
	void verifyAccount(String verifyToken);
	
}
