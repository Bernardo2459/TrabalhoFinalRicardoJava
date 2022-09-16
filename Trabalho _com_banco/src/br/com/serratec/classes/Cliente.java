package br.com.serratec.classes;

public class Cliente {
	private int idcliente;
	private String nome;
	private String endereco;
	private String cpf;
	private String rg;
	private String sexo;
	
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	@Override
	public String toString() {
		return "Nome = " + getNome() + "/ Endereço = " + getEndereco() + "/ Cpf= " + getCpf()
				+ "/ Rg = " + getRg() + "/ Id do cliente = " + getIdcliente() + "/ Sexo = " + getSexo();
	}
	
	
}
