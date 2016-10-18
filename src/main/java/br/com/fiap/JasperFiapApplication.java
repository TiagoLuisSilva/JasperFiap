package br.com.fiap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

import br.com.fiap.component.ClienteComponent;
import br.com.fiap.repository.BoletoRepository;
import br.com.fiap.repository.ClienteRepository;
import br.com.fiap.repository.NotaFiscalRepository;

@SpringBootApplication
@ConfigurationProperties
public class JasperFiapApplication {
	@Autowired
	private ClienteComponent clienteComponent;
	
	public static void main(String[] args) {
		
		
		SpringApplication.run(JasperFiapApplication.class, args);
	}
	
}
