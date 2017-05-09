<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>     
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery-3.2.1.min.js"></script>
<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/material.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ripples.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
<title>Actualizar Colaboracion</title>
</head>
<t:paginabasicaUsuario>
<body>
<h2>Valora la colaboración</h2><br>
<form:form method="post" modelAttribute="colaboracion">
<table>
<tr>
<td><form:input path="codigoOferta" hidden="true"/></td>
</tr>
<tr>
<td><form:input path="codigoDemanda" hidden="true"/></td>
</tr>
<tr>
<td><form:input path="descripcionOferta" hidden="true"/></td>
</tr>
<tr>
<td><form:input path="descripcionDemanda" hidden="true"/></td>
</tr>
<tr>
<td><form:label path="fechaFin">Fecha de fin</form:label>
<td><form:input path="fechaFin" value="${fechaFin}"/></td>
</tr>
<tr>
<td><form:label path="horas">Horas</form:label>
<td><form:input path="horas"/></td>
</tr>
<tr>
<td><form:label path="puntuacion">Puntuacion</form:label>
<td><form:input path="puntuacion"/></td>
</tr>
<tr>
<td><form:label path="comentarios">Comentarios</form:label>
<td><form:input path="comentarios"/></td>
</tr>
</table>
<br>
<input type="submit" value="Terminar Colaboracion"/>
</form:form>
</body>
</t:paginabasicaUsuario>
</html>