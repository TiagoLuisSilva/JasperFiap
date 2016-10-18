package br.com.fiap.dto;

import java.io.Serializable;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import br.com.fiap.entity.ClienteVO;


public class ClienteDTO  implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	private Long id; 
	private String nome; 
	private String endereco; 
	private String cidade; 
	private String estado; 
	private String cnpj;

	public ClienteDTO(){
		
	}
	public ClienteDTO(ClienteVO cliente){
		setId(cliente.getId());
		setNome(cliente.getNome());
		setEndereco(cliente.getEndereco());
		setCidade(cliente.getCidade());
		setEstado(cliente.getEstado());
		setCnpj(cliente.getCnpj());
	}
	public  ClienteVO toClienteVO(){
		ClienteVO cliente = new ClienteVO();
		cliente.setId(this.id);
		cliente.setNome(this.nome);
		cliente.setEndereco(this.endereco);
		cliente.setCidade(this.cidade);
		cliente.setEstado(this.estado);
		cliente.setCnpj(this.cnpj);
		
		return cliente;
	}
	
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
	
}
