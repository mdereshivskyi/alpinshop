package test.project.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {

	@JsonIgnore
	private Long id;
	private String categoryName;
	//private List<ApartmentDTO> apartments;
}
