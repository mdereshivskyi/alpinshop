package test.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.project.entity.BootsEnt;

@Repository
public interface ApartmentsRepos extends JpaRepository<BootsEnt, Long>{

	BootsEnt findByApartmentId(String id);
	
	boolean existsByApartmentId(String apartmentId);
	
	BootsEnt deleteByApartmentId(String apartmentId);
	
}
