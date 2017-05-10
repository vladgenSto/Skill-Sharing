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
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"> 
<link
	href="${pageContext.request.contextPath}/css/estilo.css"
	rel="stylesheet">
</head>
<body class="fondo">
	<header class="container page-header">
		<h1 style="left: 35%; position: relative;"> <i id="icon" class="fa fa-pencil" style="text-shadow: rgb(179, 43, 43) 0px 0px 0px, rgb(188, 45, 45) 1px 1px 0px, rgb(198, 47, 47) 2px 2px 0px, rgb(207, 50, 50) 3px 3px 0px, rgb(217, 52, 52) 4px 4px 0px, rgb(226, 54, 54) 5px 5px 0px, rgb(236, 56, 56) 6px 6px 0px, rgb(245, 59, 59) 7px 7px 0px; font-size: 31px; color: rgb(255, 255, 255); height: 53px; width: 53px; line-height: 53px; border-radius: 19%; text-align: center; background-color: rgb(255, 61, 61);"></i> 
		Skill Sharing</h1>
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