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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Actualizar Estudiante</title>
</head>
<body>
<form:form method="post" modelAttribute="estudiante">
<table>
<tr>
<td><form:label path="dni">DNI</form:label></td>
<td><form:input path="dni"/></td>
</tr>
<tr>
<td><form:label path="nombre">Nombre</form:label></td>
<td><form:input path="nombre"/></td>
</tr>
<tr>
<td><form:label path="licenciatura">Licenciatura</form:label></td>
<td><form:input path="licenciatura"/></td>
</tr>
<tr>
<td><form:label path="curso">Curso</form:label></td>
<td><form:input path="curso"/></td>
</tr>
<tr>
<td><form:label path="correo">Email</form:label></td>
<td><form:input path="correo"/></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="Actualiza Estudiante"></td>
</tr>
</table>
</form:form>

</body>
</html>