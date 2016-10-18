package br.com.fiap.component;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.dto.BoletoDTO;
import br.com.fiap.entity.BoletoVO;
import br.com.fiap.exceptions.ValidarException;
import br.com.fiap.repository.BoletoRepository;

@Component
public class BoletoComponent extends BaseCRUDComponent<BoletoVO>{

	@Autowired
	private BoletoRepository boletoRepository;
	

	@PostConstruct
	public void setUp() {
		setBaseRepository(boletoRepository);
	}
	
	@Override
	protected void validarCadastro(BoletoVO object) throws ValidarException {
		  
	}

 

	public boolean existeRegistro() {

		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(BoletoVO.class);
		criteria.setProjection(Projections.countDistinct("id"));
		Long registros = ((Number) criteria.uniqueResult()).longValue();
		boolean existe = false;
		if (registros != null && registros == 0L){
			existe = true;
		}
		return existe;
	}
}
