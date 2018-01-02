<%@page import="java.util.List"%>
<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>lista de user</title>
<script type="text/javascript">
function confirmaExclusao(id){
	if (window.confirm('Tem certeza')){
		location.href="usucontroller.do?acao=exc&id="+id;
	}
}
</script>
<script type="text/javascript">
	function novo(){
		location.href='usucontroller.do?acao=cad';
	}
</script>
</head>
<body>
<%@include file="menu.jsp" %>
<%
	List<Usuario> lista=(List<Usuario>) request.getAttribute("lista"); 
%>
	<table border="1">
	<tr> 
		<th>Id</th>
		<th>Nome</th>
		<th>Acao</th>
	</tr>

	<%for (Usuario u:lista){ %>
		<tr>
			<td><%out.print(u.getId()); %></td> 
			<td><%=u.getNome() %> </td>
			<td><a href="javascript:confirmaExclusao(<%=u.getId()%>)">Excluir</a>
			|<a href="usucontroller.do?acao=alt&id=<%=u.getId()%>"> Alterar </a></td>
		</tr>
	<%} %>
	</table>
	<input type="button" Value="Novo" onclick="javascript:novo()">
</body>
</html>