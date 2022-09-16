package br.com.serratec.classesDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.serratec.classes.Fornecedor;
import br.com.serratec.conexao.Conexao;

public class FornecedorDAO {
	private Conexao conexao; //---
	PreparedStatement pInclusao;
	
	public FornecedorDAO(Conexao conexao) {  //---
		this.conexao = conexao;
		prepararSqlInclusao();
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into java.fornecedor";
		sql = sql + " (nome, cnpj, endereco)";
		sql = sql + " values ";
		sql = sql + " ( ?, ?, ?)";
		
		try {
			pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int incluirFornecedor(Fornecedor fornecedor) {
		try {
			pInclusao.setString(1, fornecedor.getNome(null));
			pInclusao.setString(2, fornecedor.getCpf_cnpj(null));
			pInclusao.setString(3, fornecedor.getEndereco(null));
			
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public Fornecedor localizarFornecedor(String nome, int idFornecedor) {
		Fornecedor fornecedor = new Fornecedor();
		ResultSet tabela;
		String sql;
		
		if (nome == null) {
			sql = "select * from java.fornecedor where idfornecedor = " + idFornecedor;
		} else {
			sql = "select * from java.fornecedor where nome = '" + nome + "'";
		}
		
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {					
				fornecedor.setId(tabela.getInt("idfornecedor"));
				fornecedor.setNome(tabela.getString("nome"));
				fornecedor.setCpf_cnpj(tabela.getString("cnpj"));
				fornecedor.setEndereco(tabela.getString("endereco"));
			} else {
				fornecedor = null;
				System.out.println("\nFornecedor n�o encontrado.");
			}
		} catch  (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return fornecedor;
	}
}
