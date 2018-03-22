<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>WallaBook - Lista de libros</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=1, minimum-scale=1">
<link rel="shortcut icon"
	href="/Wallabook/MostrarMisLibros/img/favicon.png" />
<link rel="stylesheet"
	href="/Wallabook/MostrarLibrosUsuario/css/estilos.css">
</head>
<body>
	<header>
		<div id="separador"></div>
		<div class="contenedor">
			<img id="logo" src="/Wallabook/MostrarLibrosUsuario/img/logotipo.png">
			<div class="contenedor-botton"></div>
		</div>
		<a href="/Wallabook/PaginaPrincipal" class="volver">Volver al menú
			principal</a>
	</header>
	<main>
	<p>
		Perfil de <a href="/Wallabook/ObtenerPerfil?usr=${suUsuario.nickname}">${suUsuario.nickname}</a>
	</p>
	</main>
	<c:if test="${not empty maxLibros}">
		<div id="limite-nodisp">
			<p>${maxLibros}</p>
		</div>
	</c:if>
	<DIV id="contenedor-general">
		<c:if test="${not empty librosSol}">
			<c:forEach items="${librosSol}" var="libro">
				<c:choose>
					<c:when test="${not empty libro.editorial}">
						<div class="contenedor-libros">
							<ul>
								<li>${libro.titulo}</li>
								<li>${libro.autor}</li>
								<li>${libro.idioma}</li>
								<li>${libro.editorial}</li>
								<li>${libro.categoria.getNombreCategoria()}</li>
								<c:choose>
									<c:when test="${libro.disponible == 0}">
										<li id="nodisponible">No disponible</li>
									</c:when>
									<c:otherwise>
										<li id="disponible">Disponible</li>
									</c:otherwise>
								</c:choose>
							</ul>
							<c:if test="${empty maxLibros}">
								<form method="post" action="/Wallabook/peticionLibro">
									<input type="hidden" name="idLibro" value="${libro.idLibro}" />
									<input type="submit" value="Solicitar libro"
										class="boton-solicitar">
								</form>
							</c:if>
						</div>
					</c:when>
					<c:otherwise>
						<div class="contenedor-libros">
							<ul>
								<li>${libro.titulo}</li>
								<li>${libro.autor}</li>
								<li>${libro.idioma}</li>
								<li>${libro.categoria.getNombreCategoria()}</li>
								<c:choose>
									<c:when test="${libro.disponible == 0}">
										<li id="nodisponible">No disponible</li>
									</c:when>
									<c:otherwise>
										<li id="disponible">Disponible</li>
									</c:otherwise>
								</c:choose>
								<li> </li>
							</ul>
							<c:if test="${empty maxLibros}">
								<form method="post" action="/Wallabook/peticionLibro">
									<input type="hidden" name="idLibro" value="${libro.idLibro}" />
									<input type="submit" value="Solicitar libro"
										class="boton-solicitar">
								</form>
							</c:if>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:if>
		<c:if test="${not empty librosNoSol}">
			<c:forEach items="${librosNoSol}" var="libro">
				<c:choose>
					<c:when test="${not empty libro.editorial}">
						<div class="contenedor-libros">
							<ul>
								<li>${libro.titulo}</li>
								<li>${libro.autor}</li>
								<li>${libro.idioma}</li>
								<li>${libro.editorial}</li>
								<li>${libro.categoria.getNombreCategoria()}</li>
								<c:choose>
									<c:when test="${libro.disponible == 0}">
										<li id="nodisponible">No disponible</li>
									</c:when>
									<c:otherwise>
										<li id="disponible">Disponible</li>
									</c:otherwise>
								</c:choose>
							</ul>
							<input type="submit" value="Ya solicitado"
								class="boton-solicitar" disabled>
						</div>
					</c:when>
					<c:otherwise>
						<div class="contenedor-libros">
							<ul>
								<li>${libro.titulo}</li>
								<li>${libro.autor}</li>
								<li>${libro.idioma}</li>
								<li>${libro.categoria.getNombreCategoria()}</li>
								<c:choose>
									<c:when test="${libro.disponible == 0}">
										<li id="nodisponible">No disponible</li>
									</c:when>
									<c:otherwise>
										<li id="disponible">Disponible</li>
									</c:otherwise>
								</c:choose>
								<li> </li>
							</ul>
							<input type="submit" value="Ya solicitado"
								class="boton-solicitar" disabled>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:if>
	</div>


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