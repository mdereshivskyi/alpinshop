package test.project.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	@JsonIgnore
	private Long id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private List<ApartmentDTO> apartments;
	
}
