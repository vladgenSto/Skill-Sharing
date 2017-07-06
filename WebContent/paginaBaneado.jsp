<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
<title>Usuario bloqueado</title>
</head>
<body class="fondo">
<h1 style="position: relative; left: 12%; top: 20px;">¡Esta bloqueado por dar uso indebido a la plataforma!</h1>
<img style="position: relative; left: 33%; top: 80px;" src="${pageContext.request.contextPath}/css/banned.png">
<br>
<th><a style="position: relative; left: 46%; top: 150px;" href="${pageContext.request.contextPath}/login.html" class="btn btn-raised btn-warning">Volver</a></th>
</body>
</html>