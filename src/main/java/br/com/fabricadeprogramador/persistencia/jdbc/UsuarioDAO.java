package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {
	
	private Connection con=ConexaoFactory.getConnection();
	public void cadastrar(Usuario usu) {
		String sql="insert into usuario (nome,login,senha) "
				+ "values (?,?,md5(?))";
		try {
			PreparedStatement preparador= con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			
			preparador.execute();
			preparador.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void alterar(Usuario usu) {
		String sql="update usuario set nome=?,login=?,senha=md5(?) "
				+ " where id=?";
		
		try 
		{	
			PreparedStatement preparador= con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());					
			preparador.execute();
			preparador.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void excluir(Usuario usu) {
		String sql="delete from usuario "
				+ " where id=?";
		
		try 
		{	
			PreparedStatement preparador= con.prepareStatement(sql);
			preparador.setInt(1, usu.getId());					
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated -- catch block
			e.printStackTrace();
		}
		
	}
	public void salvar(Usuario usu) {
		if (usu.getId()!=null && usu.getId()!=0) {
			this.alterar(usu);
		}else {
			this.cadastrar(usu);
		}
			
	}
	/**
	 * 
	 * @param Id
	 * @return
	 */
	public Usuario buscaPorID(int Id) {
		Usuario usuRetorno=null;
		String sql="select * from usuario where id=?";
		
		try {
			PreparedStatement preparador=con.prepareStatement(sql);
			preparador.setInt(1, Id);
			//retorno da consulta
			ResultSet resultado=preparador.executeQuery();
			//se tem resultado
			if (resultado.next()) {
				//instancia o obj user
				usuRetorno=new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
				return usuRetorno;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return usuRetorno;
	}

	public List<Usuario> buscaTodos() {
		Usuario usuRetorno=null;
		String sql="select * from usuario";
		boolean hasrecord=false;
		List<Usuario> lista=new ArrayList<Usuario>();
		try {
			PreparedStatement preparador=con.prepareStatement(sql);
	
			//retorno da consulta
			ResultSet resultado=preparador.executeQuery();
			//se tem resultado
			while (resultado.next()) {
				hasrecord=true;
				usuRetorno=new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
				lista.add(usuRetorno);
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		if (hasrecord) {
			return lista;	
		}else {
			return null;
		}		
	}
	public Usuario autenticar(Usuario usuConsulta) {
		String sql="select * from usuario where login=? and senha=md5(?)";
		try (PreparedStatement preparador=con.prepareStatement(sql)){
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			
			ResultSet resultado=preparador.executeQuery();
			//se tem resultado
			if (resultado.next()) {
				//instancia o obj user
				usuConsulta=new Usuario();
				usuConsulta.setId(resultado.getInt("id"));
				usuConsulta.setNome(resultado.getString("nome"));
				usuConsulta.setLogin(resultado.getString("login"));
				usuConsulta.setSenha(resultado.getString("senha"));
				return usuConsulta;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
