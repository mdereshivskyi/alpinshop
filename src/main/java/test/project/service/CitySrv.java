package test.project.service;

import java.util.List;

import test.project.domain.HelmetDTO;

public interface CitySrv {
	
	void create(HelmetDTO city);
	
	HelmetDTO get(String cityId);
	
	List<HelmetDTO> getAll();
	
	void update(HelmetDTO city);
	
	void delete(Long id);
	
	HelmetDTO deleteByCityId(String cityId);
}
