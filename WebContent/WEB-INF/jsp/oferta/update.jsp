<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Actualizar Oferta</title>
</head>
<body>
<form:form method="post" modelAttribute="oferta">
<table>
<tr>
<td><form:label path="codigoOferta">Codigo Oferta</form:label></td>
<td><form:input path="codigoOferta"/></td>
</tr>
<tr>
<td><form:label path="descripcion">Descripcion</form:label></td>
<td><form:input path="descripcion"/></td>
</tr>
<tr>
<td><form:label path="fechaInicio">Fecha de Inicio</form:label></td>
<td><form:input path="fechaInicio"/></td>
</tr>
<tr>
<td><form:label path="fechaFin">Fecha Fin</form:label></td>
<td><form:input path="fechaFin"/></td>
</tr>
<tr>
<td><form:label path="dniEstudiante">Dni del estudiante</form:label></td>
<td><form:input path="dniEstudiante"/></td>
</tr>
<tr>
<td><form:label path="nombreHabilidad">Nombre de la habilidad</form:label></td>
<td><form:input path="nombreHabilidad"/></td>
</tr>
<tr>
<td><form:label path="nivelHabilidad">Nivel de la habilidad</form:label></td>
<td><form:input path="nivelHabilidad"/></td>
</tr>

<tr>
<td colspan="2"><input type="submit" value="Actualiza Oferta"></td>
</tr>

</table>
</form:form>

</body>
</html>