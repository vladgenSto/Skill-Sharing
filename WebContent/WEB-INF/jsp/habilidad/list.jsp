<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery-3.2.1.min.js"></script>
<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/material.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ripples.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
<title>Gestion de Habilidades</title>
</head>
<t:paginabasicaAdmin title="Lista Habilidades">
<jsp:body>
<h1>Lista de Habilidades</h1>
<table class="table table-hover">
<tr>
<th>Nombre</th>
<th>Nivel</th>
<th>Descripcion</th>
</tr>
<c:forEach items="${habilidades}" var="habilidad">
<tr>
<th>${habilidad.nombre}</th>
<th>${habilidad.nivel}</th>
<th>${habilidad.descripcion}</th>
<th><a href="update/${habilidad.nombre}_${habilidad.nivel}.html" class="btn btn-default">Editar</a></th>
<th><a href="delete/${habilidad.nombre}_${habilidad.nivel}.html" class="btn btn-default">Borrar</a></th>
</tr>
</c:forEach>
</table>
<a href="add.html" class="btn btn-default">Anyadir Habilidad</a>
<script src="js/jQuery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/material.min.js"></script>
<script src="js/ripples.min.js"></script>
<script>
	$.maaterial.init();
</script>
</jsp:body>
</t:paginabasicaAdmin>
</html>