<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Todas las demandas</title>
</head>
<t:paginabasicaUsuario>
<body class="fondo">
<h1>Todas las demandas existentes</h1>
<table class="table table-hover">
<tr>
<th>Descripcion</th>
<th>Fecha de Inicio</th>
<th>Fecha Fin</th>
<th>Estudiante</th>
<th>Nombre de la habilidad</th>
<th>Nivel de la habilidad</th>
<%-- <th>Escoge</th> --%>
</tr>
<c:forEach items="${demandas}" var="listdemanda">
<c:forEach items="${listdemanda.value}" var="demanda">
<tr>
<th>${demanda.descripcion}</th>
<th><fmt:formatDate value="${demanda.fechaInicio}" pattern="dd/MM/YYYY"/></th>
<th><fmt:formatDate value="${demanda.fechaFin}" pattern="dd/MM/YYYY"/></th>
<th>${listdemanda.key}</th>
<th>${demanda.nombreHabilidad}</th>
<th>${demanda.nivelHabilidad}</th>
<%-- <th><a href="${pageContext.request.contextPath}/oferta/crearColaboracion/${oferta.codigoOferta}.html" class="btn btn-raised btn-primary">Crear colaboracion</a></th> --%>
</tr>
</c:forEach>
</c:forEach>
</table>
</body>
</t:paginabasicaUsuario>
</html>