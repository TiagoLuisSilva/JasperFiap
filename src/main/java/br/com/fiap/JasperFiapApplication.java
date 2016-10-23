package br.com.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@ConfigurationProperties
public class JasperFiapApplication {
	 
	public static void main(String[] args) {
		SpringApplication.run(JasperFiapApplication.class, args);
	}
	
}
