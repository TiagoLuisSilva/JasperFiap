package br.com.fiap;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.component.BoletoComponent;
import br.com.fiap.component.ClienteComponent;
import br.com.fiap.component.MassaDeDados;
import br.com.fiap.dto.ClienteDTO;
import br.com.fiap.entity.ClienteVO;
import br.com.fiap.exceptions.ValidarException;
import br.com.fiap.repository.ClienteRepository;
 
 

@RestController
public class JasperController {

	@Autowired
	private MassaDeDados massaDeDados;

	@Autowired
	private ClienteComponent clienteComponent;
	
	@PostConstruct
	private void checarRegistros() throws ValidarException{
		if (!massaDeDados.existeRegistro()){
			massaDeDados.gerarMassaDeDados();
		}
		 
	}
	
	@RequestMapping("/gerarRelatorio")
	public String gerarRelatorio(){ 
		
		return "adasdas";
	}
	@RequestMapping(value="/cliente/consultar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  consultarClientes(@RequestBody ClienteDTO clienteDTO) throws ValidarException{
		List<ClienteVO> listaClientes = clienteComponent.buscarPorNome(clienteDTO);
		List<ClienteDTO> listaRetorno = new ArrayList<ClienteDTO>();
		for (ClienteVO cliente : listaClientes){
			ClienteDTO dto = new ClienteDTO(cliente);
			listaRetorno.add(dto);
		}
		return new ResponseEntity<Object>(listaRetorno, null, HttpStatus.OK);
		
	}
	

	@RequestMapping(value="/cliente/consultar", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  consultarClientess() throws ValidarException{
		List<ClienteVO> listaClientes = clienteComponent.buscarPorNome( new ClienteDTO());
		List<ClienteDTO> listaRetorno = new ArrayList<ClienteDTO>();
		for (ClienteVO cliente : listaClientes){
			ClienteDTO dto = new ClienteDTO(cliente);
			listaRetorno.add(dto);
		}
		return new ResponseEntity<Object>(listaRetorno, null, HttpStatus.OK);
		
	}
	
 
}
