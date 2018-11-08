package test.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import test.project.entity.BagpackEnt;

@Repository
public interface CategoryRepos extends JpaRepository<BagpackEnt, Long>{

	@Query("Select c from CategoryEnt c where c.id = 1")
	List<BagpackEnt> findRoom();
	
	@Query("Select c from CategoryEnt c where c.id = 2")
	List<BagpackEnt> findFlat();
	
}
