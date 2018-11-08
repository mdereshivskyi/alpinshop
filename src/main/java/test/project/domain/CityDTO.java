package test.project.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityDTO {

	@JsonIgnore
	private Long id;
	private String cityId;
	private String name;
	private List<ApartmentDTO> apartments;
}
