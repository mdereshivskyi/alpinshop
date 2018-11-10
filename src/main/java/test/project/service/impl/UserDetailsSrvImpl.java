package test.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import test.project.entity.UserEnt;
import test.project.repository.UserRepos;

@Service
public class UserDetailsSrvImpl implements UserDetailsService{

	@Autowired
	private UserRepos userRepos;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEnt userEnt = userRepos.findByUsername(username);
		if(userEnt == null) {
			throw new UsernameNotFoundException("User with username '" + username + "' not found");
		}
				
		return User
				.builder()
					.username(userEnt.getUsername())
					.password(userEnt.getPassword())
					.authorities(userEnt.getRole())
				.build();
	}
	
}
