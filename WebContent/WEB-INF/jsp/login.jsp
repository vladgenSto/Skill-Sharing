<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery-3.2.1.min.js"></script>
<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/material.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ripples.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estilo.css">
<title>Login</title>
</head>

<body class="fondo">
	<header class="container page-header">
	<h1 style="width: 50%; left: 37%; position: relative;"> <i id="icon" class="fa fa-pencil" style="text-shadow: rgb(179, 43, 43) 0px 0px 0px, rgb(188, 45, 45) 1px 1px 0px, rgb(198, 47, 47) 2px 2px 0px, rgb(207, 50, 50) 3px 3px 0px, rgb(217, 52, 52) 4px 4px 0px, rgb(226, 54, 54) 5px 5px 0px, rgb(236, 56, 56) 6px 6px 0px, rgb(245, 59, 59) 7px 7px 0px; font-size: 31px; color: rgb(255, 255, 255); height: 53px; width: 53px; line-height: 53px; border-radius: 19%; text-align: center; background-color: rgb(255, 61, 61);"></i> 
		Skill Sharing </h1>
	</header>
	<!--Mask-->

	<div class="container">
		<div class="row" id="home">

			<!--First column-->

			<div class="col-lg-6">
				<div class="description">
					<h2 class="h2-responsive wow fadeInLeft" style="font-weight: bold;">Descripcion:</h2>
					<hr class="hr-light wow fadeInLeft">
					<h4 class="h4:first-letter">La siguiente plataforma esta
						impulsada por el Consejo de de Estudiante de la Universitat Jaume
						I para promover la colaboracion altruista entre los estudiantes y
						mejorar los resultados y el aprendizaje de aquellos que necesitan
						soporte en alguna materia o competencia transversal.</h4>
				</div>
			</div>
			<!--/.First column-->
			<br> <br>

			<!--Second column-->
			<div class="col-lg-5">
				<!--Form-->
				<form:form class="card wow fadeInRight" method="post"
					modelAttribute="user"
					action="${pageContext.request.contextPath}/login.html">
					<div class="card-block">
						<!--Header-->
						<div class="text-center">
							<h3>
								<i class="fa fa-lock"></i> Acceder:
							</h3>
							<hr>
							<br>
						</div>

						<!--Body-->
						<div class="md-form">
							<i class="fa fa-user prefix"></i>
							<form:input path="username" type="text" id="form3"
								class="form-control" placeholder="Usuario"></form:input>
							<form:errors path="username" cssClass="error" />

						</div>
						<div class="md-form">
							<i class="fa fa-lock prefix"></i>
							<form:input path="password" type="password" id="form2"
								class="form-control" placeholder="Contrase�a"></form:input>
							<form:errors path="password" cssClass="error" />

						</div>
						<br>
						<div class="text-center">
							<button class="btn btn-primary btn-lg">Entrar&nbsp<i class="fa fa-sign-in" aria-hidden="true"></i></button>
						</div>

					</div>
				</form:form>
				<!--/.Form-->
			</div>
			<!--/Second column-->
		</div>
	</div>
	<!--/.Mask-->
	<footer style="position: relative; top: 85%;">
	<hr>
	<p class="text-muted">
	<img style="width: 10%;" src="${pageContext.request.contextPath}/css/logo-uji.-DESARROLLO-SOCIAL-Y-PAZ.-030.png"> EI1027 - Dise�o e Implementacioon de Sistemas de Informacion &nbsp&nbsp 
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp � 2017 Copyright
	<t:infoPagina/>
	</p>
	</footer>
</body>
</html>


