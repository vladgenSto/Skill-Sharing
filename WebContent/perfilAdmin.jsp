<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Perfil</title>
</head>
<t:paginabasicaAdmin title="Skill Sharing">
<body class="perfil">
<!--First column-->
        <div class="col-md-3 mb-r">
            <div class="avatar">
                <img style="width: 80%;" src="${pageContext.request.contextPath}/css/Rick_Sanchez.png">
            </div>

        </div>
        <!--/First column-->
        <!--Secont column-->
		<div class="col-md-9 mb-r">
			<h3>${estudiante.nombre}</h3>
			<hr>
			<br>
			<h5>Correo: ${estudiante.correo}</h5>
			<h5>Licenciatura: ${estudiante.licenciatura}</h5>
			<h5>Curso: ${estudiante.curso}</h5>
		</div>

</body>
</t:paginabasicaAdmin>
</html>