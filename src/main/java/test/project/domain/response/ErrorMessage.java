package test.project.domain.response;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage {

	private String message;
	private LocalDateTime timestamp;
	
	public ErrorMessage(String message) {
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}
	
	
	
}
