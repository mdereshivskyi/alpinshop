package test.project.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BagpackDTO {

	@JsonIgnore
	private Long id;
	private String bagpackId;
	private String volume;
	private String color;
	private String description;
	private String imageUrl;
	private BigDecimal price;
	private UserDTO user;
	
}
