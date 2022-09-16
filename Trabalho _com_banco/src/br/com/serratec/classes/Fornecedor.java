package br.com.serratec.classes;

public class Fornecedor {
	private int idproduto;
	private int idfornecedor;
	private String nome;
	private String cnpj;
	private String endereco;
	private String descricao; 
	
	public int getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}
	public int getIdfornecedor() {
		return idfornecedor;
	}
	public void setIdfornecedor(int idfornecedor) {
		this.idfornecedor = idfornecedor;
	}
	public String getNome(String nome) {
		this.nome = nome;
		return nome;
	}
	public String getCpf_cnpj(String cnpj) {
		this.cnpj = cnpj;
		return null;
	}
	public String getEndereco(String endereco) {
		this.endereco = endereco;
		return endereco;
	}
	public void setId(int int1) {
		
		
	}
	public void setNome(String nome) {
		
	}
	public void setCpf_cnpj(String string) {
		
	}
	public void setEndereco(String string) {
		
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
