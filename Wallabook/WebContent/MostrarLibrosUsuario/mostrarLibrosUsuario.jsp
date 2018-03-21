<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>WallaBook - Lista de libros </title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=1, minimum-scale=1">
<link rel="shortcut icon" href="/Wallabook/MostrarMisLibros/img/favicon.png" />
<link rel="stylesheet" href="/Wallabook/MostrarLibrosUsuario/css/estilos.css">
</head>
<body>
	<header>

		<div id="separador"></div>
		<div class="contenedor">

			<img id="logo" src="/Wallabook/MostrarLibrosUsuario/img/logotipo.png">

			<div class="contenedor-botton"></div>
		</div>
		<a href="/Wallabook/PaginaPrincipal" class="volver">Volver al menú principal</a>
	</header>
	<main>
	<p>Libros de <a href="/Wallabook/ObtenerPerfil?usr=${suUsuario.nickname}">${suUsuario.nickname}</a></p>
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
				<p>${libro.categoria.getNombreCategoria()}</p>
				<c:choose>
					<c:when test="${libro.disponible == 0}">
						<p id="nodisponible">No disponible</p>
					</c:when>
					<c:otherwise>
						<p id="disponible">Disponible</p>
					</c:otherwise>
				</c:choose>
				<a href="#" class="boton-solicitar">Solicitar libro</a>
			</div>
		</c:forEach>
	</div>

	<footer>
		<div class="contenedor">
			<p class="copy">WallaBook &copy; 2018 Dev: Ivan - Moha</p>

		</div>
		<div class="social">
<img href="#" class="social-icon" src="/Wallabook/perfilPropio/img/socialfacebook.png">
<img href="#" class="social-icon" src="/Wallabook/perfilPropio/img/socialtwitter.png">
<img href="#" class="social-icon" src="/Wallabook/perfilPropio/img/socialgoogle.png">
<img href="#" class="social-icon" src="/Wallabook/perfilPropio/img/sicialdropbox.png">
<img href="#" class="social-icon" src="/Wallabook/perfilPropio/img/socialgps.png">
</div>
	</footer>
</body>
</html>