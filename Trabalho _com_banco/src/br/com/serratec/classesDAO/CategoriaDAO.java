package br.com.serratec.classesDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.serratec.classes.Categoria;
import br.com.serratec.conexao.Conexao;

public class CategoriaDAO {
	Conexao conexao;
	PreparedStatement pInclusao;
	PreparedStatement pAlteracao;
	
	public CategoriaDAO (Conexao conexao) {
		this.conexao = conexao;
		
		prepararSqlInclusao();
		prepararSqlAlteracao();
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into java.categoria";
		sql = sql + " (descricao)";
		sql = sql + " values ";
		sql = sql + " (?)";
		
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
	
	public boolean existeDescricaoCategoria(Categoria categoria) {
		ResultSet tabela;
		boolean retorno;
		
		String sql = "select descricao from java.categoria where descricao = '" + categoria.getDescricao() +"'";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
					System.out.printf("A categoria %s j� est� cadastrada.", categoria.getDescricao());
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
	
	public int incluirCategoria(Categoria categoria) {
		if (!existeDescricaoCategoria(categoria)) {
			try {						
				pInclusao.setString(1, categoria.getDescricao());
				
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
			} else
				categoria = null;
			tabela.close();
			
		} catch  (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		
		return categoria;
	}
	
	public int retornarIdCategoria(String descricaoCategoria) {
		int idCategoria = 0;
		ResultSet tabela;
		
		String sql = "select idcategoria from java.categoria";
		sql = sql + " where descricao = '" + descricaoCategoria +"'";
		
		tabela = conexao.query(sql.toUpperCase());
		
		try {
			if (tabela.next()) {
				idCategoria = tabela.getInt("idcategoria");
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
		
		return idCategoria;
	}
}
