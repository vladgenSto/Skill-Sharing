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
<title>Todas las ofertas</title>
</head>
<t:paginabasicaUsuario>
<body class="fondo">
<script type="text/javascript">
function confirmar(codigoOferta){
	var respuesta=confirm("¿Deseas crear una colaboracion con esta oferta?");
	if(respuesta){
		window.location="../oferta/crearColaboracion/"+codigoOferta+".html";
		}
}
</script>
<h1>Todas las ofertas existentes</h1>
<table class="table table-hover">
<tr>
<th>Descripcion</th>
<th>Fecha de Inicio</th>
<th>Fecha Fin</th>
<th>Estudiante</th>
<th>Nombre de la habilidad</th>
<th>Nivel de la habilidad</th>
<th>Escoge</th>
</tr>
<c:forEach items="${ofertas}" var="listoferta">
<c:forEach items="${listoferta.value}" var="oferta">
<tr>
<th>${oferta.descripcion}</th>
<th><fmt:formatDate value="${oferta.fechaInicio}" pattern="dd/MM/YYYY"/></th>
<th><fmt:formatDate value="${oferta.fechaFin}" pattern="dd/MM/YYYY"/></th>
<th>${listoferta.key}</th>
<th>${oferta.nombreHabilidad}</th>
<th>${oferta.nivelHabilidad}</th>
<th><a href="javascript:confirmar(${oferta.codigoOferta})" class="btn btn-raised btn-primary">Crear colaboracion</a></th>
</tr>
</c:forEach>
</c:forEach>
</table>
</body>
</t:paginabasicaUsuario>
</html>