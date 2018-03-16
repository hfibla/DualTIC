<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>WallaBook - Todos los libros</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.1, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/Wallabook/MostrarLibros/css/estilos.css">
    
</head>
    <body>
<header>
    <div class="contenedor">
        <img class="logo" src="/Wallabook/MostrarLibros/img/Logo-login.png">
     </div>
</header>
<main>
    
    <div class="contenedor1">
    <h4>Listado de libros</h4>
    </div>    
<section class="linea"></section>
           <div class="centrar">
           <c:choose>
           		<c:when test="${empty error}">
            <c:forEach items="${libros}" var="libro">
            	<a href="/Wallabook/ObtenerLibrosUsuario?usr=${libro.usuario.getNickname()}">
	            	<c:choose>
						<c:when test="${libro.editorial != '0'}">
							<div class="contenedor-libros-con-ed">
								<p>${libro.titulo}</p>
								<p>${libro.autor}</p>
								<p>${libro.idioma}</p>
								<p>${libro.editorial}</p>
								<p>${libro.usuario.getNickname()}</p>
								<p>${libro.usuario.getLocalidad()}</p>
							</div>
						</c:when>
						<c:otherwise>
			                <div class="contenedor-libros-sin-ed">
								<p>${libro.titulo}</p>
			                    <p>${libro.autor}</p>
								<p>${libro.idioma}</p>					
								<p>${libro.usuario.getNickname()}</p>
								<p>${libro.usuario.getLocalidad()}</p>
			                 </div>  
		                </c:otherwise>
	                </c:choose>
	        	</a>
            </c:forEach>
            </c:when>
            <c:otherwise>
            	<p>${error}</p>
            </c:otherwise>
            
            </c:choose>
</div>
        <a id="registro-btn" href="/Wallabook/PaginaPrincipal">Volver a la página principal</a>
</main>
         <!-- footer-->
         <footer>
            <div class="contenedor">
                <p class="copy">WallaBook &copy; 2018 Dev: Moha</p>
                
            </div>
        </footer>
    </body>
</html>