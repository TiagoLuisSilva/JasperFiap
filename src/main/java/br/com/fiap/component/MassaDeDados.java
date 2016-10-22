package br.com.fiap.component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.entity.BoletoVO;
import br.com.fiap.entity.ClienteVO;
import br.com.fiap.entity.NotaFiscalVO;
import br.com.fiap.exceptions.ValidarException;

@Component
public class MassaDeDados {

	@Autowired
	private ClienteComponent clienteComponent;
	@Autowired
	private BoletoComponent boletoComponent;
	@Autowired
	private NotaFiscalComponent notaFiscalComponent;
	@Autowired
	private BoletoNotaFiscalComponent boletoNotaFiscalComponent;
	
	public void gerarMassaDeDados() throws ValidarException {
		
		try {
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
			
			clienteComponent.persistir(cliente1);
			clienteComponent.persistir(cliente2);
			clienteComponent.persistir(cliente3);
			clienteComponent.persistir(cliente4);
			clienteComponent.persistir(cliente5);
			
			NotaFiscalVO nota = null;
			
			BoletoVO boleto1 = gerarBoleto(cliente1, 1,  2, true);
			BoletoVO boleto2 = gerarBoleto(cliente1, 1,  3, true);;
			List<BoletoVO> listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto1);
			listaBoletos.add(boleto2);
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto1.setNotaFiscal(nota);
			boleto2.setNotaFiscal(nota);
			notaFiscalComponent.persistir(nota); 
			
			BoletoVO boleto3 = gerarBoleto(cliente1, 2,  4, false);
			BoletoVO boleto4 = gerarBoleto(cliente1, 3,  1, true);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto3);
			listaBoletos.add(boleto4);
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto3.setNotaFiscal(nota);
			boleto4.setNotaFiscal(nota); 
			notaFiscalComponent.persistir(nota);
			
			BoletoVO boleto5 = gerarBoleto(cliente2, 1,  2, false);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto5);
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto5.setNotaFiscal(nota); 
			notaFiscalComponent.persistir(nota);
			
			BoletoVO boleto6 = gerarBoleto(cliente2, 0,  6, true);
			BoletoVO boleto7 = gerarBoleto(cliente2, 2,  2, false);
			BoletoVO boleto8 = gerarBoleto(cliente2, 4,  1, true);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto6);
			listaBoletos.add(boleto7);
			listaBoletos.add(boleto8);
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto6.setNotaFiscal(nota);
			boleto7.setNotaFiscal(nota);
			boleto8.setNotaFiscal(nota);
			notaFiscalComponent.persistir(nota); 
			
			BoletoVO boleto9 =  gerarBoleto(cliente2, 5,  1, false);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto9);
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto9.setNotaFiscal(nota); 
			notaFiscalComponent.persistir(nota);
			 
			BoletoVO boleto10 = gerarBoleto(cliente2, 2,  2, true);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto10);
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto10.setNotaFiscal(nota);
			notaFiscalComponent.persistir(nota); 
			 
			BoletoVO boleto11 = gerarBoleto(cliente3, 1,  2, true);
			BoletoVO boleto12 = gerarBoleto(cliente3, 2,  2, true);
			BoletoVO boleto13 = gerarBoleto(cliente3, 3,  2, true);
			BoletoVO boleto14 = gerarBoleto(cliente3, 4,  2, true);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto11);
			listaBoletos.add(boleto12);
			listaBoletos.add(boleto13);
			listaBoletos.add(boleto14);
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto11.setNotaFiscal(nota);
			boleto12.setNotaFiscal(nota);
			boleto13.setNotaFiscal(nota);
			boleto14.setNotaFiscal(nota); 
			notaFiscalComponent.persistir(nota);
			
			BoletoVO boleto15 = gerarBoleto(cliente3, 1,  2, true);
			BoletoVO boleto16 = gerarBoleto(cliente3, 0,  2, true);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto15);
			listaBoletos.add(boleto16);
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto15.setNotaFiscal(nota);
			boleto16.setNotaFiscal(nota);
			notaFiscalComponent.persistir(nota); 
			
			BoletoVO boleto17 = gerarBoleto(cliente3, 5,  2, true);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto17); 
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto17.setNotaFiscal(nota); 
			notaFiscalComponent.persistir(nota);
			
			BoletoVO boleto18 = gerarBoleto(cliente4, 1,  2, false);
			BoletoVO boleto19 = gerarBoleto(cliente4, 0,  6, true);
			BoletoVO boleto20 = gerarBoleto(cliente4, 2,  2, false);
			BoletoVO boleto21 = gerarBoleto(cliente4, 4,  1, true);
			BoletoVO boleto22 = gerarBoleto(cliente4, 5,  1, false);
			BoletoVO boleto23 = gerarBoleto(cliente4, 2,  2, true);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto18); 
			listaBoletos.add(boleto19); 
			listaBoletos.add(boleto20); 
			listaBoletos.add(boleto21); 
			listaBoletos.add(boleto22); 
			listaBoletos.add(boleto23); 
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto18.setNotaFiscal(nota);
			boleto19.setNotaFiscal(nota);
			boleto20.setNotaFiscal(nota);
			boleto21.setNotaFiscal(nota);
			boleto22.setNotaFiscal(nota);
			boleto23.setNotaFiscal(nota); 
			notaFiscalComponent.persistir(nota);
			
			BoletoVO boleto24 = gerarBoleto(cliente5, 1,  2, true);
			BoletoVO boleto25 = gerarBoleto(cliente5, 2,  2, true);
			BoletoVO boleto26 = gerarBoleto(cliente5, 3,  2, true);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto24); 
			listaBoletos.add(boleto25); 
			listaBoletos.add(boleto26); 
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto24.setNotaFiscal(nota);
			boleto25.setNotaFiscal(nota);
			boleto26.setNotaFiscal(nota);
			notaFiscalComponent.persistir(nota); 
			
			BoletoVO boleto27 = gerarBoleto(cliente5, 4,  2, true);
			BoletoVO boleto28 = gerarBoleto(cliente5, 1,  2, true);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto27); 
			listaBoletos.add(boleto28);  
			nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto27.setNotaFiscal(nota);
			boleto28.setNotaFiscal(nota);
			notaFiscalComponent.persistir(nota); 
			
			BoletoVO boleto29 = gerarBoleto(cliente5, 0,  2, true);
			BoletoVO boleto30 = gerarBoleto(cliente5, 5,  2, true);
			listaBoletos = new ArrayList<BoletoVO>();
			listaBoletos.add(boleto29); 
			listaBoletos.add(boleto30);  
		    nota = gerarNotaFiscal(listaBoletos);
			nota.setListaBoletos(listaBoletos);
			boleto29.setNotaFiscal(nota);
			boleto30.setNotaFiscal(nota);
			notaFiscalComponent.persistir(nota); 
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		 
		
		
	}

	private NotaFiscalVO gerarNotaFiscal(List<BoletoVO> listaBoletos) throws ValidarException {
		NotaFiscalVO nota = new NotaFiscalVO();
		ClienteVO cli = listaBoletos.get(0).getCliente();
		nota.setCliente(cli);
		BigDecimal valorNota = BigDecimal.ZERO;
		for (BoletoVO boleto : listaBoletos){
			if (nota.getData() == null){
				nota.setData(boleto.getDataDocumento());
			}
			valorNota = valorNota.add(boleto.getValorCobrado());
		}
		nota.setDiscriminacaoGeral("Nota Fiscal Cliente:" + cli.getNome() );
		nota.setValor(valorNota);
		return nota;
	}

	private BoletoVO gerarBoleto(ClienteVO cliente, Integer diasDataDocumento, Integer diasVencimento, boolean geraMulta) {
		 BoletoVO boleto = new BoletoVO();
		 boleto.setCliente(cliente);
		 
		 Calendar calendar = Calendar.getInstance();
		 calendar.add(Calendar.DAY_OF_MONTH, -diasDataDocumento);
		 boleto.setDataDocumento(calendar.getTime());
		 
		 calendar = Calendar.getInstance();
		 calendar.add(Calendar.DAY_OF_MONTH, diasVencimento);
		 boleto.setDataVencimento(calendar.getTime());
		 
		 boleto.setCodigoDeBarras(gerarCodigoBarras());
		 boleto.setValorDocumento(gerarValor());
		 if (geraMulta){
			 boleto.setMulta(gerarMulta(boleto.getValorDocumento()));
		 }
		 BigDecimal valorCobrado = boleto.getValorDocumento().add(boleto.getMulta());
		 boleto.setValorCobrado(valorCobrado);
		 boleto.setNomeBanco("SANTANDER S.A");
		 boleto.setObservacao("Campo Obeservação ");
		 return boleto;
	}

	private String gerarCodigoBarras() {
		long x = 1234224234523542342L;
		long y = 1134224234523542342L;
		Random r = new Random();
		
		Long number = x+((long)(r.nextDouble()*(y-x)));
		Long  number2 = x+((long)(r.nextDouble()*(y-x)));
		
		return number.toString()  + number2.toString() ;
	}

	private BigDecimal gerarValor() {
		long x = 100L;
		long y = 1000L;
	 
		Random r = new Random();
		Double number = x+((Double)(r.nextDouble()*(y-x)));
		BigDecimal valor = new BigDecimal(number.toString());
		valor = valor.setScale( 2,  RoundingMode.UP);
 
 		return valor; 
	}
	private BigDecimal gerarMulta(BigDecimal valor) {
 		return valor.multiply(new BigDecimal(10)).divide(new BigDecimal(100)); 
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

	public boolean existeRegistro() {
		 
		return clienteComponent.existeRegistro();
	}
}
