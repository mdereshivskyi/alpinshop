package test.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.project.domain.UserDTO;
import test.project.domain.request.SigninRequest;
import test.project.domain.response.SigninResponse;
import test.project.service.UserSrv;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private UserSrv userSrv;
	
	@PostMapping("signup")
	public ResponseEntity<Void> registerUser(@RequestBody UserDTO dto) {
		userSrv.save(dto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PostMapping("signin")
	public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest request) {
		String token = userSrv.signin(request.getUsername(), request.getPassword());
		String role = "";
		
		if(token != null) {
			role = userSrv.findByUsername(request.getUsername()).getRole().toString();
		}
		
		return new ResponseEntity<SigninResponse>(new SigninResponse(token, role), HttpStatus.OK);
	}
	

	@GetMapping("check-email")
	public ResponseEntity<Boolean> checkUserEmail(@RequestParam("email") String email){
		
		return new ResponseEntity<Boolean>(userSrv.existsByEmail(email), HttpStatus.OK);
	}
	
}
