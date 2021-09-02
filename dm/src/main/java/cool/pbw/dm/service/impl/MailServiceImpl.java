package cool.pbw.dm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl {
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendSimpleMail(String to,String subject,String content) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom("1911847149@qq.com");
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(content);
			javaMailSender.send(simpleMailMessage);
		}catch (Exception e){
			
		}
	}
}

