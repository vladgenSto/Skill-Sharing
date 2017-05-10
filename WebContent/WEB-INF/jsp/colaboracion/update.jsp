<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
<title>Actualizar Colaboracion</title>
</head>
<body class="formulario">
<form:form class="form-horizontal" method="post" modelAttribute="colaboracion">
<fieldset>
<legend>Volara Colaboracion</legend>
<div class="form-group">
	<form:form path="codigoOferta"/>
<!--     	<label for="codigoOferta" class="col-md-2 control-label" style="color: black; font-size: medium;">Codigo Oferta</label> -->
		<div class="col-md-4">
        	<form:input path="codigoOferta" class="form-control" rows="3" id="codigoOferta" hidden="true"></form:input>
      	</div>
    	<div>
<%--       		<form:errors path="codigoOferta" cssClass="error"/> --%>
      	</div>
	</div>
<div class="form-group">
	<form:form path="codigoDemanda"/>
<!--     	<label for="codigoDemanda" class="col-md-2 control-label" style="color: black; font-size: medium;">Codigo Demanda</label> -->
		<div class="col-md-4">
        	<form:input path="codigoDemanda" class="form-control" rows="3" id="codigoDemanda" hidden="true"></form:input>
      	</div>
    	<div>
<%--       		<form:errors path="codigoDemanda" cssClass="error"/> --%>
      	</div>
	</div>
<div class="form-group">
	<form:form path="descripcionOferta"/>
    	<label for="descripcionOferta" class="col-md-2 control-label" style="color: black; font-size: medium;">Descripcion Oferta</label>
		<div class="col-md-4">
        	<form:textarea path="descripcionOferta" class="form-control" rows="3" id="descripcionOferta"></form:textarea>
        	<span class="help-block">Una pequeña descripcion sobre lo que ira la oferta.</span>
      	</div>
    	<div>
      		<form:errors path="descripcionOferta" cssClass="error"/>
      	</div>
	</div>
<div class="form-group">
	<form:form path="descripcionDemanda" />
    	<label for="descripcionDemanda" class="col-md-2 control-label" style="color: black; font-size: medium;">Descripcion Demanda</label>
		<div class="col-md-4">
        	<form:textarea path="descripcionDemanda" class="form-control" rows="3" id="descripcionDemanda"></form:textarea>
        	<span class="help-block">Una pequeña descripcion sobre lo que ira la demanda.</span>
      	</div>
    	<div>
      		<form:errors path="descripcionDemanda" cssClass="error"/>
      	</div>
	</div>
<div class="form-group">
      <label for="fechaFin" class="col-md-2 control-label" style="color: black; font-size: medium;">Fecha Fin</label>

      <div class="col-md-2">
        <form:input path="fechaFin" type="text" class="form-control" id="fechaFin" value="${fechaFin}"></form:input>
        <span class="help-block">(mm/dd/yyyy)</span>
      </div>
      <div>
      	<form:errors path="fechaFin" cssClass="error"/>
      </div>
    </div>
<div class="form-group">
	<form:form path="horas"/>
    	<label for="horas" class="col-md-2 control-label" style="color: black; font-size: medium;">Horas</label>
		<div class="col-md-4">
        	<form:input path="horas" class="form-control" rows="3" id="horas"></form:input>
      	</div>
    	<div>
      		<form:errors path="horas" cssClass="error"/>
      	</div>
	</div>
<div class="form-group">
      <label for="puntuacion" class="col-md-2 control-label" style="color: black; font-size: medium;">Puntuacion</label>
      <div class="col-md-2">      
        <form:select id="puntuacion" class="form-control" name="nivel" path="puntuacion">
        	<option>1</option>
        	<option>2</option>
        	<option>3</option>
        	<option>4</option>
        	<option>5</option>
        </form:select>
      </div>
      <div>
      	<form:errors path="puntuacion" cssClass="error"/>
      </div>
    </div>
<div class="form-group">
	<form:form path="comentarios"/>
    	<label for="comentarios" class="col-md-2 control-label" style="color: black; font-size: medium;">Comentarios</label>
		<div class="col-md-4">
        	<form:textarea path="comentarios" class="form-control" rows="3" id="comentarios"></form:textarea>
        	<span class="help-block">Que le ha parecido al usuario la colaboracion</span>
      	</div>
    	<div>
      		<form:errors path="comentarios" cssClass="error"/>
      	</div>
	</div>
<br>
<button type="submit" class="btn btn-raised btn-primary">Terminar</button>
</fieldset>
</form:form>
</body>
</html>