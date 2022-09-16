package br.com.serratec.classesDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import br.com.serratec.classes.Fornecedor;
import br.com.serratec.classes.Produto;
import br.com.serratec.conexao.Conexao;

public class ProdutoDAO {
	private Conexao conexao;
	private String schema;
	PreparedStatement pInclusao;
	PreparedStatement pAlteracao;
	PreparedStatement pInclusaoFornecedor;
	
	
	public ProdutoDAO(Conexao conexao, String schema) {
		this.conexao = conexao;
		
		prepararSqlInclusao();
		prepararSqlAlteracao();
		prepararSqlInclusaoFornecedor();
	}
	
	
	//-----------------------------SQL DE INCLUSÂO E ALTERAÇÂO---------------------------------------------
	private void prepararSqlInclusao() {
		String sql = "insert into vendas.produto";
		sql = sql + " (nomeProduto, descricao, dtfabricacao, custo, vlunitario)";
		sql = sql + " values ";
		sql = sql + " (?, ?, ?, ?, ?)";
		
		try {
			pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	
	
	private void prepararSqlAlteracao() {
		String sql = "update vendas.produto set";
		sql = sql + " nomeProduto = ?,";
		sql = sql + " descricao = ?,";
		sql = sql + " dtfabricacao = ?,";
		sql = sql + " custo = ?,";
		sql = sql + "vlunitario = ?";
		sql = sql + " where idproduto = ?";
		
		
		try {
			Produto produto = new Produto();
			pAlteracao = conexao.getC().prepareStatement(sql);
			
			pAlteracao.setString(1, produto.getNomeproduto());
			pAlteracao.setString(2, produto.getDescricao());
			pAlteracao.setString(3, produto.getDtfabricacao());
			pAlteracao.setDouble(4, produto.getCusto());
			pAlteracao.setDouble(5, produto.getVlunitario());
			pAlteracao.setInt(6, produto.getIdproduto());
			
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	private void prepararSqlInclusaoFornecedor() {
		String sql = "insert into vendas.prodfor";
		sql = sql + " (idproduto, idfornecedor)";
		sql = sql + " values ";
		sql = sql + " (?, ?)";
		
		try {
			pInclusaoFornecedor = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	//--------------------------------------------------------------------------------------------------
	
	//-------------------------------------PRODUTO----------------------------------------------------
	public boolean existeDescricaoProduto(Produto produto) {
		ResultSet tabela;
		boolean retorno;
		
		String sql = "select descricao from vendas.produto where descricao = '" + produto.getDescricao() +"'";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
					System.out.printf("O produto %s j� est� cadastrado.", produto.getDescricao());
					tabela.close();
					retorno = true;
			} else
				retorno = false;
			
			tabela.close();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return true;
		}
		
		return retorno;
	}
	
	public int incluirProduto(Produto produto) {
		if (!existeDescricaoProduto(produto)) {
			try {						
				pInclusao.setString(1, produto.getNomeproduto());
				pInclusao.setString(2, produto.getDescricao());
				pInclusao.setString(3, produto.getDtfabricacao());
				pInclusao.setDouble(4, produto.getCusto());
				pInclusao.setDouble(5, produto.getVlunitario());
				
				return pInclusao.executeUpdate();
				
			} catch (Exception e) {
				System.err.println(e);
				e.printStackTrace();
				return 0;
			}
		} else 
			return 0;
	}
	
	public int alterarProduto(Produto produto) {
		try {		
			pAlteracao.setString(1, produto.getDescricao());
			pAlteracao.setDouble(2, produto.getVlunitario());
			pAlteracao.setString(3, produto.getObservacao());
			pAlteracao.setInt(4, produto.getIdsubcategoria());
			pAlteracao.setInt(5, produto.getIdproduto());
			
			return pAlteracao.executeUpdate();
			
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public void apagarProduto(int idProduto) {
		String sql = "delete from java.produto";
		sql = sql + " where idproduto = " + idProduto;
		
		conexao.query(sql);		
	}
	
	public Produto selecionarProduto(int idProduto) {
		Produto produto = new Produto();
		ResultSet tabela;
		
		String sql = "select * from vendas.produto where idproduto = " + idProduto;
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				produto.setIdproduto(tabela.getInt("idproduto"));
				produto.setNomeproduto(tabela.getString("NomeProduto"));
				produto.setDescricao(tabela.getString("descricao"));
				produto.setDtfabricacao(tabela.getString("dtfabricacao"));
				produto.setCusto(tabela.getDouble("custo"));
				produto.setVlunitario(tabela.getDouble("vlunitario"));
				
			}
			tabela.close();
		} catch  (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		
		return produto;
	}
	
	public int retornarIdProduto(String descricaoProduto) {
		int idProduto = 0;
		ResultSet tabela;
		
		String sql = "select idproduto from vendas.produto";
		sql = sql + " where upper(descricao) = '" + descricaoProduto +"'";
		
		tabela = conexao.query(sql.toUpperCase());
		
		try {
			if (tabela.next()) {
				idProduto = tabela.getInt("idproduto");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
		
		return idProduto;
	}
	
	/*public void listarProdutos() {
		ResultSet tabela;
		
		String sql = "select ";
		sql = sql + " 	idproduto, ";
		sql = sql + " 	descricao";
		sql = sql + " 	vlunitario,";
		sql = sql + " 	descricao,";
		sql = sql + "	nomeproduto ";
		sql = sql + " from vendas.produto p";
		sql = sql + " order by descricao";
		
		tabela = conexao.query(sql);		
		
		try {
			if (tabela.next()) {
				System.out.println("C�digo\tDescri��o\t\t\t\tPre�o\t\tSubCategoria\tCategoria\tFornecedor");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("N�o h� dados para serem listados.");
				return;
			}
						
			tabela.beforeFirst();
			
			while (tabela.next()) {  			
				String descricao = "";
				String sPreco;
				String sSubCat = "";
				String sCateg = "";
				String sNmFornecedor = "";
				
				
				if (tabela.getString("descricao").length() < 30) {
					for (int i=30; i>tabela.getString("descricao").length(); i--) {
						descricao += " ";
					}					
				}
				descricao = tabela.getString("descricao") + descricao;
				
				sPreco = String.format("%,2.2f", tabela.getDouble("vlunitario"));
				
				String sPrecoEspaco = "";
				
				if (sPreco.length() < 10) {
					for (int i=10; i>sPreco.length(); i--) {
						sPrecoEspaco += " ";
					}					
				}
				sPreco = sPrecoEspaco + sPreco;
				
				if (tabela.getString("desc_subcategoria").length() < 15
						&& tabela.getString("desc_subcategoria") != null) {
					for (int i=15; i>tabela.getString("desc_subcategoria").length(); i--) {
						sSubCat += " ";
					}					
				}
				
				sSubCat = tabela.getString("desc_subcategoria") + sSubCat;
				
				if (tabela.getString("desc_categoria").length() < 15
						&& tabela.getString("desc_categoria") != null) {
					for (int i=15; i>tabela.getString("desc_categoria").length(); i--) {
						sCateg += " ";
					}					
				}
				
				sCateg = tabela.getString("desc_categoria") + sCateg;
				
				if (tabela.getString("nmfornecedor") == null) {
					sNmFornecedor = "";
				} else sNmFornecedor = tabela.getString("nmfornecedor");
				
				System.out.printf("%d\t%s\t\t%s\t%s\t%s\t%s\n", 
						tabela.getInt("idproduto"),
						descricao,
						sPreco,
						sSubCat,
						sCateg,
						sNmFornecedor
						);
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
	}*/
	public void listarProdutos() {
		String sql = "select ";
		sql = sql + " 	idproduto, ";
		sql = sql + " 	descricao, ";
		sql = sql + " 	vlunitario,";
		sql = sql + "   dtfabricacao,";
		sql = sql + "   custo,";
		sql = sql + "	nomeproduto ";
		sql = sql + " from vendas.produto ";
		sql = sql + " order by idproduto";

	
	ResultSet tabela;
	tabela = conexao.query(sql);
	try {
        //Montagem do Cabecalho
        if (tabela.next()) {
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.printf("idProduto\tNome do produto\t Descrição\tData de fabricação\t\tCusto\tValor unitário\n");

        } else {
            System.out.println("Nao ha dados para serem listados.");
            return;
        }

        tabela.beforeFirst();
        

        while (tabela.next()) {

            System.out.printf("%d\t\t%s\t\t%s\t\t%s\t\t\t%f\t\t\t%f\t\t\n", 
                    tabela.getInt("idProduto"),
                    tabela.getString("Nomeproduto"),
                    tabela.getString("Descricao"),
                    tabela.getString("Dtfabricacao"),
                    tabela.getDouble("Custo"),
                    tabela.getDouble("Vlunitario")
                    );
        } 

    } catch (Exception e) {
        System.err.println(e.getMessage());
        e.printStackTrace();
    }
}
	
	//-------------------------------------FORNCEDOR----------------------------------------
	public boolean existeFornecedorParaProduto(String nomeFornecedor, int idproduto) {
		ResultSet tabela;
		boolean retorno;
		
		String sql = "select p.idfornecedor from vendas.prodfor p";
		sql = sql + " left join vendas.fornecedor f on f.idfornecedor = p.idfornecedor";
		sql = sql + " where f.nome = '" + nomeFornecedor +"'";
		sql = sql + " and idproduto = " + idproduto;
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
					System.out.printf("O fornecedor %s j� est� cadastrado para este produto.", nomeFornecedor);
					tabela.close();
					retorno = true;
			} else
				retorno = false;
			
			tabela.close();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return true;
		}
		
		return retorno;
	}
	
	public int incluirFornecedor(Fornecedor fornecedor, Produto produto) {
		
		if (!existeFornecedorParaProduto(fornecedor.getNome(null), produto.getIdproduto())) {
			try {						
				pInclusaoFornecedor.setInt(1, produto.getIdproduto());
				pInclusaoFornecedor.setInt(2, fornecedor.getIdfornecedor());
				
				return pInclusaoFornecedor.executeUpdate();
				
			} catch (Exception e) {
				System.err.println(e);
				e.printStackTrace();
				return 0;
			}			
		} else return 0;
	}
	//------------------------------------------------------------------------------------------
}
