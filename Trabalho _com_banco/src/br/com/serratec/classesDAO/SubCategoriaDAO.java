package br.com.serratec.classesDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.serratec.classes.Categoria;
import br.com.serratec.classes.SubCategoria;
import br.com.serratec.conexao.Conexao;

public class SubCategoriaDAO {
	Conexao conexao;
	PreparedStatement pInclusao;
	PreparedStatement pAlteracao;
	
	public SubCategoriaDAO (Conexao conexao) {
		this.conexao = conexao;
		
		prepararSqlInclusao();
		prepararSqlAlteracao();
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into java.subcategoria";
		sql = sql + " (descricao, idcategoria)";
		sql = sql + " values ";
		sql = sql + " (?, ?)";
		
		try {
			pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	private void prepararSqlAlteracao() {
		String sql = "update java.categoria set";
		sql = sql + " descricao = ?";
		sql = sql + " where idcategoria = ?";
		
		try {
			pAlteracao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public boolean existeDescricaoSubCategoria(SubCategoria SubCategoria) {
		ResultSet tabela;
		boolean retorno;
		
		String sql = "select descricao from java.subcategoria where descricao = '" + SubCategoria.getDescricao() +"'";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
					System.out.printf("A subcategoria %s j� est� cadastrada.", SubCategoria.getDescricao());
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
	
	public int incluirSubCategoria(SubCategoria subcategoria) {
		if (!existeDescricaoSubCategoria(subcategoria)) {
			try {						
				pInclusao.setString(1, subcategoria.getDescricao());
				pInclusao.setInt(2, subcategoria.getIdCategoria());
				
				return pInclusao.executeUpdate();
				
			} catch (Exception e) {
				System.err.println(e);
				e.printStackTrace();
				return 0;
			}
		} else 
			return 0;
	}
	
	public int alterarCategoria(Categoria categoria) {
		try {		
			pAlteracao.setString(1, categoria.getDescricao());
			pAlteracao.setInt(2, categoria.getIdcategoria());
			
			return pAlteracao.executeUpdate();
			
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public void apagarCategoria(int idCategoria) {
		String sql = "delete from java.categoria";
		sql = sql + " where idcategoria = " + idCategoria;
		
		conexao.query(sql);		
	}
	
	public Categoria selecionarCategoria(int idCategoria) {
		Categoria categoria = new Categoria();
		ResultSet tabela;
		
		String sql = "select * from java.categoria where idcategoria = " + idCategoria;
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) { //---
				categoria.setIdcategoria(tabela.getInt("idcategoria"));
				categoria.setDescricao(tabela.getString("descricao"));
			}
			tabela.close();
		} catch  (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		
		return categoria;
	}
	
	public int retornarIdSubCategoria(String descricaoSubCategoria) {
		int idSubCategoria = 0;
		ResultSet tabela;
		
		String sql = "select idsubcategoria from java.subcategoria";
		sql = sql + " where upper(descricao) = '" + descricaoSubCategoria +"'";
		
		tabela = conexao.query(sql.toUpperCase());
		
		try {
			if (tabela.next()) {
				idSubCategoria = tabela.getInt("idsubcategoria");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
		
		return idSubCategoria;
	}
	
	public void listarSubcategorias() {
		ResultSet tabela;
		
		String sql = "select c.descricao as desccat, ";
		sql = sql + " s.descricao as descsub ";
		sql = sql + " from java.subcategoria s";
		sql = sql + " join java.categoria c on c.idcategoria = s.idcategoria";
		sql = sql + " order by s.idcategoria, s.descricao";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				System.out.println("Categoria\tSubCategoria");
				System.out.println("---------------------------");
			}
			
			tabela.beforeFirst();
			String descCat = "";
			
			while (tabela.next()) {
				if (tabela.getRow() == 1) {
					descCat = tabela.getString("desccat");
				} else if (descCat.contentEquals(tabela.getString("desccat"))) {
						descCat = "";
				} else {
					descCat = tabela.getString("desccat");
					System.out.println("");
					System.out.println("---------------------------");
				}
				
				System.out.printf("%s\n", descCat);
				
				if (!descCat.isBlank()) {
					System.out.println("---------------------------");	
				} 
				System.out.printf("\t\t%s", tabela.getString("descsub"));
				
				descCat = tabela.getString("desccat");
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
	}
}
