package com.example.mail;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.domain.MailDto;

@Component
public class MyMailSender {
	
	@Autowired
	private JavaMailSender mailSender;
	
	private static final String FROM_ADDRESS = "kwangjin23@naver.com";
	
	public void sendTextMail(MailDto mailDto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(FROM_ADDRESS);
		message.setTo(mailDto.getAddress());
		message.setSubject(mailDto.getTitle());
		message.setText(mailDto.getMessage());
		
		mailSender.send(message);
	}
	
	// html형식의 내용과 첨부파일 첨부 가능 메일 전송
	public void sendHtmlMail(MailDto mailDto) {
		try {
			MailHandler mailHandler = new MailHandler(mailSender);
			
			// 보내는 사람
			mailHandler.setFrom(FROM_ADDRESS);
			
			// 받는 사람
			mailHandler.setTo(mailDto.getAddress());
			
			// 메일 제목
			mailHandler.setSubject(mailDto.getTitle());
			
			// 메일 내용 (html 문서형식)
			String htmlContent = "<p>" + mailDto.getMessage() + "</p>";
			htmlContent += "<img src='cid:img1'>";
			mailHandler.setText(htmlContent, true);
			
			// 이미지 삽입
			mailHandler.setInline("img1", "static/images/main_img.jpg");
			
			// 첨부 파일
			mailHandler.setAttach("jsp.jpg", "static/026a2fab-3704-4e04-9dc4-578d1ddf548b_OIP.jpg");
			
			// 메일 전송
			mailHandler.send();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}








