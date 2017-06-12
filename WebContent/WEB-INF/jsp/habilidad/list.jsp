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
<th>Editar</th>
<th>Borrar</th>
</tr>
<c:forEach items="${habilidades}" var="habilidad">
<tr>
<th>${habilidad.nombre}</th>
<th>${habilidad.nivel}</th>
<th>${habilidad.descripcion}</th>
<th><a href="update/${habilidad.nombre}_${habilidad.nivel}.html" class="btn btn-success"><i class="fa fa-pencil"></i></a></th>
<th><a href="delete/${habilidad.nombre}_${habilidad.nivel}.html" class="btn btn-danger"><i class="fa fa-remove"></i></a></th>
</tr>
</c:forEach>
</table>
<a href="add.html" class="btn btn-raised btn-primary">Añadir</a>
</jsp:body>
</t:paginabasicaAdmin>
</html>