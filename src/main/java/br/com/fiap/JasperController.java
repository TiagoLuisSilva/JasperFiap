package br.com.fiap;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
import br.com.fiap.component.NotaFiscalComponent;
import br.com.fiap.dto.BoletoDTO;
import br.com.fiap.dto.ClienteDTO;
import br.com.fiap.dto.FiltroBoletoDTO;
import br.com.fiap.dto.FiltroNotasFiscaisDTO;
import br.com.fiap.entity.BoletoVO;
import br.com.fiap.entity.ClienteVO;
import br.com.fiap.exceptions.ValidarException;
import br.com.fiap.uteis.ParametrosRelVO;
import br.com.fiap.uteis.Uteis;
 
 

@RestController	
public class JasperController {

	@Autowired
	private MassaDeDados massaDeDados;

	@Autowired
	private ClienteComponent clienteComponent;
	@Autowired
	private BoletoComponent boletoComponent;
	@Autowired
	private NotaFiscalComponent notaFiscalComponent;
	
	@PostConstruct
	private void checarRegistros() throws ValidarException{ 
		if (!massaDeDados.existeRegistro()){
			massaDeDados.gerarMassaDeDados();
		} 
	}
	//////////////////////// CLIENTES ////////////////////////////////////
	@RequestMapping("/cliente/gerarRelatorio")
	public HttpEntity<byte[]>  gerarRelatorio() throws Exception{ 

		 String  url = JasperController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		 ParametrosRelVO parametros = new ParametrosRelVO();
		 parametros.setDiretorioRel(url+"/relatorios");
		 
		 byte[] arquivo = clienteComponent.gerarRelatorio(parametros);
		
		 return Uteis.abrirPdf(arquivo, "RelatorioDeClientes.pdf");
		  
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
	//////////////////////// BOLETOS  ////////////////////////////////////


	@RequestMapping(value="/boleto/consultar", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  consultarBoletos() throws ValidarException{
		List<BoletoVO> listaboletos = boletoComponent.buscarTodos();
		List<BoletoDTO> listaRetorno = new ArrayList<BoletoDTO>();
		for (BoletoVO boleto : listaboletos){
			BoletoDTO dto = new BoletoDTO(boleto);
			listaRetorno.add(dto);
		}
		return new ResponseEntity<Object>(listaRetorno, null, HttpStatus.OK);
		
	}
	@RequestMapping("/boleto/gerarRelatorio")
	public HttpEntity<byte[]>  gerarRelatorioRelatorio() throws Exception{ 

		 String  url = JasperController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		 ParametrosRelVO parametros = new ParametrosRelVO();
		 parametros.setDiretorioRel(url+"/relatorios");
		 
		 byte[] arquivo = boletoComponent.gerarRelatorio(31L,parametros);
		
		 return Uteis.abrirPdf(arquivo, "Boleto.pdf");
		  
	}
 
	@RequestMapping(value="/boleto/gerarRelatorio", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public HttpEntity<byte[]>  gerarRelatorioRelatorioPorBoleto(@RequestBody FiltroBoletoDTO boleto) throws Exception{ 

		 String  url = JasperController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		 ParametrosRelVO parametros = new ParametrosRelVO();
		 parametros.setDiretorioRel(url+"/relatorios");
		 
		 byte[] arquivo = boletoComponent.gerarRelatorio(boleto.getIdBoleto(), parametros);
		
		 return Uteis.abrirPdf(arquivo, "Boleto.pdf"); 
	}

	//////////////////////// NOTAS ////////////////////////////////////
	 
	@RequestMapping(value="/notafiscal/gerarRelatorio", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public HttpEntity<byte[]>  gerarRelatorioNotasFiscais(@RequestBody FiltroNotasFiscaisDTO filtroNotas) throws Exception{ 

		 String  url = JasperController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		 ParametrosRelVO parametros = new ParametrosRelVO();
		 parametros.setDiretorioRel(url+"/relatorios"); 
		 
		 byte[] arquivo = notaFiscalComponent.gerarRelatorio(filtroNotas.getIdCliente(), filtroNotas.getIdNotaFiscal(), filtroNotas.isGeraBoletos(), parametros);
		
		 return Uteis.abrirPdf(arquivo, "NotaFiscal.pdf"); 
	}

	@RequestMapping(value="/notafiscal/gerarRelatorio", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)	
	public HttpEntity<byte[]>  gerarRelatorioNotasFiscaiss() throws Exception{ 

		 String  url = JasperController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		 ParametrosRelVO parametros = new ParametrosRelVO();
		 parametros.setDiretorioRel(url+"/relatorios");
		 
		 byte[] arquivo = notaFiscalComponent.gerarRelatorio(11L, null, true, parametros);
		   
		 return Uteis.abrirPdf(arquivo, "NotaFiscal.pdf"); 
	}
}
