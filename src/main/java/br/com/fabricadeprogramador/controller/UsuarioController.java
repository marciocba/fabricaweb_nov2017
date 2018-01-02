package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;
//http://localhost:8080/fabricaweb/usucontroller.do

@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet{
	public UsuarioController() {
		System.out.println("construtor");
	}
	@Override
	public void init() throws ServletException {
		System.out.println("chamou init");
		super.init();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String acao=req.getParameter("acao");
		if (acao.equalsIgnoreCase("exc")) {
			String id=req.getParameter("id");

			Usuario usu=new Usuario();
			if (id!=null || id.isEmpty()) {
				usu.setId(Integer.parseInt(id));
				UsuarioDAO usuDAO=new UsuarioDAO();
			
				usu=usuDAO.buscaPorID(Integer.parseInt(id));	
				usuDAO.excluir(usu);
			
				//resp.getWriter().print(usu+" excluido com Sucesso");
				resp.sendRedirect("usucontroller.do?acao=lis");
				//resp.sendRedirect("formusuario.html");
			}
			
		}else if(acao.equalsIgnoreCase("lis")) {
			//implementar a lista
			UsuarioDAO usuDAO=new UsuarioDAO();
			List<Usuario> lista=usuDAO.buscaTodos();
			
			/*printe na tela via command 
			for (Usuario u:lista) {
				resp.getWriter().print(u+"<br>");
			}*/
			//resp.getWriter().print(lista);
			
			//passando um obj para jsp
			req.setAttribute("lista", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
			dispatcher.forward(req, resp);
		}else if(acao.equalsIgnoreCase("alt")) {
			String id=req.getParameter("id");
			UsuarioDAO usuDAO=new UsuarioDAO();
			Usuario usu=usuDAO.buscaPorID(Integer.parseInt(id));
			req.setAttribute("usu", usu);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			requestDispatcher.forward(req, resp);
			
		} else if (acao.equalsIgnoreCase("cad")) {
			Usuario usu=new Usuario();
			//usu.setId(0);
			usu.setNome("");
			usu.setLogin("");
			usu.setSenha("");
			req.setAttribute("usu", usu);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			requestDispatcher.forward(req, resp);
		}
		System.out.println("chamou doGet!"+req);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("chamou doPOST!"+req);
		String id=req.getParameter("id");
		String nome=req.getParameter("nome");
		String login=req.getParameter("login");
		String senha=req.getParameter("senha");
		
		Usuario usu=new Usuario();
		if (id!=null && !id.equalsIgnoreCase("")) {
			usu.setId(Integer.parseInt(id));
		}
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		
		System.out.println(usu);
		
		UsuarioDAO usuDAO=new UsuarioDAO();
		usuDAO.salvar(usu);
		
//		resp.getWriter().print("Sucesso");
		resp.sendRedirect("usucontroller.do?acao=lis");

		System.out.println("Sucesso do post!");

	}
	@Override
	public void destroy() {
		System.out.println("destroui");	
		super.destroy();
	}
}
