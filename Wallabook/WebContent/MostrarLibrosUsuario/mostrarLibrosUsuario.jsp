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
	<c:if test="${not empty maxLibros}">
		<div id="limite-nodisp">
			<p>${maxLibros}</p>
		</div>
	</c:if>
	<DIV id="contenedor-general">
		<c:if test="${not empty librosSol}">
		<c:forEach items="${librosSol}" var="libro">
			<c:choose>
				<c:when test="${libro.editorial != '0'}">
					<div class="contenedor-libros-con-ed">
						<p>${libro.titulo}</p>
						<p>${libro.autor}</p>
						<p>${libro.idioma}</p>
						<p>${libro.editorial}</p>
						<p>${libro.categoria.getNombreCategoria()}</p>
						<c:choose>
							<c:when test="${libro.disponible == 0}">
								<p id="nodisponible">No disponible</p>
							</c:when>
							<c:otherwise>
								<p id="disponible">Disponible</p>
							</c:otherwise>
						</c:choose>
						<c:if test="${empty maxLibros}">
							<form method="post" action="/Wallabook/peticionLibro">							
								<input type="hidden" name="idLibro" value="${libro.idLibro}"/>
								<input type="submit" value="Solicitar libro" class="boton-solicitar">
							</form>
						</c:if>
					</div>
				</c:when>
				<c:otherwise>
					<div class="contenedor-libros-sin-ed">
						<p>${libro.titulo}</p>
						<p>${libro.autor}</p>
						<p>${libro.idioma}</p>
						<p>${libro.categoria.getNombreCategoria()}</p>
						<c:choose>
							<c:when test="${libro.disponible == 0}">
								<p id="nodisponible">No disponible</p>
							</c:when>
							<c:otherwise>
								<p id="disponible">Disponible</p>
							</c:otherwise>
						</c:choose>
						<c:if test="${empty maxLibros}">							
							<form method="post" action="/Wallabook/peticionLibro">							
								<input type="hidden" name="idLibro" value="${libro.idLibro}"/>
								<input type="submit" value="Solicitar libro" class="boton-solicitar">
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
				<c:when test="${libro.editorial != '0'}">
					<div class="contenedor-libros-con-ed">
						<p>${libro.titulo}</p>
						<p>${libro.autor}</p>
						<p>${libro.idioma}</p>
						<p>${libro.editorial}</p>
						<p>${libro.categoria.getNombreCategoria()}</p>
						<c:choose>
							<c:when test="${libro.disponible == 0}">
								<p id="nodisponible">No disponible</p>
							</c:when>
							<c:otherwise>
								<p id="disponible">Disponible</p>
							</c:otherwise>
						</c:choose>
						<input type="submit" value="Ya solicitado" class="boton-solicitar" disabled>
					</div>
				</c:when>
				<c:otherwise>
					<div class="contenedor-libros-sin-ed">
						<p>${libro.titulo}</p>
						<p>${libro.autor}</p>
						<p>${libro.idioma}</p>
						<p>${libro.categoria.getNombreCategoria()}</p>
						<c:choose>
							<c:when test="${libro.disponible == 0}">
								<p id="nodisponible">No disponible</p>
							</c:when>
							<c:otherwise>
								<p id="disponible">Disponible</p>
							</c:otherwise>
						</c:choose>						
							<input type="submit" value="Ya solicitado" class="boton-solicitar" disabled>						
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</c:if>		
	</div>

	<footer>
		<div class="contenedor">
			<p class="copy">WallaBook &copy; 2018 Dev: Ivan - Moha</p>

		</div>
		<div class="social">
<img class="social-icon" src="/Wallabook/perfilPropio/img/socialfacebook.png">
<img class="social-icon" src="/Wallabook/perfilPropio/img/socialtwitter.png">
<img class="social-icon" src="/Wallabook/perfilPropio/img/socialgoogle.png">
<img class="social-icon" src="/Wallabook/perfilPropio/img/sicialdropbox.png">
<img class="social-icon" src="/Wallabook/perfilPropio/img/socialgps.png">
</div>
	</footer>
</body>
</html>