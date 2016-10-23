package br.com.fiap.uteis;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class Uteis {
	public static HttpEntity<byte[]>  abrirPdf(byte[] arquivo, String nome){
		

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "pdf"));
	    header.set("Content-Disposition",
	                   "attachment; filename=" + nome);
	    header.setContentLength(arquivo.length);

	    return new HttpEntity<byte[]>(arquivo, header);
		
	}
}
