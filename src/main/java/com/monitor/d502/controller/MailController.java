package com.monitor.d502.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
	@Autowired
	MailSender mailSender;
	
	@GetMapping("/send")
	public String send(){
		//建立邮件消息
		SimpleMailMessage mainMessage = new SimpleMailMessage();
		//发送者
		mainMessage.setFrom("ponyliq@163.com");
		//接收者
		mainMessage.setTo("18395397125@163.com");
		//发送的标题
		mainMessage.setSubject("温度异常报警");
		//发送的内容
		mainMessage.setText("您好，上大计算中心D412温度异常，温度34°c");
		mailSender.send(mainMessage);
		return "1";
	}

}
