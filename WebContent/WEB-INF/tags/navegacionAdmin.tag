<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<div class="navbar navbar-default" style="background-color: #4db6ac;">
  <div class="container">
    <div class="navbar-collapse collapse navbar-responsive-collapse">
      <ul class="nav navbar-nav">
     	<li><a href="${pageContext.request.contextPath}/perfilAdmin.jsp">Perfil</a></li>
  		<li><a href="${pageContext.request.contextPath}/estudiante/list.html">Gestion de Estudiantes</a></li>
  		<li><a href="${pageContext.request.contextPath}/habilidad/list.html">Gestion de Habilidades</a></li>
  		<li><a href="${pageContext.request.contextPath}/oferta/listAdmin.html">Consulta Ofertas</a></li>
  		<li><a href="${pageContext.request.contextPath}/demanda/listAdmin.html">Consulta Demandas</a></li>
  		<li><a href="${pageContext.request.contextPath}/colaboracion/listAdmin.html">Consulta Colaboraciones</a></li>
      </ul>     
    </div>
  </div>
</div>