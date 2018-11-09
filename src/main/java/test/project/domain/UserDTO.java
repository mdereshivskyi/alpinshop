package test.project.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import test.project.entity.enums.UserRole;

@Data
@NoArgsConstructor
public class UserDTO {

	@JsonIgnore
	private Long id;
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private UserRole role;
	private List<BagpackDTO> apartments;
	
}
