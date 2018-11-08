package test.project.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingStatusDTO {

	@JsonIgnore
	private Long id;
	private String bookingStatusId;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private List<ApartmentDTO> apartments;
}
