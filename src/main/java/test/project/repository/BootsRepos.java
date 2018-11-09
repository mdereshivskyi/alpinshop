package test.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.project.entity.BootsEnt;

@Repository
public interface BootsRepos extends JpaRepository<BootsEnt, Long>{

	BootsEnt findByBootsId(String bootsId);
	
	List<BootsEnt> findBySize(float size);
}
