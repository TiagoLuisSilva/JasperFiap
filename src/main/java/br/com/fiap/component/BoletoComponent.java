package br.com.fiap.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
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
import br.com.fiap.repository.BoletoRepository;
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

 

	public byte[] gerarRelatorio(Long idBoleto,ParametrosRelVO superRelVO) throws Exception{
		superRelVO.setNomeRelatorio("Boleto");
		BoletoVO boleto =  new BoletoVO();
		if(idBoleto !=null){
		    boleto =  boletoRepository.getOne(idBoleto);
		} 
		boleto.setLocalPagamento("PAGAVEL EM QUALQUER AGENCIA BANCARIA");
		boleto.setCnpjBeneficiario("50.485.215/0001-18");
		boleto.setCateira("BS");
		boleto.setEspecie("RS");
		superRelVO.getObjetos().clear();
		superRelVO.getObjetos().add(boleto); 
        JRDataSource dataSource = null;
 
		File arquivo = new File(superRelVO.getDiretorioRel()+"/boleto.jasper"); 
	    String string = superRelVO.getDiretorioRel().substring(1, superRelVO.getDiretorioRel().length());
	    InputStream jasperStream = getClass().getResourceAsStream("/relatorios/boleto.jasper");
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

	public void gerarMassaDeDados() {
	   
	}
}
