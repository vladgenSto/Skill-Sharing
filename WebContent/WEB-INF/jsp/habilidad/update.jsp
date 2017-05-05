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
<title>Actualizar Habilidad</title>
</head>
<body>
<form:form method="post" modelAttribute="habilidad">
<table>
<tr>
<td><form:label path="nombre">Nombre</form:label></td>
<td><form:input path="nombre"/></td>
</tr>
<tr>
<td><form:label path="nivel">Nivel</form:label></td>
<td><form:input path="nivel"/></td>
</tr>
<tr>
<td><form:label path="descripcion">Descripcion</form:label></td>
<td><form:input path="descripcion"/></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="Actualizar Habilidad"></td>
</tr>
</table>
</form:form>
<script src="js/jQuery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/material.min.js"></script>
<script src="js/ripples.min.js"></script>
<script>
	$.maaterial.init();
</script>
</body>
</html>