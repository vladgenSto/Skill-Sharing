<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<title>Gestion de Ofertas</title>
</head>
<t:paginabasicaUsuario title="Lista Ofertas">
<jsp:body>
<h1>Lista de Ofertas</h1>
<table class="table table-hover">
<tr>
<th>Descripcion</th>
<th>Fecha de Inicio</th>
<th>Fecha Fin</th>
<th>Nombre de la habilidad</th>
<th>Nivel de la habilidad</th>
</tr>
<c:forEach items="${listaOfertasUsuario}" var="oferta">
<tr>
<th>${oferta.descripcion}</th>
<th>${oferta.fechaInicio}</th>
<th>${oferta.fechaFin}</th>
<th>${oferta.nombreHabilidad}</th>
<th>${oferta.nivelHabilidad}</th>
<th><a href="update/${oferta.codigoOferta}.html" class="teal-text"><i class="fa fa-pencil"></i></a></th>
<th><a href="delete/${oferta.codigoOferta}.html" class="teal-text"><i class="fa fa-remove"></i></a></th>

<th><a href="update/${oferta.codigoOferta}.html" class="btn btn-raised btn-yellow">Editar</a></th>
<th><a href="delete/${oferta.codigoOferta}.html" class="btn btn-raised btn-warning">Borrar</a></th>
</tr>
</c:forEach>
</table>
<a href="add.html" class="btn btn-raised btn-primary">Añadir</a>
</jsp:body>
</t:paginabasicaUsuario>
</html>