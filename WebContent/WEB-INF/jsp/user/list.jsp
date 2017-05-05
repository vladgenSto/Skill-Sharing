<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Area privada</title>
</head>
<t:paginabasicaUsuario title="Lista Usuarios">
<jsp:body>
<h1>Lista de Usuarios del Sistema</h1>
<table>
<tr class="capcalera">
<th>Usuario</th>
</tr>
<c:forEach items="${users}" var="user" varStatus="loop"> 
<tr class="fons">
<td>${user.username}</td>
</tr>
</c:forEach>
</table>
<script src="js/jQuery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/material.min.js"></script>
<script src="js/ripples.min.js"></script>
<script>
	$.maaterial.init();
</script>
</jsp:body>
</t:paginabasicaUsuario>
</html>