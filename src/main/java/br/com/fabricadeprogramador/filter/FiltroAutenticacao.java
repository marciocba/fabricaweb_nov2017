package br.com.fabricadeprogramador.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(dispatcherTypes= {DispatcherType.REQUEST}, urlPatterns="/*")
public class FiltroAutenticacao implements Filter{
	//atributes
		private static final 
			Logger	logger = LoggerFactory.getLogger(FiltroAutenticacao.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("ini() method");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("ini() method");
		HttpServletRequest httpRequest= (HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		
		HttpSession session = httpRequest.getSession(false);
		String uri=httpRequest.getRequestURI();
		if (session!=null || uri.lastIndexOf("login.html")!=-1 || 
				uri.lastIndexOf("autenticador.do")!=-1
				) {
			System.out.println("Passout Filtrado "+uri);
			chain.doFilter(request, response);
		}else {
			System.out.println("permissao negada! "+uri);
			httpResponse.sendRedirect("login.html");
		}
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
