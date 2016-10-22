package br.com.fiap.component;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.entity.BoletoNotaFiscalVO;
import br.com.fiap.entity.NotaFiscalVO;
import br.com.fiap.exceptions.ValidarException;
import br.com.fiap.repository.BoletoNotaFiscalRepository;

@Component
public class BoletoNotaFiscalComponent extends BaseCRUDComponent<BoletoNotaFiscalVO>{

	@Autowired
	private BoletoNotaFiscalRepository notaFiscalRepository;
	

	@PostConstruct
	public void setUp() {
		setBaseRepository(notaFiscalRepository);
	}
	
	@Override
	protected void validarCadastro(BoletoNotaFiscalVO object) throws ValidarException {
		  
	}

 

	public boolean existeRegistro() {

		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(NotaFiscalVO.class);
		criteria.setProjection(Projections.countDistinct("id"));
		Long registros = ((Number) criteria.uniqueResult()).longValue();
		boolean existe = false;
		if (registros != null && registros == 0L){
			existe = true;
		}
		return existe;
	}

	public void gerarMassaDeDados() {
	   
	}
}
