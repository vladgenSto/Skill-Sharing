<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery-3.2.1.min.js"></script>
<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/material.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ripples.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
<title>Pagina Principal</title>
</head>
<body class="fondo">
<header class="container page-header">
		<h1 style="left: 35%; position: relative;"> <i id="icon" class="fa fa-pencil" style="text-shadow: rgb(179, 43, 43) 0px 0px 0px, rgb(188, 45, 45) 1px 1px 0px, rgb(198, 47, 47) 2px 2px 0px, rgb(207, 50, 50) 3px 3px 0px, rgb(217, 52, 52) 4px 4px 0px, rgb(226, 54, 54) 5px 5px 0px, rgb(236, 56, 56) 6px 6px 0px, rgb(245, 59, 59) 7px 7px 0px; font-size: 31px; color: rgb(255, 255, 255); height: 53px; width: 53px; line-height: 53px; border-radius: 19%; text-align: center; background-color: rgb(255, 61, 61);"></i> 
		Skill Sharing </h1>
</header>
<div>
<img style="width: 50%; left: 23%; position: relative;" src="${pageContext.request.contextPath}/css/404743_e2ed_2.jpg">
</div>
<br>
<div>
<a href="${pageContext.request.contextPath}/login.html" style="left: 45%; position: relative;" type="submit" class="btn btn-raised btn-primary">Entrar</a>
</div>
<footer>
	<hr>
	<p class="text-muted">
	EI1027 - Diseño e Implementacioon de Sistemas de Informacion
	</p>
	</footer>
</body>
</html>