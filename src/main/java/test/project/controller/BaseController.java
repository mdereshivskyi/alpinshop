package test.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.project.domain.mail.Mail;
import test.project.service.EmailSrv;
import test.project.service.UserSrv;

@RestController
public class BaseController {

	@Autowired
	private EmailSrv emailSrv;
	
	@Autowired
	private UserSrv userSrv;
	
	@GetMapping("test-message")
	public ResponseEntity<Void> sendTestMessage(@RequestParam("email") String email){
		Mail mail = Mail.builder()
				.to(email)
				.subject("Test mail subject")
				.content("Test content")
				.build();
		
		emailSrv.sendMessage(mail);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("verify")
	public ResponseEntity<String> verifyAccount(@RequestParam("token") String token) {
		userSrv.verifyAccount(token);
		
		return new ResponseEntity<String>("Your account is TRUE", HttpStatus.OK);
	}
	
}
