<%@ tag description="Estructura d'una pàgina normal admin"
	pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${title}</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">
<!-- Estils propis -->
<link
	href="${pageContext.request.contextPath}/css/estilo.css"
	rel="stylesheet">
</head>
<body class="fondo">
	<header class="container page-header">
		<h1>Skill Sharing</h1>
	</header>
	<t:navegacionAdmin />
	<div class="loggeduser"><t:logininfo /></div>
	<div class="container">
		<jsp:doBody />
	</div>
	<footer>
	<hr>
	<p class="text-muted">
	EI1027 - Diseño e Implementacioon de Sistemas de Informacion
	</p>
	</footer>
</body>
</html>