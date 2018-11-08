package test.project.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SingInRequest {

	private String username;
	private String password;
	
}
