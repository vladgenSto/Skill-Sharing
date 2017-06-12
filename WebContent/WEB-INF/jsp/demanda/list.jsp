<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>   
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
<title>Gestion de Demandas</title>
</head>
<t:paginabasicaUsuario title="Lista Demandas">
<jsp:body>
<h1>Lista de Demandas</h1>
<table class="table table-hover">
<tr>
<th>Descripcion</th>
<th>Fecha de Inicio</th>
<th>Fecha Fin</th>
<th>Nombre de la habilidad</th>
<th>Nivel de la habilidad</th>
<th>Opciones</th>
</tr>
<c:forEach items="${listaDemandasUsuario}" var="demanda">
<tr>
<th>${demanda.descripcion}</th>
<th><fmt:formatDate value="${demanda.fechaInicio}" pattern="dd/MM/YYYY"/></th>
<th><fmt:formatDate value="${demanda.fechaFin}" pattern="dd/MM/YYYY"/></th>
<th>${demanda.nombreHabilidad}</th>
<th>${demanda.nivelHabilidad}</th>
<th><a href="update/${demanda.codigoDemanda}.html" class="btn btn-success"><i class="fa fa-pencil"></i></a>
<a href="delete/${demanda.codigoDemanda}.html" class="btn btn-danger"><i class="fa fa-remove"></i></a>
<a href="buscar/${demanda.codigoDemanda}.html" class="btn btn-info"><i class="fa fa-search" aria-hidden="true"></i></a></th>
</tr>
</c:forEach>
</table>
<a href="add.html" class="btn btn-raised btn-primary">Añadir</a>
</jsp:body>
</t:paginabasicaUsuario>
</html>