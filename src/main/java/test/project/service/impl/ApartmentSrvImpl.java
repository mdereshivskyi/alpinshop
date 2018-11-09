package test.project.service.impl;

import static test.project.constants.ErrorMessage.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.project.domain.BagpackDTO;
import test.project.entity.BootsEnt;
import test.project.exceptions.ApartmentNotFoundException;
import test.project.exceptions.ApartmentServiceException;
import test.project.repository.BagpackRepos;
import test.project.service.ApartmentSrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;

@Service
public class ApartmentSrvImpl implements ApartmentSrv{
	
	@Autowired
	private BagpackRepos apartmentRepos;

	@Autowired
	private ObjectMapperUtils objectMapper;

	@Autowired
	private StringUtils stringUtils;
	
	@Override
	public void create(BagpackDTO apartment) {
		String apartmentId = stringUtils.generate();
		if(!apartmentRepos.existsByApartmentId(apartmentId)) {
			apartment.setApartmentId(apartmentId);
			BootsEnt apartmentEnt = objectMapper.map(apartment, BootsEnt.class);
		
			apartmentRepos.save(apartmentEnt);
		} else {
			throw new ApartmentServiceException(RECORD_ALREADY_EXISTS);
		}
		
	}

	@Override
	public BagpackDTO get(String apartmentId) {
		BootsEnt apartmentEnt = apartmentRepos.findByApartmentId(apartmentId);
		if(apartmentEnt == null) {
			throw new ApartmentNotFoundException(NO_RECORD_FOUND);
		}return objectMapper.map(apartmentEnt, BagpackDTO.class);
	}

	@Override
	public List<BagpackDTO> getAll() {
		List<BootsEnt> apartments = apartmentRepos.findAll();
		return objectMapper.mapAll(apartments, BagpackDTO.class);
	}

	@Override
	public void update(BagpackDTO apartment) {
		apartmentRepos.save(objectMapper.map(apartment, BootsEnt.class));
		
	}

	@Override
	public void delete(Long id) {
		apartmentRepos.deleteById(id);
		
	}

	@Override
	public BagpackDTO deleteByApartmentId(String apartmentId) {
		BootsEnt apartmentEnt = apartmentRepos.deleteByApartmentId(apartmentId);
		if(apartmentEnt == null) {
			throw new ApartmentNotFoundException(NO_RECORD_FOUND);
		}
		return objectMapper.map(apartmentId, BagpackDTO.class);
	}

}
