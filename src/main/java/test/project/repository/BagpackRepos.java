package test.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.project.entity.BagpackEnt;

@Repository
public interface BagpackRepos extends JpaRepository<BagpackEnt, Long>{

	BagpackEnt findByBagpackId(String bagpackId);
	
	List<BagpackEnt> findByVolume(Long volume);
	
}
