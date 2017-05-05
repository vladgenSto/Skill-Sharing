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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></link>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">

<title>Añadir Oferta</title>
</head>
<body>		
<form:form class="form-horizontal" method="post" modelAttribute="oferta">
  <fieldset>
    <legend>Nueva Oferta</legend>
<!--     <div class="form-group"> -->
<!--       <label for="codigoOferta" class="col-md-2 control-label" style="color: black; font-size: medium;">Codigo Oferta</label> -->

<!--       <div class="col-md-2"> -->
<%--         <form:input path="codigoOferta" type="text" class="form-control" id="codigoOferta" placeholder="Codigo oferta"></form:input> --%>
<!--       </div> -->
<!--       <div> -->
<%--       	<form:errors path="codigoOferta" cssClass="error"/> --%>
<!--       </div> -->
<!--     </div> -->
    <div class="form-group">
      <label for="descripcion" class="col-md-2 control-label" style="color: black; font-size: medium;">Descripcion</label>

      <div class="col-md-4">
        <form:textarea path="descripcion" class="form-control" rows="3" id="descripcion"></form:textarea>
        <span class="help-block">Una pequeña descripcion sobre lo que ira la oferta.</span>
      </div>
      <div>
      	<form:errors path="descripcion" cssClass="error"/>
      </div>
      </div>
      <div class="form-group">
      <label for="fechaInicio" class="col-md-2 control-label" style="color: black; font-size: medium;">Fecha Inicio</label>

      <div class="col-md-2">
        <form:input path="fechaInicio" type="text" class="form-control" id="fechaInicio" placeholder="mm/dd/yyyy"></form:input>
      </div>
      <div>
      	<form:errors path="fechaInicio" cssClass="error"/>
      </div>
    </div>
    <div class="form-group">
      <label for="fechaFin" class="col-md-2 control-label" style="color: black; font-size: medium;">Fecha Fin</label>

      <div class="col-md-2">
        <form:input path="fechaFin" type="text" class="form-control" id="fechaFin" placeholder="mm/dd/yyyy"></form:input>
      </div>
      <div>
      	<form:errors path="fechaFin" cssClass="error"/>
      </div>
    </div>
    <div class="form-group">
      <label for="dniEstudiante" class="col-md-2 control-label" style="color: black; font-size: medium;">DNI Estudiante</label>

      <div class="col-md-2">
        <form:input path="dniEstudiante" type="text" class="form-control" id="dniEstudiante" placeholder="DNI" value="${user.dniEstudiante}"/>
      </div>
      <div>
      	<form:errors path="dniEstudiante" cssClass="error"/>
      </div>
    </div>
    <div class="form-group">
      <label for="nombreHabilidad" class="col-md-2 control-label" style="color: black; font-size: medium;">Nombre Habilidad</label>

      <div class="col-md-2">
        <form:select id="nombre" class="form-control" name="nombre" path="nombreHabilidad">
        	<option>---Elige---</option>
        	<c:forEach items="${listaHabilidades}" var="habilidad">
        	<option>${habilidad.nombre}</option>
        	</c:forEach>
        </form:select>
      </div>
    </div>
    <div class="form-group">
      <label for="nivelHabilidad" class="col-md-2 control-label" style="color: black; font-size: medium;">Nivel Habilidad</label>
      <div class="col-md-2">
        <form:select id="nivelHabilidad" class="form-control" name="nivel" path="nivelHabilidad">
        	<option>---Elige---</option>
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
        <button type="button" class="btn btn-default">Cancel</button>
        <button type="submit" class="btn btn-primary">Añadir</button>
      </div>
    </div>
  </fieldset>
</form:form>

</body>
</html>
