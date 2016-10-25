package br.com.fiap.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

@Entity(name="notafiscal")
public class NotaFiscalVO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cliente_id")
	private ClienteVO cliente;
	@Column
	private String discriminacaoGeral;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data")	
	private Date data;

    @Column(precision = 15, scale = 2, nullable = false)
	private BigDecimal valor;

	@OneToMany(mappedBy="notaFiscal", cascade=CascadeType.ALL ,fetch=FetchType.LAZY)
	private List<BoletoVO> listaBoletos;

	@Transient
	private boolean exibeBoletos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public String getDiscriminacaoGeral() {
		return discriminacaoGeral;
	}

	public void setDiscriminacaoGeral(String discriminacaoGeral) {
		this.discriminacaoGeral = discriminacaoGeral;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<BoletoVO> getListaBoletos() {
		return listaBoletos;
	}
	public JRDataSource getListaBoletosJr() {

		JRDataSource jr = new JRBeanArrayDataSource(getListaBoletos().toArray());
		return jr;
	}
	public void setListaBoletos(List<BoletoVO> listaBoletos) {
		this.listaBoletos = listaBoletos;
	}

	public boolean isExibeBoletos() {
		return exibeBoletos;
	}

	public void setExibeBoletos(boolean exibeBoletos) {
		this.exibeBoletos = exibeBoletos;
	} 
}
