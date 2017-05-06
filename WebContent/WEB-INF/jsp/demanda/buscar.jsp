<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Ofertas relacionadas</title>
</head>
<body>
<fieldset>
<legend>Lista de Ofertas</legend>
<table class="table table-hover">
<tr>
<th>Descripcion</th>
<th>Fecha de Inicio</th>
<th>Fecha Fin</th>
<th>DNI del estudiante</th>
<th>Nombre de la habilidad</th>
<th>Nivel de la habilidad</th>
</tr>
<c:forEach items="${listaOfertasRelacionadas}" var="oferta">
<tr>
<th>${oferta.descripcion}</th>
<th>${oferta.fechaInicio}</th>
<th>${oferta.fechaFin}</th>
<th>${oferta.dniEstudiante}</th>
<th>${oferta.nombreHabilidad}</th>
<th>${oferta.nivelHabilidad}</th>
<th><a href="update/${demanda.codigoDemanda}.html  "WebContent/WEB-INF/jsp/colaboracion/add.jsp"" class="btn btn-raised btn-yellow">Crear colaboracion</a></th>
<!-- Falta retocar esto  -->
</tr>
</c:forEach>
</table>
</fieldset>
</body>
</html>