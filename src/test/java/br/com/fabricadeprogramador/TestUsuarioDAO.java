package br.com.fabricadeprogramador;

import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		//testCadastrar("maria","maria","123");
		//testAlterar();
		//testExcluir(20);
		//testExcluir(21);
		//testSalvar();
		testBuscaPorID(5);
		//testBuscarTodos();
		//testAutenticar("joao","123");
		//testAutenticar("maria","123");
		
	}
	private static void testAutenticar(String login,String senha) {
		Usuario usu=new Usuario();
		usu.setLogin(login);
		usu.setSenha(senha);
		//dao
		UsuarioDAO usuDAO= new UsuarioDAO();
		Usuario usuRetorno = usuDAO.autenticar(usu);
		System.out.println(usuRetorno);
		
	}
	public static void testCadastrar(String nome,String login,String senha) {
		// Criando usuario
		Usuario usu=new Usuario();
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		//dao
		UsuarioDAO usuDAO= new UsuarioDAO();
		usuDAO.cadastrar(usu);
		
		System.out.println("Cadastrado com Sucesso");	
	}
	public static void testAlterar() {
		// Criando usuario
		Usuario usu=new Usuario();
		usu.setId(1);
		usu.setNome("nome3");
		usu.setLogin("login3");
		usu.setSenha("senha3");
		//dao
		UsuarioDAO usuDAO= new UsuarioDAO();
		usuDAO.alterar(usu);
		
		System.out.println("alterado com Sucesso");
	}
	public static void testExcluir(int Id) {
		Usuario usu=new Usuario();
		usu.setId(Id);
		
		//dao
		UsuarioDAO usuDAO= new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluido com Sucesso");
	}
	public static void testSalvar() {
		Usuario usu=new Usuario();
		//usu.setId(1);
		usu.setNome(">nome4");
		usu.setLogin("loging4");
		usu.setSenha("senha4");

		//dao
		UsuarioDAO usuDAO= new UsuarioDAO();
		usuDAO.salvar(usu);
		
		System.out.println("salvo com Sucesso");
		
	}
	public static void testBuscaPorID(int id) {
		Usuario usu=new Usuario();
		//DAO
		UsuarioDAO usuDAO=new UsuarioDAO();
		usu=usuDAO.buscaPorID(id);
		if (usu==null) {
			System.out.println("Usuario nao existe");
		}else {
			System.out.println("Usurio existe>>"+usu.toString());
		}
	}
	public static void testBuscarTodos() {
		UsuarioDAO usuDAO=new UsuarioDAO();
		List<Usuario> usuLista=usuDAO.buscaTodos();
		
		if (usuLista==null) 
			System.out.println("Banco de dados vazio");
		else {
			for (Usuario u:usuLista) {
				System.out.println(u);
			}
		}
	}
}
