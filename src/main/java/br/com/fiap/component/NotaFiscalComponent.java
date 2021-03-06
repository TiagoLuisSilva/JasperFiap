package br.com.fiap.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.entity.BoletoVO;
import br.com.fiap.entity.NotaFiscalVO;
import br.com.fiap.exceptions.ValidarException;
import br.com.fiap.repository.NotaFiscalRepository;
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
public class NotaFiscalComponent extends BaseCRUDComponent<NotaFiscalVO>{

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;
	

	@PostConstruct
	public void setUp() {
		setBaseRepository(notaFiscalRepository);
	}
	
	@Override
	protected void validarCadastro(NotaFiscalVO object) throws ValidarException {
		  
	}
 
    public  byte[] gerarRelatorio(Long idCliente,Long idNota,boolean exibeBoletos,ParametrosRelVO superRelVO) throws Exception{

		superRelVO.setNomeRelatorio("NotaFiscal");
		NotaFiscalVO nota =  new NotaFiscalVO();
		superRelVO.getObjetos().clear();
		if(idNota !=null){
		    nota =  bucarNotaFiscal(idNota);
		    superRelVO.getObjetos().add(nota); 
		} else {
			List<NotaFiscalVO> notas = (List<NotaFiscalVO>) bucarNotaFiscalPorCliente(idCliente);
			 superRelVO.getObjetos().addAll(notas);
		}
		nota.setExibeBoletos(exibeBoletos);
        JRDataSource dataSource = null;
  
	    String string = superRelVO.getDiretorioRel().substring(1, superRelVO.getDiretorioRel().length());
	    InputStream jasperStream = getClass().getResourceAsStream("/relatorios/notaFiscal.jasper");
	    Map<String,Object> params = new HashMap<>();
	    dataSource = new JRBeanCollectionDataSource(superRelVO.getObjetos(), false);
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream); 
	    params.put("SUBREPORT_DIR", "relatorios/");
	    params.put("EXIBIR_BOLETOS", exibeBoletos);
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
	public List<NotaFiscalVO> bucarNotaFiscalPorCliente(Long idCliente) {

		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(NotaFiscalVO.class); 
		criteria.createAlias("cliente", "cliente");
		criteria.add(Restrictions.eq("cliente.id", idCliente));
		
		List<NotaFiscalVO> notas = (List<NotaFiscalVO>) criteria.list();
		 
		return notas;
	}


	public NotaFiscalVO bucarNotaFiscal(Long idNota) {

		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(NotaFiscalVO.class); 
		criteria.add(Restrictions.eq("id", idNota));
		
		NotaFiscalVO nota = (NotaFiscalVO) criteria.uniqueResult();
		
		if (nota.getListaBoletos() !=null && !nota.getListaBoletos().isEmpty()){
			criteria = entityManager.unwrap(Session.class).createCriteria(BoletoVO.class);
			criteria.createAlias("notaFiscal", "notaFiscal");
			criteria.add(Restrictions.eq("notaFiscal.id", nota.getId()));
			nota.setListaBoletos(criteria.list());
		}
		return nota;
	}

}
