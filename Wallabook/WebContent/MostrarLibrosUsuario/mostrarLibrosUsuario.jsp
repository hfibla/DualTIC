<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>WallaBook</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, user-scalable=1, minimum-scale=1">

<link rel="stylesheet" href="css/estilos.css">
</head>
<body>
	<header>

		<div id="separador"></div>
		<div class="contenedor">

			<img id="logo" src="img/logotipo.png">

			<div class="contenedor-botton"></div>
		</div>
		<a href="" class="volver">Volver</a>
	</header>
	<main>
	<p>Libros de ${suUsuario.nickname}</p>
	</main>
	<DIV id="contenedor-general">

		<c:forEach items="${libros}" var="libro">
			<div class="contenedor-libros">
				<p>${libro.titulo}</p>
				<p>${libro.autor}</p>
				<p>${libro.idioma}</p>
				<c:if test="${libro.editorial > 0}">
					<p>${libro.editorial}</p>
				</c:if>
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
	</div>

	<footer>
		<div class="contenedor">
			<p class="copy">WallaBook &copy; 2018 Dev: Ivan</p>

		</div>
	</footer>
</body>
</html>