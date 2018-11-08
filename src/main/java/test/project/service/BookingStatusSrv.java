package test.project.service;

import test.project.domain.BookingStatusDTO;

public interface BookingStatusSrv {

	void create(BookingStatusDTO booking);
	
	BookingStatusDTO deleteByBookingStatusId(String bookingStatusId);
	
}
