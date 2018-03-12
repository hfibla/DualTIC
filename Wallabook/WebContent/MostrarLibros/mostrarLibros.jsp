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
            <c:forEach items="${libros}" var="libro">
	            	<c:choose>
						<c:when test="${libro.editorial != '0'}">
							<div class="contenedor-libros-con-ed">
								<p>${libro.titulo}</p>
								<p>${libro.autor}</p>
								<p>${libro.idioma}</p>
								<p>${libro.editorial}</p>
								<p>${libro.usuario.getNickname()}</p>
							</div>
						</c:when>
						<c:otherwise>
			                <div class="contenedor-libros-sin-ed">
								<p>${libro.titulo}</p>
			                    <p>${libro.autor}</p>
								<p>${libro.idioma}</p>					
								<p>${libro.usuario.getNickname()}</p>
			                 </div>  
		                </c:otherwise>
	                </c:choose>
            </c:forEach>
</div>
        <a id="registro-btn" href="/Wallabook/PaginaPrincipal">Volver a la p�gina principal</a>
</main>
         <!-- footer-->
         <footer>
            <div class="contenedor">
                <p class="copy">WallaBook &copy; 2018 Dev: Moha</p>
                
            </div>
        </footer>
    </body>
</html>