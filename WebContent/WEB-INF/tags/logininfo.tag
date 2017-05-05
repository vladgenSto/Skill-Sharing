<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- La sessió està disponible automàticament en l’objecte "session" -->
<c:set var="user" scope="request" value='${session.getAttribute("user")}'/>
<p class="loggeduser">
<c:choose>
<c:when test='${user == null}'>
No autenticat <a href="${pageContext.request.contextPath}/login.html">Entrar</a> </c:when>
<c:otherwise>
Autenticat com ${user.username}
<a href="${pageContext.request.contextPath}/logout.html">Eixir</a> </c:otherwise>
</c:choose>
</p>