package test.project.service.impl;

import static test.project.constants.ErrorMessage.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.project.domain.UserDTO;
import test.project.repository.UserRepos;
import test.project.service.UserSrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;

@Service
public class UserSrvImpl implements UserSrv{

	@Autowired
	private UserRepos userRepos;
	
	@Autowired
	private ObjectMapperUtils objectMapper;

	@Autowired
	private StringUtils stringUtils;
	
	@Override
	public void save(UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDTO findByUsername(String username) { 
		return objectMapper.map(userRepos.findByUsername(username), UserDTO.class);
	}

	@Override
	public String signin(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
