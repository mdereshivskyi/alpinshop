package test.project.service;

import test.project.domain.mail.Mail;

public interface EmailSrv {
	
	void sendMessage(Mail mail);
}
