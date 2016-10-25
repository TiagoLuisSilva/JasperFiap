package br.com.fiap.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.fiap.entity.BoletoVO;

public class BoletoDTO  implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;	 
	private String cliente;  
	private Date dataDocumento; 
	private Date dataVencimento; 
	private BigDecimal valorDocumento; 
	private BigDecimal multa; 
	private BigDecimal valorCobrado;

	public BoletoDTO(BoletoVO boleto) {
		 this.id = boleto.getId();
		 this.cliente =boleto.getCliente().getNome();
		 this.dataDocumento = boleto.getDataDocumento();
		 this.dataVencimento = boleto.getDataVencimento();
		 this.valorDocumento = boleto.getValorDocumento();
		 this.multa = boleto.getMulta();
		 this.valorCobrado = boleto.getValorCobrado();
		 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public BigDecimal getMulta() {
		return multa;
	}

	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}

	public BigDecimal getValorCobrado() {
		return valorCobrado;
	}

	public void setValorCobrado(BigDecimal valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

}
