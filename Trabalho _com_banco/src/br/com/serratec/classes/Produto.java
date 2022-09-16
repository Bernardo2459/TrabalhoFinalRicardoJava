package br.com.serratec.classes;

public class Produto {
	private int idproduto;
	private String nomeproduto;
	private String data;
	private double custo;
	private double vlunitario;
	private String descricao;
	private String observacao;
	private int idsubcategoria;
	private String dtfabricacao;
	
	public String getDtfabricacao() {
		return dtfabricacao;
	}
	public void setDtfabricacao(String dtfabricacao) {
		this.dtfabricacao = dtfabricacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public int getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}
	public String getNomeproduto() {
		return nomeproduto;
	}
	public void setNomeproduto(String nomeproduto) {
		this.nomeproduto = nomeproduto;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
	public double getVlunitario() {
		return vlunitario;
	}
	public void setVlunitario(double vlunitario) {
		this.vlunitario = vlunitario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getIdsubcategoria() {
		return idsubcategoria;
	}
	public int setIdsubcategoria(int idsubcategoria) {
		this.idsubcategoria = idsubcategoria;
		return idsubcategoria;
	}
}
