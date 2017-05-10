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
<t:paginabasicaUsuario title="Skill Sharing">
<body class="perfil">
<h2 style="font-weight: bold;">Descripcion:</h2>
<!--First column-->
        <div class="col-md-4 mb-r">
            <div class="avatar">
                <img style="width: 50%;" src="${pageContext.request.contextPath}/css/Morty.JPG">
            </div>
            <h4>Morty</h4>


        </div>
        <!--/First column-->
<br>
<br>
<br>
<br>
<br>
<footer>
	<hr>
	<p class="text-muted">
	EI1027 - Diseño e Implementacioon de Sistemas de Informacion
	</p>
	</footer>
</body>
</t:paginabasicaUsuario>
</html>