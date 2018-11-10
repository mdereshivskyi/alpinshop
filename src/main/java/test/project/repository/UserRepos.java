package test.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.project.entity.UserEnt;

@Repository
public interface UserRepos extends JpaRepository<UserEnt, Long>{

	UserEnt findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	UserEnt findByEmailVerificationToken(String token);
	
	
}
