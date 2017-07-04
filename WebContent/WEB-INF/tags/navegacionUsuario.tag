<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<div class="navbar navbar-default"  style="background-color: #4db6ac;">
  <div class="container">
    <div class="navbar-collapse collapse navbar-responsive-collapse">
      <ul class="nav navbar-nav">
     	<li><a href="${pageContext.request.contextPath}/perfilUsuario.jsp">Perfil</a></li>
  		<li><a href="${pageContext.request.contextPath}/oferta/list.html">Mis Ofertas</a></li>
  		<li><a href="${pageContext.request.contextPath}/oferta/buscar.html">Ofertas disponibles</a></li>
  		<li><a href="${pageContext.request.contextPath}/demanda/list.html">Gestion de Demandas</a></li>
        <li><a href="${pageContext.request.contextPath}/colaboracion/list.html">Gestion de Colaboraciones</a></li>
      </ul>
    </div>
  </div>
</div>
