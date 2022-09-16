package br.com.serratec.principal;

import java.sql.Timestamp;
import java.util.Scanner;

import br.com.serratec.conexao.*;
import br.com.serratec.classes.*;
import br.com.serratec.classesDAO.*;


public class Principal {

	public static Scanner input = new Scanner(System.in);
	public static final String BD = "joaquim";
	public static final String SCHEMA = "vendas";
	//public static final Conexao con = new Conexao("PostgreSql", "localhost", "5432", "joaquim", "postgres", "2kawhileonard");
	
	public static void main(String[] args) {
		Conexao con = new Conexao("PostgreSql", "localhost", "5432", "joaquim", "postgres", "2kawhileonard");
		
		con.conect();
		menuPrincipal(con, SCHEMA);
		
		con.disconect();
		input.close();
	}
	
	//CRIAÇÂO DO MENU E MENU CRUD--------------------------------------------------------------------
	public static void menuPrincipal(Conexao con, String SCHEMA) {
		int opcao;
		
		do {
			System.out.println("\nMENU PRINCIPAL");
			System.out.println("---------------------");
			System.out.println("0- Sair");
			System.out.println("1- Cliente");
			System.out.println("2- Produto");
			System.out.println("3- Pedido");			
			
			opcao = informeOpcao("Informe uma opção: ");
			
			switch(opcao) {
			case 0: System.out.println("Sistema encerrado."); break;
			case 1: menuCliente(con); break;	
			case 2: menuProduto(con); break;
			case 3: menuPedido(con); break;
			default: System.out.println("Opção inválida.");
			}		
			
		} while (opcao!=0);	
		
	}
	
	public static int informeOpcao(String msg) {
		System.out.print("\n"+ msg);
		String resposta = input.nextLine();
		int opcao;
		
		try {
			opcao = Integer.parseInt(resposta);
		} catch (Exception e) {					
			opcao = 0;
		}
		
		return opcao;
	}
	
	public static void menuCRUD( ) {
		System.out.println("1- Incluir");
		System.out.println("2- Alterar");
		System.out.println("3- Excluir");
		System.out.println("4- Localizar");
		System.out.println("5- Listar");
		System.out.println("6- Voltar");
		System.out.println("7- Encerrar");
	}
	
	//----------------------------------------------------------------------
	
	
	
	//----------------------CLIENTE--------------------
	public static void menuCliente(Conexao con) {
		int opcao;
		
		do {
			menuCRUD();
			opcao = informeOpcao("Informe uma opção: ");
			
			switch(opcao) {			
			case 1: incluirCliente(con); break;
			case 2: alterarCliente(con); break;
			case 3: excluirCliente(con); break;
			case 4: localizarCliente(con); break;
			case 5: listarCliente(con); break;
			case 6: menuPrincipal(con, SCHEMA); break;
			case 7:System.out.println("Programa encerrado");
			default: System.out.println("Opção inválida.");
			}	
		} while (opcao!=7);
	}
	
	public static void excluirCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con, SCHEMA);
		
		int codigo = informeOpcao("\nInforme o código: "); 
		
		Cliente cliente = clienteDAO.selecionarCliente(codigo);
		
		if (cliente != null) {
			clienteDAO.apagarCliente(cliente.getIdcliente());
		}
	}
	
	public static void localizarCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con, SCHEMA);
		
		
		int codigo = informeOpcao("\nInforme o código: "); 
		
		Cliente cliente = clienteDAO.selecionarCliente(codigo);
		
		if (cliente != null) {
			System.out.println(cliente);
			
			
		}
	}
	
	public static void alterarCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con, SCHEMA);
		
		int codigo = informeOpcao("\nInforme o código: "); 
		
		Cliente cliente = clienteDAO.selecionarCliente(codigo);
		Cliente clientenovo = solicitarDadosCliente();
		clientenovo.setIdcliente(cliente.getIdcliente());
		
		if (cliente!=null) {
			clienteDAO.alterarCliente(clientenovo);
		}else {
			System.out.println("O Cliente n existe");
		}
	}

	public static void listarCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con, SCHEMA);
		
		clienteDAO.listarClientes();
	}
	
	public static void incluirCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con, SCHEMA);
		
		Cliente cliente = solicitarDadosCliente();
		
		clienteDAO.incluirCliente2(cliente);
	}
	
	public static Cliente solicitarDadosCliente() {
		Cliente cliente = new Cliente();
		
		
		System.out.println("Dados do cliente");
		System.out.println(Constante.LINHA_FINA);
		System.out.println("Informe o nome: ");
		String nome = input.nextLine();
		
		System.out.println("Informe o CPF: ");
		String cpf = input.nextLine();
		
		System.out.println("Informe o RG: ");
		String rg = input.nextLine();
		
		System.out.println("Informe o sexo: ");
		String sexo = input.nextLine();
		
		System.out.println("Informe o endereço: ");
		String endereco = input.nextLine();
		
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setRg(rg);
		cliente.setSexo(sexo);
		cliente.setEndereco(endereco);
		
		return cliente;
	}
	//------------------------------------------------------------
	
	
	
	//-------------------PRODUTO------------------------
	public static void menuProduto(Conexao con) {
		int opcao;
		
		do {
			menuCRUD();
			opcao = informeOpcao("Informe uma opção: ");
			
			switch(opcao) {			
			case 1: incluirProduto(con); break;
			case 2: alterarProduto(con); break;
			case 3: excluirProduto(con); break;
			case 4: localizarProduto(con); break;
			case 5: listarProduto(con); break;
			case 6: break;
			default: System.out.println("Opção inválida.");
			}	
		} while (opcao!=6);
	}
	
	public static Produto solicitarDadosProduto() {
		Produto produto = new Produto();
		
		System.out.println("Dados do produto");
		System.out.println(Constante.LINHA_FINA);
		System.out.println("Informe o nome do produto: ");
		String nome = input.nextLine();
		
		System.out.println("Informe a descrição do produto: ");
		String descricao = input.nextLine();
		
		System.out.println("Informe a data de fabricação: ");
		String data = input.nextLine();
		
		System.out.println("Informe o custo do produto: ");
		double custo = input.nextDouble();
		
		System.out.println("Informe o valor unitário do produto: ");
		double vlunitario = input.nextDouble();
		
		
		produto.setNomeproduto(nome);
		produto.setDtfabricacao(data);
		produto.setDescricao(descricao);
		produto.setCusto(custo);
		produto.setVlunitario(vlunitario);
		
		return produto;
	}
	
	public static void incluirProduto(Conexao con) {
		ProdutoDAO produtoDAO = new ProdutoDAO(con, SCHEMA);
		
		Produto produto = solicitarDadosProduto();
		
		produtoDAO.incluirProduto(produto);
	}
	
	public static void alterarProduto(Conexao con) {
		ProdutoDAO produtoDAO = new ProdutoDAO(con, SCHEMA);
		
		int codigo = informeOpcao("\nInforme o código: "); 
		
		Produto produto = produtoDAO.selecionarProduto(codigo);
				
		if (produto != null) {
			produtoDAO.alterarProduto(produto);
		}
	}
	
	public static void excluirProduto(Conexao con) {
		ProdutoDAO produtoDAO = new ProdutoDAO(con, SCHEMA);
		
		int codigo = informeOpcao("\nInforme o código: "); 
		
		Produto produto = produtoDAO.selecionarProduto(codigo);
		
		if (produto != null) {
			produtoDAO.apagarProduto(produto.getIdproduto());
		}
	}
	
	public static void localizarProduto(Conexao con) {
		ProdutoDAO produtoDAO = new ProdutoDAO(con, SCHEMA);
		

		int codigo = informeOpcao("\nInforme o código: "); 
		
		Produto produto = produtoDAO.selecionarProduto(codigo);
		
		if (produto != null) {
			System.out.println("Localização do produto");
			System.out.println(Constante.LINHA_FINA);
			System.out.printf("Nome do produto: %s", produto.getNomeproduto());
			System.out.printf("Descrição: %s", produto.getDescricao());
		}
	}
	
	public static void listarProduto(Conexao con) {
		ProdutoDAO produtoDAO = new ProdutoDAO(con, SCHEMA);
		
		produtoDAO.listarProdutos();
	}
	//------------------------------------------------------------
	
	
	
	//---------------------PEDIDO--------------------------------
	public static void menuPedido(Conexao con) {
		int opcao;
		
		do {
			menuCRUD();
			opcao = informeOpcao("Informe uma opção: ");
			
			switch(opcao) {			
			case 1: fazerPedido(con); break;
			case 2: alterarPedido(con); break;
			case 3: excluirPedido(con); break;
			case 4: localizarPedido(con); break;
			case 5: listarPedido(con); break;
			case 6: break;
			default: System.out.println("Opção inválida.");
			}	
		} while (opcao!=6);
	}
	public static Pedido fazerPedido(Conexao con) {
		Pedido pedido = new Pedido();
		
		System.out.println("Faça o seu pedido");
		System.out.println(Constante.LINHA_FINA);
		System.out.println("Informe o id do produto que deseja adquirir: ");
		Integer idproduto = input.nextInt();
		
		System.out.println("Informe a quantidade do produto que deseja comprar: ");
		Integer quantidade = input.nextInt();
		
		pedido.setIdproduto(idproduto);
		pedido.setQuantidade(quantidade);
		
		return pedido;
	}
	
	public static void excluirPedido(Conexao con) {
		PedidoDAO pedidoDAO = new PedidoDAO(con, SCHEMA);
		
		int codigo = informeOpcao("\nInforme o código do pedido: "); 
	
		Pedido pedido = pedidoDAO.selecionarPedido( codigo);
		
		if (pedido != null) {
			pedidoDAO.apagarPedido(pedido.getIdpedido());
		}
	}
	
	public static void alterarPedido(Conexao con) {
		PedidoDAO pedidoDAO = new PedidoDAO(con, SCHEMA);
		
		int codigo = informeOpcao("\nInforme o código: "); 
		
		Pedido pedido = pedidoDAO.selecionarPedido( codigo);
				
		if (pedido != null) {
			pedidoDAO.alterarPedido(pedido);
		}
	}
	
	public static void localizarPedido(Conexao con) {
		PedidoDAO pedidoDAO = new PedidoDAO(con, SCHEMA);
		int codigo = informeOpcao("\nInforme o código: "); 
		
		Pedido pedido = pedidoDAO.selecionarPedido( codigo);
		
		if (pedido != null) {
			System.out.println("Localização do pedido");
			System.out.println(Constante.LINHA_FINA);
			System.out.printf("Nome: %s", pedido.getCliente());
			System.out.printf("Produto: %s", pedido.getIdproduto());
		}
	}
	
	public static void listarPedido(Conexao con) {
		PedidoDAO pedidoDAO = new PedidoDAO(con, SCHEMA);
		
		pedidoDAO.listarPedido();
	}
}
