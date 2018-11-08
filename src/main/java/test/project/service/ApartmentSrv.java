package test.project.service;

import java.util.List;

import test.project.domain.ApartmentDTO;

public interface ApartmentSrv {

	void create(ApartmentDTO apartment);
	
	ApartmentDTO get(String apartmentId);
	
	List<ApartmentDTO> getAll();
	
	void update(ApartmentDTO apartment);
	
	void delete(Long id);
	
	ApartmentDTO deleteByApartmentId(String apartmentId);
}
