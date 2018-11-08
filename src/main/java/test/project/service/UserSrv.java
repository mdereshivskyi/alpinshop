package test.project.service;

import java.util.List;

import test.project.domain.UserDTO;

public interface UserSrv {

	void save(UserDTO user);
	
	UserDTO findById(Long  id);
	
	List<UserDTO> findAll();
	
	boolean existsByUsername(String username);
	
	UserDTO findByUsername(String username);
	
	String signin(String username, String password);
	
	
}
