<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Anyadir Colaboracion</title>
</head>
<body>
<h2>Nueva Colaboracion</h2>
<form:form method="post" modelAttribute="colaboracion">
<table>
<tr>
<td><form:label path="codigoOferta">Codigo Oferta</form:label>
<td><form:input path="codigoOferta" value="${oferta.codigoOferta}"/></td>
<td><form:errors path="codigoOferta" cssClass="error"/></td>
</tr>
<tr>
<td><form:label path="codigoDemanda">Codigo Demanda</form:label>
<td><form:input path="codigoDemanda" value="${demanda.codigoDemanda}"/></td>
<td><form:errors path="codigoDemanda" cssClass="error"/></td>
</tr>
<tr>
<td><form:label path="horas">Horas</form:label>
<td><form:input path="horas"/></td>
<td><form:errors path="horas" cssClass="error"/></td>
</tr>
<tr>
<td><form:label path="puntuacion">Puntuacion</form:label>
<td><form:select class="form-control" name="puntuacion" style="width:70%" path="puntuacion">
  <option>1</option>
  <option>2</option>
  <option>3</option>
  <option>4</option>
  <option>5</option>
</form:select></td>
<td><form:errors path="puntuacion" cssClass="error"/></td>
</tr>
<tr>
<td><form:label path="comentarios">Comentarios</form:label>
<td><form:input path="comentarios"/></td>
<td><form:errors path="comentarios" cssClass="error"/></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="Anyadir Colaboracion"></td>
</tr>
</table>
</form:form>

</body>
</html>