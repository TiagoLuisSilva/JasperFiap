package br.com.fiap.component;

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
		if (registros != null && registros == 0L){
			existe = true;
		}
		return existe;
	}
}
