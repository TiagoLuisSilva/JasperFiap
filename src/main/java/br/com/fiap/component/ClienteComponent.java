package br.com.fiap.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.dto.ClienteDTO;
import br.com.fiap.entity.ClienteVO;
import br.com.fiap.exceptions.ValidarException;
import br.com.fiap.repository.ClienteRepository;
import br.com.fiap.uteis.ParametrosRelVO;
import br.com.fiap.uteis.RelatorioUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

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
		if (clienteDTO !=null && !StringUtils.isBlank(clienteDTO.getNome())){
			criteria.add(Restrictions.like("nome", "%"+clienteDTO.getNome()));
		} 
		return criteria.list();
	}

	public byte[] gerarRelatorio(ParametrosRelVO superRelVO) throws Exception{
		superRelVO.setNomeRelatorio("RelatorioDeClientes");

		List<ClienteVO> listaClientes = buscarPorNome(new ClienteDTO());
		
		superRelVO.setObjetos(listaClientes);
        JRDataSource dataSource = null;
 
		 File arquivo = new File(superRelVO.getDiretorioRel()+"/clientes.jasper"); 
		 String string = superRelVO.getDiretorioRel().substring(1, superRelVO.getDiretorioRel().length());
	    InputStream jasperStream = getClass().getResourceAsStream("/relatorios/clientes.jasper");
	    Map<String,Object> params = new HashMap<>();
	    dataSource = new JRBeanCollectionDataSource(superRelVO.getObjetos(), false);
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream); 
	    params.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	 
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
	    
	    String caminhoRelatorio = RelatorioUtils.realizarExportacaoPDF(superRelVO, jasperPrint);
	    File file = new File(caminhoRelatorio);
        byte[] buffer = null;
        InputStream inputStream = null;
        if (file.exists()) {
        	try{
	            inputStream = new FileInputStream(file);
	            buffer = new byte[inputStream.available()];
	            inputStream.read(buffer);
        	}finally{
        		if (inputStream != null) {
        			inputStream.close();
        		}
        	}    
        }
        return buffer;  
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

}
