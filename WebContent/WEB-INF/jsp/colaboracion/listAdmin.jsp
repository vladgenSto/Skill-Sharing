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
<title>Lista de Colaboraciones</title>
</head>
<t:paginabasicaAdmin title="Lista de Colaboraciones">
<jsp:body>
<h1>Lista de Colaboraciones</h1>
<table class="table table-hover">
<tr>
<th>Descripcion Oferta</th>
<th>Descripcion Demanda</th>
<th>Horas</th>
<th>Puntuacion</th>
<th>Comentarios</th>
<th>Fecha inicio</th>
<th>Fecha fin</th>
</tr>
<c:forEach items="${colaboraciones}" var="colaboracion">
<tr>
<th>${colaboracion.descripcionOferta}</th>
<th>${colaboracion.descripcionDemanda}</th>
<th>${colaboracion.horas}</th>
<th>${colaboracion.puntuacion}</th>
<th>${colaboracion.comentarios}</th>
<th>${colaboracion.fechaInicio}</th>
<th>${colaboracion.fechaFin}</th>
</tr>
</c:forEach>
</table>
<button type="submit" class="btn btn-raised btn-primary">Estadisticas</button>
</jsp:body>
</t:paginabasicaAdmin>
</html>