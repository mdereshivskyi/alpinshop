package test.project.service.impl;

import static test.project.constants.ErrorMessage.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.project.domain.ApartmentDTO;
import test.project.entity.BootsEnt;
import test.project.exceptions.ApartmentNotFoundException;
import test.project.exceptions.ApartmentServiceException;
import test.project.repository.ApartmentsRepos;
import test.project.service.ApartmentSrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;

@Service
public class ApartmentSrvImpl implements ApartmentSrv{
	
	@Autowired
	private ApartmentsRepos apartmentRepos;

	@Autowired
	private ObjectMapperUtils objectMapper;

	@Autowired
	private StringUtils stringUtils;
	
	@Override
	public void create(ApartmentDTO apartment) {
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
	public ApartmentDTO get(String apartmentId) {
		BootsEnt apartmentEnt = apartmentRepos.findByApartmentId(apartmentId);
		if(apartmentEnt == null) {
			throw new ApartmentNotFoundException(NO_RECORD_FOUND);
		}return objectMapper.map(apartmentEnt, ApartmentDTO.class);
	}

	@Override
	public List<ApartmentDTO> getAll() {
		List<BootsEnt> apartments = apartmentRepos.findAll();
		return objectMapper.mapAll(apartments, ApartmentDTO.class);
	}

	@Override
	public void update(ApartmentDTO apartment) {
		apartmentRepos.save(objectMapper.map(apartment, BootsEnt.class));
		
	}

	@Override
	public void delete(Long id) {
		apartmentRepos.deleteById(id);
		
	}

	@Override
	public ApartmentDTO deleteByApartmentId(String apartmentId) {
		BootsEnt apartmentEnt = apartmentRepos.deleteByApartmentId(apartmentId);
		if(apartmentEnt == null) {
			throw new ApartmentNotFoundException(NO_RECORD_FOUND);
		}
		return objectMapper.map(apartmentId, ApartmentDTO.class);
	}

}
