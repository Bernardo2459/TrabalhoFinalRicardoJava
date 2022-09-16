package br.com.serratec.classes;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Pedido {
	private int idpedido;
	private int idcliente;
	private double vltotal;
	private int idproduto;
	private int quantidade;
	private Date data_emissao;
	
	Cliente cliente = new Cliente();
	private ArrayList <PedidoItem> produto = new ArrayList<>();
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public ArrayList<PedidoItem> getPedItem() {
		return this.produto;
	}
	
	/*public void imprimirItensPedido() {
		System.out.println("Produtos do pedido: " + getNumero());
		System.out.println("-----------------------------------");
		for (PedItem pi:produto) {
			System.out.println("Código: "+ pi.getIdProduto());
			System.out.println("Descrição: "+ pi.getDescricao());
			System.out.printf("Quantidade: %,2.3f", pi.getQuantidade());
			System.out.printf("%nPreço: R$ %,2.2f", pi.getVlPreco());
			System.out.printf("%nTotal: R$ %,2.2f", pi.getQuantidade() * pi.getVlPreco());	
			System.out.println("\n-----------------------------------");
		}
	}*/
	
	/*
	 public void adicionarProduto(PedItem itens) {
		PedItem peditem = new PedItem();
		
		peditem.setDescricao(itens.getDescricao());
		peditem.setIdProduto(itens.getIdProduto());
		peditem.setVlCusto(itens.getVlCusto());
		peditem.setVlPreco(itens.getVlPreco());
		peditem.setQuantidade(itens.getQuantidade());
		
		produto.add(peditem);
	}
	 */
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getIdpedido() {
		return idpedido;
	}
	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public int getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}

	public double getVltotal() {
		return vltotal;
	}
	
	public void setVltotal(double vltotal) {
		this.vltotal = vltotal;
	}
	
	public Date getData_emissao() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(this.data_emissao);
		return data_emissao;
	}
	
	public void setData_emissao(Date data_emissao) {
		long timeInMiliSegundos = data_emissao.getTime();
		this.data_emissao = data_emissao;
	}
	
}
