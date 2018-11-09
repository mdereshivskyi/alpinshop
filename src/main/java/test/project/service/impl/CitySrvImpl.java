package test.project.service.impl;

import java.util.List;
import static test.project.constants.ErrorMessage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.project.domain.HelmetDTO;
import test.project.entity.JacketEnt;
import test.project.exceptions.CityNotFoundException;
import test.project.exceptions.CityServiceException;
import test.project.repository.CityRepos;
import test.project.service.CitySrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;
@Service
public class CitySrvImpl implements CitySrv{

	@Autowired
	private CityRepos cityRepos;
	
	@Autowired
	private ObjectMapperUtils objectMapper;

	@Autowired
	private StringUtils stringUtils;
	
	@Override
	public void create(HelmetDTO city) {
		String cityId = stringUtils.generate();
		if(!cityRepos.existsByCityId(cityId)) {
			city.setCityId(cityId);
			JacketEnt cityEnt = objectMapper.map(city, JacketEnt.class);
		
			cityRepos.save(cityEnt);
		} else {
			throw new CityServiceException(RECORD_ALREADY_EXISTS);
			
		}
		
	}

	@Override
	public HelmetDTO get(String cityId) {
		JacketEnt cityEnt = cityRepos.findByCityId(cityId);
		if(cityEnt == null) {
			throw new CityNotFoundException(NO_RECORD_FOUND);
		} return objectMapper.map(cityEnt, HelmetDTO.class);
	}

	@Override
	public List<HelmetDTO> getAll() {
		List<JacketEnt> cities = cityRepos.findAll();
		return objectMapper.mapAll(cities, HelmetDTO.class);
	}

	@Override
	public void update(HelmetDTO city) {
		cityRepos.save(objectMapper.map(city, JacketEnt.class));
		
	}

	@Override
	public void delete(Long id) {
		cityRepos.deleteById(id);
	}

	@Override
	public HelmetDTO deleteByCityId(String cityId) {
		JacketEnt cityEnt = cityRepos.deleteByCityId(cityId);
		if(cityEnt == null) {
			throw new CityNotFoundException(NO_RECORD_FOUND);
		}
		return objectMapper.map(cityId, HelmetDTO.class);
	}

}
