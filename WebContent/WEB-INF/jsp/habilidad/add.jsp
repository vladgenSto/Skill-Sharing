<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<title>Anyadir Habilidad</title>
</head>
<body>
<h2>Nueva Habilidad</h2>
<form:form method="post" modelAttribute="habilidad">
<table class="table">
<tr>
<td><form:label path="nombre">Nombre</form:label></td>
<td><form:input path="nombre"/></td>
<td><form:errors path="nombre" cssClass="error"/></td>
<tr>
<td><form:label path="descripcion">Descripcion</form:label></td>
<td><form:input path="descripcion"/></td>
<td><form:errors path="descripcion" cssClass="error"/></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="Anyadir Habilidad"></td>
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