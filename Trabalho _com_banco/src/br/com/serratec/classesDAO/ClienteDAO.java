package br.com.serratec.classesDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.serratec.classes.Cliente;
import br.com.serratec.classes.Utils;
import br.com.serratec.conexao.Conexao;

public class ClienteDAO {
	private Conexao conexao;
	private String schema;
	private PreparedStatement pInclusao;
    private PreparedStatement pAlteracao;
	public ClienteDAO(Conexao conexao, String schema){
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusao();
		prepararSqlAlteracao();
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into " + this.schema + ".cliente ";
		sql += " (  Nome, Endereco, rg, cpf, sexo )";
		sql += " values ";
		sql += " ( ? , ? , ?, ?, ?)";
		
		try {
			pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int incluirCliente2(Cliente cliente) {
		//Verificando se ja existe o produto que deseja incluir
			
			
				try {
					//Preenchendo o objeto pInclusao com os dados do produto
					
					pInclusao.setString(1, cliente.getNome());
					pInclusao.setString(2, cliente.getEndereco());
					pInclusao.setString(3, cliente.getRg());
					pInclusao.setString(4, cliente.getCpf());
					pInclusao.setString(5, cliente.getSexo());
					
					//Executando a inclusao do produto no database
					return pInclusao.executeUpdate();
					
				} catch (Exception e) {
					System.err.println(e);
					e.printStackTrace();
					return 0;
				}
			
			
		}

	private void prepararSqlAlteracao() {
		String sql = "update " + this.schema + ".cliente set";
		sql += " Nome = ?,";
		sql += " Endereco = ?, ";
		sql += "RG = ?, ";
		sql += "CPF = ?, ";
		sql += " sexo = ?";
		sql += " where idCliente = ?";
				
		try {
			pAlteracao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int alterarCliente(Cliente cliente) {
		
		
			try {		
				//Preenchendo o objeto pInclusao com os dados do produto
				pAlteracao.setString(1, cliente.getNome());
				pAlteracao.setString(2, cliente.getEndereco());
				pAlteracao.setString(3, cliente.getRg());
				pAlteracao.setString(4, cliente.getCpf());
				pAlteracao.setString(5, cliente.getSexo());
				pAlteracao.setInt(6, cliente.getIdcliente());
				
				
				return pAlteracao.executeUpdate();
			} 
			catch (Exception e) {
				System.err.println(e);
				e.printStackTrace();
				return 0;
			}		
		
	}
	

	
	
	public Cliente selecionarCliente(int idCliente) {
		Cliente cliente = new Cliente();
		ResultSet tabela;
		
		String sql = " select * " + "from " + this.schema + ".cliente where idcliente = " + idCliente;
		
		tabela = conexao.query(sql);
		
		try {
	
			if (tabela.next()) {
				cliente.setIdcliente(tabela.getInt("idcliente"));
				cliente.setNome		(tabela.getString("nome"));
				cliente.setCpf		(tabela.getString("cpf"));
				cliente.setRg		(tabela.getString("rg"));
				cliente.setEndereco	(tabela.getString("endereco"));
			} else
				System.out.println("IdCliente " + idCliente + " não localizado.");
			
			tabela.close();
		}catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return cliente;
		
	}
	
	public void apagarCliente(int idCliente) {
		String sql = "delete from * " + this.schema + ".cliente where idcliente = " + idCliente ;
		
		conexao.query(sql);
	}
	
	public void listarClientes() {
		String sql = "select * from " + this.schema + ".cliente";
		
		ResultSet tabela;
		tabela = conexao.query(sql);
		try {
            //Montagem do Cabecalho
            if (tabela.next()) {
                System.out.println("-------------------------------------------------------------------------------------------");
                System.out.printf("idCliente\tNome\tEndereço\tRG\t\tCPF\tSexo\n");

            } else {
                System.out.println("Nao ha dados para serem listados.");
                return;
            }

            tabela.beforeFirst();
            

            if (tabela.next()) {

                System.out.printf("%d\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t\t%s\t\t\n", 
                        tabela.getInt("idCliente"),
                        tabela.getString("Nome"),
                        tabela.getString("Endereco"),
                        tabela.getString("RG"),
                        tabela.getString("CPF"),
                        tabela.getString("Sexo")
                        );
            }else {
            	System.out.println("Está vazio");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
	}
}
