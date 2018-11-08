package test.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.project.entity.HelmetEnt;

@Repository
public interface BookingStatusRepos extends JpaRepository<HelmetEnt, Long>{

	HelmetEnt findByBookingStatusId(String id);
	
	boolean existsByBookingStatusID(String bookingStatusId);
	
	HelmetEnt deleteByBookingStatusId(String bookingStatusId);
}
