<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>WallaBook - Mis libros</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=1, minimum-scale=1">
<link rel="shortcut icon" href="/Wallabook/MostrarMisLibros/img/favicon.png" />
 <link rel="stylesheet" href="/Wallabook/CambiarDisponibilidadMisLibros/css/estilos.css">
</head>
<body>
	<header>

		<div id="separador"></div>		

			<img id="logo" src="/Wallabook/CambiarDisponibilidadMisLibros/img/Logo-login.png">
			<a id="registro-btn" href="/Wallabook/PaginaPrincipal">Volver a la página principal</a>
	</header>
	
	 <!--BOTONES GUARDAR CANCELAR -->
			<div class="contenedor-buttons">
             <a href="/Wallabook/ObtenerMisLibros" id="discard-changes">Cancelar</a>
          <form method="post" action="/Wallabook/CambiarDisponibilidadLibro">
           <a><button id="save-changes" type="submit">Guardar cambios</button></a> 
          
	<DIV id="contenedor-general">
		<c:forEach items="${libros}" var="libro">
			<div class="contenedor-libros">
				<p>${libro.titulo}</p>
				<p>${libro.autor}</p>
				<p>${libro.idioma}</p>
				<c:if test="${libro.editorial != '0'}">
					<p>${libro.editorial}</p>
				</c:if>
								
				<!-- ----------------Botón switch----------------- -->
				  
				<p id="disponibilidadcss">
					<label class="container">Disponibilidad:  
						<c:choose>
							<c:when test="${libro.disponible == 0}">
								<input type="checkbox" name="disponible" value="${libro.idLibro}">
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="disponible" value="${libro.idLibro}" checked>
							</c:otherwise>
						</c:choose>
						<span class="checkmark"></span> 
					</label> 
				</p> 
			</div>
		</c:forEach>
	</DIV>
	</form>
	</div>
	

	<footer>
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