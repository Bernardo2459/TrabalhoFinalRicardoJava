package br.com.serratec.classesDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.serratec.classes.Pedido;
import br.com.serratec.classes.Utils;
import br.com.serratec.conexao.Conexao;

public class PedidoDAO {
	private Conexao conexao;
	private String schema;
	private PreparedStatement pInclusao;
    private PreparedStatement pAlteracao;
	public PedidoDAO(Conexao conexao, String schema){
		this.conexao = conexao;
		this.schema = schema;
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into " + this.schema + ".pedido "
		 + " (  idcliente, idproduto, idpedido_item)"
		 + " values "
		 + " ( ? , ? , ?)\n";
		
		try {
			pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void incluirPedido(Pedido pedido) {
		prepararSqlInclusao();
		String sql = "insert into " + this.schema + ".pedido " + " (idcliente, idproduto, idpedido_item) " + "values " +
				" ( " + 
					pedido.getIdproduto()+ ", "+
					pedido.getIdcliente()+ ", "+
					pedido.getQuantidade()+
					" ) ";
				conexao.query(sql);
				
		}
	
	public void alterarPedido(Pedido pedido) {
		String sql = "update " +
				this.schema + ".pedido set " +
				"produto = '" + pedido.getIdproduto() + "'" +
				", quantidade = '" + pedido.getQuantidade() + "'" +
				"where idcliente = " + pedido.getIdpedido();
		conexao.query(sql);
	}
	
	public Pedido selecionarPedido(int idPedido) {
		Pedido pedido = new Pedido();
		ResultSet tabela;
		
		String sql = " select * " + "from " + this.schema + ".pedido where idpedido = " + idPedido;
		
		tabela = conexao.query(sql);
		
		try {
	
			if (tabela.next()) {
				pedido.setIdproduto(tabela.getInt("idpedido"));
				pedido.setQuantidade (tabela.getInt("quantidade"));
			} else
				System.out.println("IdPedido " + idPedido + " não localizado.");
			
			tabela.close();
		}catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return pedido;
		
	}
	
	public void apagarPedido(int idPedido) {
		String sql = "delete from * " + this.schema + ".cliente where idcliente = " + idPedido ;
		
		conexao.query(sql);
	}
	
	public void listarPedido() {
		String sql = "select * from " + this.schema + ".pedido";
		
		ResultSet tabela;
		tabela = conexao.query(sql);
		try {
            //Montagem do Cabecalho
            if (tabela.next()) {
                System.out.println("-------------------------------------------------------------------------------------------");
                System.out.printf("idPedido\tidCliente\t\tidProduto\t\tidPedidoItem\tValorTotal\tdata_emissao\n");

            } else {
                System.out.println("Nao ha dados para serem listados.");
                return;
            }

            tabela.beforeFirst();
            

            if (tabela.next()) {

                System.out.printf("%d\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t\t%s\t\t\n", 
                        tabela.getInt("idPedido"),
                        tabela.getInt("idCliente"),
                        tabela.getInt("idProduto"),
                        tabela.getString("idpedido_item"),
                        tabela.getString("vltotal"),
                        tabela.getString("data_emissao")
                        );
            }else {
            	System.out.println("Está vazio");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
	
		
		conexao.query(sql);
	}
	
	public void imprimirPedido(int idcliente) {
		ResultSet tabela;
		
		String sql = "select * from " + this.schema + ".pedido where idcliente = " + idcliente;
		
		tabela = conexao.query(sql);
		
		
		try {
            //Montagem do Cabecalho
            if (tabela.next()) {
                System.out.println("-------------------------------------------------------------------------------------------");
                System.out.printf("idPedido\tidProduto\tidCliente\tValor_Total\t\tData_Emissao\n");

            } else {
                System.out.println("Nao ha dados para serem listados.");
                return;
            }

            tabela.beforeFirst();
            String sPreco = "";

            while (tabela.next()) {

                sPreco = Utils.doubleToString(tabela.getDouble("vlTotal"));

                System.out.printf("%d \t\t %d \t\t %d \t\t %s \t\t %s \n", 
                        tabela.getInt("idPedido"),
                        tabela.getInt("idProduto"),
                        tabela.getInt("idCliente"),
                        sPreco,
                        tabela.getString("dataEmissao")
                        );
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
