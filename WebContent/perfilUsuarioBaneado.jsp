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
<t:paginabasicaUsuarioBaneado title="Perfil">
<body class="perfil">
		<!--First column-->
		<div class="col-md-3 mb-r">
			<div class="avatar">
				<img style="width: 80%;"
					src="${pageContext.request.contextPath}/css/Morty.JPG">
			</div>


		</div>
		<!--/First column-->
		<!--Secont column-->
		<div class="col-md-9 mb-r">
			<div class="col-md-4 mb-r">
				<h3>${estudiante.nombre}</h3>
			</div>
			<div class="col-md-4 mb-r">
<%-- 				<h4 style="left: 70%; position: relative;">Horas dadas: ${horasDadas}</h4> --%>
<%-- 				<h4 style="left: 70%; position: relative;">Horas recibidas: ${horasRecibidas}</h4> --%>
				<h4 style="left: 85%; color: red; position: relative; font-size: xx-large; font-weight: bold;">Saldo: ${saldo + 20}</h4>
			</div>
			<div class="col-md-12 mb-r">
				<hr>
			</div>
			<br>
			<br>
			<br>
			<br>
			<br>
			<h5>Correo: ${estudiante.correo}</h5>
			<h5>Licenciatura: ${estudiante.licenciatura}</h5>
			<h5>Curso: ${estudiante.curso}</h5>
			<br>
			<h5 style="color: red; font-weight: bold;">Tu saldo es negativo, debido a eso estas baneado y solo puedes ofrecer servicios.</h5>
			<br>
			<table class="table">
				<thead>
					<tr>
						<th>Ofertas</th>
						<th>Demandas</th>
						<th>Colaboraciones</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${numOfertas}
						<td>${numDemandas}
						<td>${numColaboraciones}
					</tr>	
				</tbody>
			</table>

		</div>

</body>
</t:paginabasicaUsuarioBaneado>
</html>