package br.com.fiap.dto;

public class FiltroNotasFiscaisDTO {
	Long notaFiscal;
	boolean geraBoletos;
	
	public Long getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(Long notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	public boolean isGeraBoletos() {
		return geraBoletos;
	}
	public void setGeraBoletos(boolean geraBoletos) {
		this.geraBoletos = geraBoletos;
	}
	 
}
