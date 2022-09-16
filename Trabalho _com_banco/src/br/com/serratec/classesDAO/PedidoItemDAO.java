package br.com.serratec.classesDAO;

import java.sql.ResultSet;

import br.com.serratec.classes.PedidoItem;
import br.com.serratec.conexao.Conexao;

public class PedidoItemDAO {
	private Conexao conexao;
	private String schema;
	
	public PedidoItemDAO(Conexao conexao, String schema){
		this.conexao = conexao;
		this.schema = schema;
	}
	
	//decidir o que incluir
	public void incluirPedidoItem(PedidoItem pedidoitem) {
		String sql = "insert into " + this.schema + ".pedidoitem " + " (produto, quantidade) " + "values " +
				" ( " + 
					pedidoitem.getProduto()+
					pedidoitem.getQtproduto()+
					" ) ";
				conexao.query(sql);
	}
	
	public void alterarPedidoItem(PedidoItem pedidoitem) {
		String sql = "update " +
				this.schema + ".cliente set " +
				"qtproduto = '" + pedidoitem.getQtproduto() + "'" +
				"produto =  '" + pedidoitem.getIdpedidoItem() + "'" +
				"where idcliente = " + pedidoitem.getIdpedido();
		conexao.query(sql);
	}
	
	public PedidoItem selecionarPedidoItem(int idPedidoItem) {
		PedidoItem pedidoitem = new PedidoItem();
		ResultSet tabela;
		
		String sql = " select * " + "from " + this.schema + ".cliente where idcliente = " + idPedidoItem;
		
		tabela = conexao.query(sql);
		
		try {
	
			if (tabela.next()) {
				pedidoitem.setIdpedidoItem(tabela.getInt("idpedidoitem"));
				pedidoitem.setIdpedido (tabela.getInt("idpedido"));
				pedidoitem.setQtproduto(tabela.getInt("qtproduto"));
				pedidoitem.setIdproduto(tabela.getInt("idproduto"));
				pedidoitem.setPercdesconto	(tabela.getDouble("percdesconto"));
			} else
				System.out.println("IdPedidoItem " + idPedidoItem + " não localizado.");
			
			tabela.close();
		}catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return pedidoitem;
		
	}
	
	public void apagarPedidoItem(int idPedidoItem) {
		String sql = "delete from * " + this.schema + ".pedidoitem where idpedidoitem = " + idPedidoItem ;
		
		conexao.query(sql);
	}
	
	public void listarPedidoItem() {
		String sql = "select * from " + this.schema + ".pedidoitem";
		
		conexao.query(sql);
	}
}
