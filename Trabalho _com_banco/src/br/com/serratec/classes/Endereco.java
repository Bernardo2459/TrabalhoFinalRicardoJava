package br.com.serratec.classes;

public class Endereco {
	private String cep;
	private String estado;
	private String cidade;
	private String bairro;
	private int numero;
	private String complemento;
	private String referencia;
	
	public Endereco() {
		
	}
	
	
	// Inicio do Get
	
	public String getCEP() {
		return cep;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public String getReferencia() {
		return referencia;
	}
	
	// Inicio do Set
	
	public void setCEP (String cep) {
		this.cep = cep;
	}
	
	public void setEstado (String estado) {
		this.estado = estado;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public void setBairro (String bairro) {
		this.bairro = bairro;
	}
	
	public void setNumero (int numero) {
		this.numero = numero;
	}
	
	public void setComplemento (String complemento) {
		this.complemento = complemento;
	}
	
	public void setReferencia (String referencia) {
		this.referencia = referencia;
	}


	public Endereco getEndereco(Object endereco) {
		// TODO Auto-generated method stub
		return null;
	}
}
