package com.monitor.d502.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.monitor.d502.interceptor.SecurityInterceptor;
import com.monitor.d502.serviceImpl.WebSocketServer;

@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter {
	@Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

	@Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(securityInterceptor()).excludePathPatterns("/static/*").excludePathPatterns("/index")
                .excludePathPatterns("/testWeb").excludePathPatterns("/websocket").addPathPatterns("/**");
    }
    
}
