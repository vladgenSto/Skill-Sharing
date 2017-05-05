<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<div class="navbar navbar-default" style="background-color: #ff4444; font-size: large;">
  <div class="container">
    <div class="navbar-collapse collapse navbar-responsive-collapse">
      <ul class="nav navbar-nav">
     	<li><a href="${pageContext.request.contextPath}/indexAdmin.jsp">Pagina princial</a></li>
  		<li><a href="${pageContext.request.contextPath}/estudiante/list.html">Gestion de Estudiantes</a></li>
  		<li><a href="${pageContext.request.contextPath}/habilidad/list.html">Gestion de Habilidades</a></li>
      </ul>     
    </div>
  </div>
</div>