<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="es">
<head>
<title>WallaBook - Notificacion libro</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, user-scalable=1, minimum-scale=1">
<link rel="shortcut icon"
	href="/Wallabook/accDenPeticiones/img/favicon.png" />
<link rel="stylesheet" href="/Wallabook/accDenPeticiones/css/estilo.css">

</head>
<body>
	<header>

		<div id="separador"></div>
		<div class="contenedor">

			<img id="logo" src="/Wallabook/accDenPeticiones/img/logotipo.png">

			<div class="contenedor-botton"></div>
		</div>
		<a href="/Wallabook/PaginaPrincipal" class="volver">Volver al
			Menu principal </a>
	</header>
	<main>

	<p>Notificaciones</p>
	</main>

	<DIV id="contenedor-general">


		<c:forEach items="${notificaciones}" var="notificacion">
			<div class="contenedor-libros">
				<p>${notificacion.mensaje}</p>
				<input type="hidden" name="nickRemitente" value="${notificacion.usuario.getNickname()}"/>
				<input type="hidden" name="idLibro" value="${notificacion.libro.getIdLibro()}"/>
				<c:if test="${notificacion.accionNecesaria == 1}">
					<form method="post" action="/Wallabook/GestionarPeticion">
						<input type="submit" name="peticion" value="Aceptar" /> 
						<input type="submit" name="peticion" value="Rechazar" />
					</form>
				</c:if>
			</div>	
		</c:forEach>


	</DIV>

	<div class="social">
		<img class="social-icon"
			src="/Wallabook/perfilPropio/img/socialfacebook.png"> <img
			class="social-icon"
			src="/Wallabook/perfilPropio/img/socialtwitter.png"> <img
			class="social-icon"
			src="/Wallabook/perfilPropio/img/socialgoogle.png"> <img
			class="social-icon"
			src="/Wallabook/perfilPropio/img/sicialdropbox.png"> <img
			class="social-icon" src="/Wallabook/perfilPropio/img/socialgps.png">
	</div>

</body>
</html>