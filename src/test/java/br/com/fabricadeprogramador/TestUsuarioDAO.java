package br.com.fabricadeprogramador;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		//testCadastrar();
		testAlterar();
		//testExcluir();
		
	}
	public static void testCadastrar() {
		// Criando usuario
		Usuario usu=new Usuario();
		usu.setNome("nome1");
		usu.setLogin("login");
		usu.setSenha("senha");
		//dao
		UsuarioDAO usuDAO= new UsuarioDAO();
		usuDAO.cadastrar(usu);
		
		System.out.println("Cadastrado com Sucesso");	
	}
	public static void testAlterar() {
		// Criando usuario
		Usuario usu=new Usuario();
		usu.setId(1);
		usu.setNome("nome2");
		usu.setLogin("login2");
		usu.setSenha("senha2");
		//dao
		UsuarioDAO usuDAO= new UsuarioDAO();
		usuDAO.alterar(usu);
		
		System.out.println("alterado com Sucesso");
	}
	public static void testExcluir() {
		Usuario usu=new Usuario();
		usu.setId(2);
		
		//dao
		UsuarioDAO usuDAO= new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluido com Sucesso");
	}
}
