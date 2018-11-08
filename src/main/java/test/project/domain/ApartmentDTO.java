package test.project.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApartmentDTO {

	@JsonIgnore
	private Long id;
	private String apartmentId;
	private String name;
	private String descriptio;
	private String imageUrl;
	private BigDecimal price;
	private CityDTO city;
	private CategoryDTO category;
	private BookingStatusDTO booking;
	private UserDTO user;
	
}
