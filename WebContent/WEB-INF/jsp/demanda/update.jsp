<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery-3.2.1.min.js"></script>
<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/material.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ripples.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
<title>Actualizar Demanda</title>
</head>
<body class="formulario">
<form:form class="form-horizontal" method="post" modelAttribute="demanda">
  <fieldset>
    <legend>Actualiza Demanda</legend>
    <div class="form-group">
    <form:hidden path="codigoDemanda" value="${demanda.codigoDemanda}"/>
      <label for="descripcion" class="col-md-2 control-label" style="color: black; font-size: medium;">Descripcion</label>

      <div class="col-md-4">
        <form:textarea path="descripcion" class="form-control" rows="3" id="descripcion"></form:textarea>
        <span class="help-block">Una peque�a descripcion sobre lo que ira la demanda.</span>
      </div>
      <div>
      	<form:errors path="descripcion" cssClass="error"/>
      </div>
      </div>
      <div class="form-group">
<!--       <label for="fechaInicio" class="col-md-2 control-label" style="color: black; font-size: medium;">Fecha Inicio</label> -->

      <div class="col-md-2">
        <form:input path="fechaInicio" type="text" class="form-control" id="fechaInicio" hidden="true"></form:input>
<!--         <span class="help-block">(mm/dd/yyyy)</span> -->
      </div>
      <div>
<%--       	<form:errors path="fechaInicio" cssClass="error"/> --%>
      </div>
    </div>
    <div class="form-group">
      <label for="fechaFin" class="col-md-2 control-label" style="color: black; font-size: medium;">Fecha Fin</label>

      <div class="col-md-2">
        <form:input path="fechaFin" type="text" class="form-control" id="fechaFin"></form:input>
        <span class="help-block">(dd/mm/yyyy)</span>
      </div>
      <div>
      	<form:errors path="fechaFin" cssClass="error"/>
      </div>
    </div>
    <div class="form-group">
<!--       <label for="dniEstudiante" class="col-md-2 control-label" style="color: black; font-size: medium;">DNI Estudiante</label> -->

      <div class="col-md-2">
        <form:input path="dniEstudiante" type="text" class="form-control" id="dniEstudiante" placeholder="DNI" value="${user.dniEstudiante}" hidden="true"/>
      </div>
      <div>
<%--       	<form:errors path="dniEstudiante" cssClass="error"/> --%>
      </div>
    </div>
    <div class="form-group">
<!--       <label for="nombreHabilidad" class="col-md-2 control-label" style="color: black; font-size: medium;">Nombre Habilidad</label> -->

      <div class="col-md-2">
        <form:select id="nombre" class="form-control" name="nombre" path="nombreHabilidad" hidden="true">
        	<option>${demanda.nombreHabilidad}</option>
        	<c:forEach items="${listaHabilidades}" var="habilidad">
        	<option>${habilidad.nombre}</option>
        	</c:forEach>
        </form:select>
      </div>
      <div>
<%--       	<form:errors path="nombreHabilidad" cssClass="error"/> --%>
      </div>
    </div>
    <div class="form-group">
      <label for="nivelHabilidad" class="col-md-2 control-label" style="color: black; font-size: medium;">Nivel Habilidad</label>
      <div class="col-md-2">
        <form:select id="nivelHabilidad" class="form-control" name="nivel" path="nivelHabilidad">
        	<option>${demanda.nivelHabilidad}</option>
        	<option>Iniciacion</option>
        	<option>Intermedio</option>
        	<option>Experto</option>
        </form:select>
      </div>
      <div>
      	<form:errors path="nivelHabilidad" cssClass="error"/>
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-6 col-md-offset-2">
        <a href="${pageContext.request.contextPath}/demanda/list.html" class="btn btn-raised btn-warning">Volver</a>
        <button type="reset" class="btn btn-raised btn-default">Limpiar</button>
        <button type="submit" class="btn btn-raised btn-primary">Actualizar</button>
      </div>
    </div>
  </fieldset>
</form:form>
	<footer style="position: relative; top: 85%;">
	<hr>
	<p class="text-muted">
		<img style="width: 10%;"
			src="${pageContext.request.contextPath}/css/logo-uji.-DESARROLLO-SOCIAL-Y-PAZ.-030.png">
		EI1027 - Dise�o e Implementacioon de Sistemas de Informacion
		&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp � 2017 Copyright
		<t:infoPagina />
	</p>
	<br>
	</footer>
</body>
</html>