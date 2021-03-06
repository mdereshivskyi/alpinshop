package test.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.project.entity.BackpackEnt;

@Repository
public interface BackpackRepos extends JpaRepository<BackpackEnt, Long>{

	BackpackEnt findByBackpackId(String bagpackId);
	
	List<BackpackEnt> findByVolume(Long volume);
	
}
