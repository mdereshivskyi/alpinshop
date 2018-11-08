package test.project.service;

import java.util.List;

import test.project.domain.CityDTO;

public interface CitySrv {
	
	void create(CityDTO city);
	
	CityDTO get(String cityId);
	
	List<CityDTO> getAll();
	
	void update(CityDTO city);
	
	void delete(Long id);
	
	CityDTO deleteByCityId(String cityId);
}
