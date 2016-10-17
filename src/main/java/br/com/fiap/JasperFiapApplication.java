package br.com.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties
public class JasperFiapApplication {

	public static void main(String[] args) {
		SpringApplication.run(JasperFiapApplication.class, args);
	}
	
}
