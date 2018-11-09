package test.project.domain;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EquipmentDTO {

	@JsonIgnore
	private Long id;
	private String equipmentId;
	private String type;
	private String description;
	private String imageUrl;
	private BigDecimal price;
	private UserDTO user;
}
