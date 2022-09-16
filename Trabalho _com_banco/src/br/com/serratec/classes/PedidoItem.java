package br.com.serratec.classes;

public class PedidoItem {
	private int idpedidoItem;
	private int idpedido;
	private int idproduto;
	private int qtproduto;
	private double desconto;
	private double vlvendaun;
	private double percdesconto;
	private double produto;
	
	
	public int getIdpedidoItem() {
		return idpedidoItem;
	}
	public void setIdpedidoItem(int idpedidoItem) {
		this.idpedidoItem = idpedidoItem;
	}
	public int getIdpedido() {
		return idpedido;
	}
	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}
	public int getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}
	public int getQtproduto() {
		return qtproduto;
	}
	public void setQtproduto(int qtproduto) {
		this.qtproduto = qtproduto;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public double getVlvendaun() {
		return vlvendaun;
	}
	public void setVlvendaun(double vlvendaun) {
		this.vlvendaun = vlvendaun;
	}
	public double getPercdesconto() {
		return percdesconto;
	}
	public void setPercdesconto(double percdesconto) {
		this.percdesconto = percdesconto;
	}
	public double getProduto() {
		return produto;
	}
	public void setProduto(double produto) {
		this.produto = produto;
	}
}
