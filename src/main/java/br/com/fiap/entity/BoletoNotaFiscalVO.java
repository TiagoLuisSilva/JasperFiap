package br.com.fiap.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
 

@Entity(name="boletonotafiscal")
public class BoletoNotaFiscalVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="boletoid")
	private BoletoVO boleto;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="notafiscal_id")
	private NotaFiscalVO notaFiscal;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BoletoVO getBoleto() {
		return boleto;
	}
	public void setBoleto(BoletoVO boleto) {
		this.boleto = boleto;
	}
	public NotaFiscalVO getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscalVO notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
}
