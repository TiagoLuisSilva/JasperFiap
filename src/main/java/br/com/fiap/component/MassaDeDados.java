package br.com.fiap.component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.entity.BoletoVO;
import br.com.fiap.entity.ClienteVO;
import br.com.fiap.exceptions.ValidarException;

@Component
public class MassaDeDados {

	@Autowired
	private ClienteComponent clienteComponent;
	@Autowired
	private BoletoComponent boletoComponent;
	
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
		
		clienteComponent.persistir(cliente1);
		clienteComponent.persistir(cliente2);
		clienteComponent.persistir(cliente3);
		clienteComponent.persistir(cliente4);
		clienteComponent.persistir(cliente5);

		boletoComponent.persistir(gerarBoleto(cliente1, 1,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente1, 1,  3, true));
		boletoComponent.persistir(gerarBoleto(cliente1, 2,  4, false));
		boletoComponent.persistir(gerarBoleto(cliente1, 3,  1, true));

		boletoComponent.persistir(gerarBoleto(cliente2, 1,  2, false));
		boletoComponent.persistir(gerarBoleto(cliente2, 0,  6, true));
		boletoComponent.persistir(gerarBoleto(cliente2, 2,  2, false));
		boletoComponent.persistir(gerarBoleto(cliente2, 4,  1, true));
		boletoComponent.persistir(gerarBoleto(cliente2, 5,  1, false));
		boletoComponent.persistir(gerarBoleto(cliente2, 2,  2, true));
		 
		boletoComponent.persistir(gerarBoleto(cliente3, 1,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente3, 2,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente3, 3,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente3, 4,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente3, 1,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente3, 0,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente3, 5,  2, true));
		 
		boletoComponent.persistir(gerarBoleto(cliente4, 1,  2, false));
		boletoComponent.persistir(gerarBoleto(cliente4, 0,  6, true));
		boletoComponent.persistir(gerarBoleto(cliente4, 2,  2, false));
		boletoComponent.persistir(gerarBoleto(cliente4, 4,  1, true));
		boletoComponent.persistir(gerarBoleto(cliente4, 5,  1, false));
		boletoComponent.persistir(gerarBoleto(cliente4, 2,  2, true));
		
		boletoComponent.persistir(gerarBoleto(cliente5, 1,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente5, 2,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente5, 3,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente5, 4,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente5, 1,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente5, 0,  2, true));
		boletoComponent.persistir(gerarBoleto(cliente5, 5,  2, true));
		
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
