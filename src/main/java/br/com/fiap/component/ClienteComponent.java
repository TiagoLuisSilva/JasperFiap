package br.com.fiap.component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.dto.ClienteDTO;
import br.com.fiap.entity.BoletoVO;
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

	public List<ClienteVO> buscarPorNome(ClienteDTO clienteDTO) {

		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(ClienteVO.class);
		criteria.add(Restrictions.like("nome", "%"+clienteDTO.getNome()));
		return criteria.list();
	}

 

	public boolean existeRegistro() {

		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(ClienteVO.class);
		criteria.setProjection(Projections.countDistinct("id"));
		Long registros = ((Number) criteria.uniqueResult()).longValue();
		boolean existe = false;
		if (registros != null && registros != 0L){
			existe = true;
		}
		return existe;
	}

	public void gerarMassaDeDados() throws ValidarException {
		ClienteVO cliente1 = new ClienteVO();
		ClienteVO cliente2 = new ClienteVO();
		ClienteVO cliente3 = new ClienteVO();
		ClienteVO cliente4 = new ClienteVO();
		ClienteVO cliente5 = new ClienteVO();
		
		setNomeCliente(cliente1, "Cliente1");
		setNomeCliente(cliente2, "Cliente2");
		setNomeCliente(cliente3, "Cliente3");
		setNomeCliente(cliente4, "Cliente4");
		setNomeCliente(cliente5, "Cliente5");
		
		setCnpjCliente(cliente1, "45.675.833/0001-90");
		setCnpjCliente(cliente2, "26.279.458/0001-15");
		setCnpjCliente(cliente3, "72.265.537/0001-94");
		setCnpjCliente(cliente4, "64.266.465/0001-71");
		setCnpjCliente(cliente5, "54.246.411/0001-90");
		
		setEnderecoCliente(cliente1,  "Rua 123, 1");
		setEnderecoCliente(cliente2,  "Rua 456, 2");
		setEnderecoCliente(cliente3,  "Rua 789, 3");
		setEnderecoCliente(cliente4,  "Rua 987, 4");
		setEnderecoCliente(cliente5,  "Rua 654, 5");
		
		setCidadeCliente(cliente1, "São Paulo");
		setCidadeCliente(cliente2, "São Paulo");
		setCidadeCliente(cliente3, "São Paulo");
		setCidadeCliente(cliente4, "São Paulo");
		setCidadeCliente(cliente5, "São Paulo");
		
		setEstadoCliente(cliente1, "SP");
		setEstadoCliente(cliente2, "SP");
		setEstadoCliente(cliente3, "SP");
		setEstadoCliente(cliente4, "SP");
		setEstadoCliente(cliente5, "SP");
		
		this.persistir(cliente1);
		this.persistir(cliente2);
		this.persistir(cliente3);
		this.persistir(cliente4);
		this.persistir(cliente5);
		
		gerarBoleto(cliente1, 0,  2);
	}

	private void gerarBoleto(ClienteVO cliente, Integer diasDataDocumento, Integer diasVencimento) {
		 BoletoVO boleto = new BoletoVO();
		 boleto.setCliente(cliente);
		 
		 Calendar calendar = Calendar.getInstance();
		 calendar.add(Calendar.DAY_OF_MONTH, -diasDataDocumento);
		 boleto.setDataDocumento(calendar.getTime());
		 
		 calendar = Calendar.getInstance();
		 calendar.add(Calendar.DAY_OF_MONTH, diasVencimento);
		 boleto.setDataVencimento(calendar.getTime());
	}

	private void setEnderecoCliente(ClienteVO cliente, String endereco) {
		 cliente.setEndereco(endereco);
	}

	private void setEstadoCliente(ClienteVO cliente, String estado) {
		 cliente.setEstado(estado);
	}

	private void setCidadeCliente(ClienteVO cliente, String cidade) {
		cliente.setCidade(cidade);		
	}

	private void setCnpjCliente(ClienteVO cliente, String cnpj) {
	  cliente.setCnpj(cnpj);
	}

	private void setNomeCliente(ClienteVO cliente, String nome) {
	   cliente.setNome(nome);
	}
}
