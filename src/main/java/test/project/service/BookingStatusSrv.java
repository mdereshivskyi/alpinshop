package test.project.service;

import test.project.domain.BootsDTO;

public interface BookingStatusSrv {

	void create(BootsDTO booking);
	
	BootsDTO deleteByBookingStatusId(String bookingStatusId);
	
}
