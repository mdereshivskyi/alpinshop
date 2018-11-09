package test.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.project.entity.JacketEnt;

@Repository
public interface JacketRepos extends JpaRepository<JacketEnt, Long>{

	JacketEnt findByJacketId(String jacketId);
	
	List<JacketEnt> findBySize(String size);
	
}
