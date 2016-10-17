package br.com.fiap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JasperController {
	@RequestMapping("/gerarRelatorio")
	public String gerarRelatorio(){
		return "adasdas";
	}
 
}
