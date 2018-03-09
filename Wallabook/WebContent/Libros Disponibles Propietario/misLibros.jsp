<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>WallaBook - Mis libros </title>
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, user-scalable=1, minimum-scale=1">

<link rel="stylesheet" href="css/estilosperfil.css">
</head>
    <body> 
    <header>
       
        <div id="separador"></div>
        <div class="contenedor"> 
        
        <img id="logo" src= "img/logotipo.png">
            
        <div class="contenedor-botton">
            
        </div>
        </div>
        <a href="" class="volver" >Mi perfil</a>
       </header>
       <main>
    
        <p> Mis Libros</p>
        </main>
       <DIV id="contenedor-general">
       
                    
                    <c:forEach items="${libros}" var="libro">
                        <div class="contenedor-libros">
                   <p>${libro.titulo}</p>
                    <p>${libro.autor}</p>
                    <p>${libro.idioma}</p>
                    <p><c:if test="${libro.editorial != '0'}">${libro.editorial}</c:if></p>
                    <p id="nodisponible"><c:if test="${libro.disponible = 0}">No disponible</c:if></p>
                    <p id="disponible"><c:if test="${libro.disponible = 1}">Disponible</c:if></p>
                    </div>
                </c:forEach>
                

        </DIV>
       
        <footer>
           <div class="contenedor">
               <p class="copy">WallaBook &copy; 2018 Dev: Ivan</p>
               
           </div>
       </footer>
    </body>  
</html>
