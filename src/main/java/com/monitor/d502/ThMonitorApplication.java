package com.monitor.d502;

import java.io.IOException;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.monitor.d502.serviceImpl.Server;
import com.monitor.d502.serviceImpl.WebSocketServer;

//测试
@SpringBootApplication
@EnableScheduling
public class ThMonitorApplication {
	public static void main(String[] args) throws UnknownHostException, IOException {
		SpringApplication springApplication = new SpringApplication(ThMonitorApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        WebSocketServer.setApplicationContext(configurableApplicationContext);//解决WebSocket不能注入的问题
	
	}
}
