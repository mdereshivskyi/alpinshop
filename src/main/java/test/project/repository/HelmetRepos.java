package test.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.project.entity.HelmetEnt;

@Repository
public interface HelmetRepos extends JpaRepository<HelmetEnt, Long>{

	HelmetEnt findByHelmetId(String helmetId);
	
	List<HelmetEnt> findBySize(String size);
}
