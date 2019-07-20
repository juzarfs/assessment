package com.assessment.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	public static final String BASE_PATH ="/";
	public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
	
	@Bean
    protected TomcatServletWebServerFactory createTomcatServletWebServerFactory(){
		return new TomcatServletWebServerFactory() ;
    }
	
}
