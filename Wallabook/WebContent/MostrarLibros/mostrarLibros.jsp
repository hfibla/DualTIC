<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>WallaBook - Todos los libros</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.1, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="css/estilos.css">
    
</head>
    <body>
<header>
    <div class="contenedor">
        <img class="logo" src="img/Logo-login.png">
     </div>
</header>
<main>
    
    <div class="contenedor1">
    <h4>Listado de libros</h4>
    </div>    
<section class="linea"></section>
    
    
</main>
        <div class="contenedor-libros">
            <c:forEach items="${libros}" var="libro">
					<p>${libro.titulo}</p>
                    <p>${libro.autor}</p>
					<p>${libro.idioma}</p>
					<p><c:if test="${libro.editorial > 0}">${libro.editorial}</c:if>
					<p>${libro.disponible}</p>   
            </c:forEach>
        </div>
         <!-- footer-->
         <footer>
            <div class="contenedor">
                <p class="copy">WallaBook &copy; 2018 Dev: Moha</p>
                
            </div>
        </footer>
    </body>
</html>