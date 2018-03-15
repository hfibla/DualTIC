<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>WallaBook - Mis libros</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, user-scalable=1, minimum-scale=1">

<link rel="stylesheet" href="/Wallabook/CambiarDisponibilidadMisLibros/css/estilos.css">
</head>
<body>
	<header>

		<div id="separador"></div>
		<div class="contenedor">

			<img id="logo" src="/Wallabook/CambiarDisponibilidadMisLibros/img/Logo-login.png">

			<div class="contenedor-botton"></div>
		</div>
	</header>
	<main>
	<p>Mis Libros</p>
	</main>
	<DIV id="contenedor-general">
		<c:forEach items="${libros}" var="libro">
			<div class="contenedor-libros">
				<p>${libro.titulo}</p>
				<p>${libro.autor}</p>
				<p>${libro.idioma}</p>
				<c:if test="${libro.editorial != '0'}">
					<p>${libro.editorial}</p>
				</c:if>
				<p>Disponibilidad</p>
				<!-- ----------------Botón switch----------------- -->
				<div class="onoffswitch"> 
				<c:choose>
					<c:when test="${libro.disponible == 0}">
						<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch">
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
					</c:otherwise>
				</c:choose>
				<label class="onoffswitch-label" for="myonoffswitch"> 
                        <span class="onoffswitch-inner"></span> 
                        <span class="onoffswitch-switch"></span> 
                </label> 
				</div>
			</div>
		</c:forEach>
	</DIV>
	

	<footer>
		<div class="contenedor">
			<p class="copy">WallaBook &copy; 2018 Dev: Moha</p>

		</div>
	</footer>
</body>
</html>