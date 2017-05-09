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
<body class="formulario">
<form:form class="form-horizontal" method="post" modelAttribute="habilidad">
  <fieldset>
    <legend>Actualiza Habilidad</legend>
    <div class="form-group">
<!--       <label for="nombre" class="col-md-2 control-label" style="color: black; font-size: medium;">Nombre</label> -->
      <div class="col-md-2">
        <form:input path="nombre" type="text" class="form-control" id="nombre" placeholder="Nombre" hidden="true"></form:input>
      </div>
      <div>
<%--       	<form:errors path="nombre" cssClass="error"/> --%>
      </div>
      </div>
      <div class="form-group">
<!--       <label for="nivel" class="col-md-2 control-label" style="color: black; font-size: medium;">Nivel</label> -->
      <div class="col-md-2">
        <form:input path="nivel" type="text" class="form-control" id="nivel" hidden="true"></form:input>
      </div>
      <div>
<%--       	<form:errors path="nivel" cssClass="error"/> --%>
      </div>
      </div>
      <div class="form-group">
      <label for="descripcion" class="col-md-2 control-label" style="color: black; font-size: medium;">Descripcion</label>
      <div class="col-md-4">
        <form:textarea path="descripcion" type="text" class="form-control" id="descripcion" placeholder="Descripcion"></form:textarea>
        <span class="help-block">Una pequeña descripcion sobre lo que ira la habilidad.</span>
      </div>
      <div>
      	<form:errors path="descripcion" cssClass="error"/>
      </div>
      </div>
    <div class="form-group">
      <div class="col-md-6 col-md-offset-2">
        <button type="reset" class="btn btn-raised btn-warning">Cancel</button>
        <button type="submit" class="btn btn-raised btn-primary">Actualizar</button>
      </div>
    </div>
  </fieldset>
</form:form>
</body>

</html>