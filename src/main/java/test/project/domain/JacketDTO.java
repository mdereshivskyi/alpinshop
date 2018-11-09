package test.project.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class JacketDTO {

	private Long id;
	private String jacketId;
	private String size;
	private String color;
	private String description;
	private String imageUrl;
	private BigDecimal price;
	private UserDTO user;
}
