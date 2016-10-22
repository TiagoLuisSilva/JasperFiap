package br.com.fiap.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity(name="boleto")
public class BoletoVO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cliente_id")
	private ClienteVO cliente;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="notafiscal_id")
	private NotaFiscalVO notaFiscal;
	
	@Column
	private String codigoDeBarras;
	@Column
	private String nomeBanco;
	@Column
	private String observacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datadocumento")
	private Date dataDocumento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datavencimento")	
	private Date dataVencimento;

    @Column(precision = 15, scale = 2, nullable = false)
	private BigDecimal valorDocumento;

    @Column(precision = 15, scale = 2, nullable = true)
	private BigDecimal multa;

    @Column(precision = 15, scale = 2, nullable = false)
	private BigDecimal valorCobrado;
	
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ClienteVO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteVO clientte) {
		this.cliente = clientte;
	}
	public NotaFiscalVO getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscalVO notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}
	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}
	public String getNomeBanco() {
		return nomeBanco;
	}
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
		if (valorDocumento == null){
			valorDocumento = BigDecimal.ZERO;
		}
		return valorDocumento;
	}
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	public BigDecimal getMulta() {
		if (multa == null){
			multa = BigDecimal.ZERO;
		}
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
