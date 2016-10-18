package br.com.fiap.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.entity.ClienteVO;
import br.com.fiap.exceptions.ValidarException;
import br.com.fiap.repository.ClienteRepository;

@Component
public class ClienteComponent extends BaseCRUDComponent<ClienteVO>{

	@Autowired
	private ClienteRepository clienteRepository;
	

	@PostConstruct
	public void setUp() {
		setBaseRepository(clienteRepository);
	}
	
	@Override
	protected void validarCadastro(ClienteVO object) throws ValidarException {
		  
	}

}
