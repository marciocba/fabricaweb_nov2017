<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<%
	Usuario usu=(Usuario) request.getAttribute("usu");
%>
	<form action="usucontroller.do" method="post">
		id: <input type="number" name="id" readonly="readonly" value="<%=usu.getId()%>"/>
		Nome: <input type="text" name="nome" value="<%=usu.getNome() %>"/>
		Login: <input type="text" name="login" value="<%=usu.getLogin() %>"/>
		Senha: <input type="password" name="senha" value="<%=usu.getSenha() %>"/>
		
		<input type="submit" value="Salvar">
		
	</form>

</body>
</html>