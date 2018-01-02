package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fabricadeprogramador.filter.FiltroAutenticacao;
import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet("/autenticador.do")
public class LoginController extends HttpServlet{
	//atributes
	private static final 
		Logger	logger = LoggerFactory.getLogger(LoginController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		logger.info("doGet() method");

		if (session!=null) {
			session.invalidate();
		}
		resp.sendRedirect("login.html");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1)capturando dados da tela
		logger.info("doPost() method");
		String login=req.getParameter("login");
		String senha=req.getParameter("senha");
		//2)colocando dados em objeto usuario
		Usuario usu=new Usuario();
		usu.setLogin(login);
		usu.setSenha(senha);
		//3)consultando se usuario existe no banco
		UsuarioDAO usuDAO=new UsuarioDAO();
		Usuario usuAutenticado = usuDAO.autenticar(usu);
		//4)verificando se usuario foi encontrado
		if (usuAutenticado!=null) {
			//5)colocando usuario na sessao
			HttpSession session = req.getSession();
			session.setAttribute("usuAutenticado", usuAutenticado);
			
			session.setMaxInactiveInterval(60*5);
			//6)redirecionando user para tela principal
			//resp.sendRedirect("usucontroller.do?acao=list");
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);;
		}else {
			resp.getWriter().print("<script>window.alert('nao encontrado'); location.href='login.html';</script>");
		}
	}
}
