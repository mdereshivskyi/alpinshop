package test.project.service;

import java.util.List;

import test.project.domain.BagpackDTO;

public interface ApartmentSrv {

	void create(BagpackDTO apartment);
	
	BagpackDTO get(String apartmentId);
	
	List<BagpackDTO> getAll();
	
	void update(BagpackDTO apartment);
	
	void delete(Long id);
	
	BagpackDTO deleteByApartmentId(String apartmentId);
}
