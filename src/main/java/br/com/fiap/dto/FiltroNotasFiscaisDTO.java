package br.com.fiap.dto;

public class FiltroNotasFiscaisDTO {
	Long idCliente;
	Long idNotaFiscal;
	boolean geraBoletos;
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getIdNotaFiscal() {
		return idNotaFiscal;
	}
	public void setIdNotaFiscal(Long idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
	}
	public boolean isGeraBoletos() {
		return geraBoletos;
	}
	public void setGeraBoletos(boolean geraBoletos) {
		this.geraBoletos = geraBoletos;
	}
	 
	 
}
