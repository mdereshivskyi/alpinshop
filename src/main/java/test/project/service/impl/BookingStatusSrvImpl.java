package test.project.service.impl;

import static test.project.constants.ErrorMessage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.project.domain.BootsDTO;
import test.project.entity.HelmetEnt;
import test.project.exceptions.BookingNotFoundException;
import test.project.exceptions.BookingServiceException;
import test.project.repository.BootsRepos;
import test.project.service.BookingStatusSrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;
@Service
public class BookingStatusSrvImpl implements BookingStatusSrv{

	@Autowired
	private BootsRepos bookingStatusRepos;
	
	@Autowired
	private ObjectMapperUtils objectMapper;

	@Autowired
	private StringUtils stringUtils;
	
	@Override
	public void create(BootsDTO booking) {
		String bookingStatusId = stringUtils.generate();
		if(!bookingStatusRepos.existsByBookingStatusID(bookingStatusId)) {
			booking.setBookingStatusId(bookingStatusId);
			HelmetEnt bookingStatusEnt = objectMapper.map(booking, HelmetEnt.class);
			
			bookingStatusRepos.save(bookingStatusEnt);
		} else { 
			throw new BookingServiceException(RECORD_ALREADY_EXISTS);
		}
		
	}

	@Override
	public BootsDTO deleteByBookingStatusId(String bookingStatusId) {
		HelmetEnt bookingStatusEnt = bookingStatusRepos.deleteByBookingStatusId(bookingStatusId);
		if(bookingStatusEnt == null) {
			throw new BookingNotFoundException(NO_RECORD_FOUND);
		}
		return objectMapper.map(bookingStatusId, BootsDTO.class);
	}

}
