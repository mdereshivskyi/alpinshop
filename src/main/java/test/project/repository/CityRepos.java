package test.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.project.entity.JacketEnt;

@Repository
public interface CityRepos extends JpaRepository<JacketEnt, Long>{
	
	JacketEnt findByCityId(String id);
	
	boolean existsByCityId(String cityId);

	JacketEnt deleteByCityId(String cityId);
	
}
