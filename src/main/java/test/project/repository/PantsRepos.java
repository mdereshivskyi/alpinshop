package test.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.project.entity.PantsEnt;

@Repository
public interface PantsRepos extends JpaRepository<PantsEnt, Long>{

	PantsEnt findByPantsId(String pantsId);
	
	List<PantsEnt> findBySize(String size);
	
}
