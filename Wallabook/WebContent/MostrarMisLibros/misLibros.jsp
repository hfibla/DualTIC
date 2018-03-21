<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>WallaBook - Mis libros</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=1, minimum-scale=1">
<link rel="stylesheet" href="/Wallabook/MostrarMisLibros/css/estilosperfil.css">
<link rel="shortcut icon" href="/Wallabook/MostrarMisLibros/img/favicon.png" />
</head>
<body>
	<header>

		<div id="separador"></div>
		<div class="contenedor">

			<img id="logo" src="/Wallabook/Perfil/img/Logo-login.png">

			<div class="contenedor-botton"></div>
		</div>
		<a href="/Wallabook/CambiarDisponibilidadLibro" class="cambiar-dispo">Editar disponibilidad</a>
		<a href="/Wallabook/ObtenerPerfil" class="volver">Mi perfil</a>
	</header>
	<main>
	<p>Mis Libros</p>
	</main>
	<c:if test="${not empty error}">
		<div id="demasiados-nodisp-cambiados">
			<p>${error}</p>
		</div>
	</c:if>
	<c:if test="${not empty maxLibros}">
		<div id="limite-nodisp">
			<p>${maxLibros}</p>
		</div>
	</c:if>
	<DIV id="contenedor-general">
		<c:forEach items="${libros}" var="libro">
			<div class="contenedor-libros">
				<p>${libro.titulo}</p>
				<p>${libro.autor}</p>
				<p>${libro.idioma}</p>
				<c:if test="${libro.editorial != '0'}">
					<p>${libro.editorial}</p>
				</c:if>
				<p>${libro.categoria.getNombreCategoria()}</p>
				<c:choose>
					<c:when test="${libro.disponible == 0}">
						<p id="nodisponible">No disponible</p>
					</c:when>
					<c:otherwise>
						<p id="disponible">Disponible</p>
					</c:otherwise>
				</c:choose>
			</div>
		</c:forEach>
	</DIV>
	<
		 <div class="social">
<img href="#" class="social-icon" src="/Wallabook/perfilPropio/img/socialfacebook.png">
<img href="#" class="social-icon" src="/Wallabook/perfilPropio/img/socialtwitter.png">
<img href="#" class="social-icon" src="/Wallabook/perfilPropio/img/socialgoogle.png">
<img href="#" class="social-icon" src="/Wallabook/perfilPropio/img/sicialdropbox.png">
<img href="#" class="social-icon" src="/Wallabook/perfilPropio/img/socialgps.png">
</div>
		
	
</body>
</html>
