package test.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.project.entity.EquipmentEnt;

@Repository
public interface EquipmentRepos extends JpaRepository<EquipmentEnt, Long>{

	EquipmentEnt findByEquipmentId(String equipmentId);
	
	List<EquipmentEnt> findByType(String type);
	
}
