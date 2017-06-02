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
<body class="formulario">
<form:form class="form-horizontal" method="post" modelAttribute="estudiante">
  <fieldset>
    <legend>Actualiza Estudiante</legend>
    <div class="form-group">
      <label for="dni" class="col-md-2 control-label" style="color: black; font-size: medium;">DNI</label>

      <div class="col-md-2">
        <form:input path="dni" type="text" class="form-control" id="dni" placeholder="DNI"></form:input>
      </div>
      <div>
      	<form:errors path="dni" cssClass="error"/>
      </div>
      </div>
      <div class="form-group">
      <label for="nombre" class="col-md-2 control-label" style="color: black; font-size: medium;">Nombre</label>

      <div class="col-md-2">
        <form:input path="nombre" type="text" class="form-control" id="nombre" placeholder="Nombre"></form:input>
      </div>
      <div>
      	<form:errors path="nombre" cssClass="error"/>
      </div>
    </div>
    <div class="form-group">
      <label for="licenciatura" class="col-md-2 control-label" style="color: black; font-size: medium;">Licenciatura</label>

      <div class="col-md-2">
        <form:input path="licenciatura" type="text" class="form-control" id="licenciatura" placeholder="Licenciatura"></form:input>
      </div>
      <div>
      	<form:errors path="licenciatura" cssClass="error"/>
      </div>
    </div>
    <div class="form-group">
      <label for="curso" class="col-md-2 control-label" style="color: black; font-size: medium;">Curso</label>

      <div class="col-md-2">
        <form:input path="curso" type="text" class="form-control" id="curso" placeholder="Curso"/>
      </div>
      <div>
      	<form:errors path="curso" cssClass="error"/>
      </div>
    </div>
    <div class="form-group">
      <label for="correo" class="col-md-2 control-label" style="color: black; font-size: medium;">Correo</label>

      <div class="col-md-2">
      	<form:input path="correo" type="text" class="form-control" id="correo" placeholder="Correo"/>
      </div>
      <div>
     	<form:errors path="correo" cssClass="error"/>	
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-6 col-md-offset-2">
        <button type="submit" class="btn btn-raised btn-primary">Actualizar</button>
        <button type="reset" class="btn btn-raised btn-warning">Cancel</button>
      </div>
    </div>
  </fieldset>
</form:form>
</body>
</html>