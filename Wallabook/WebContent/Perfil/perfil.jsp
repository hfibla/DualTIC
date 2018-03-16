<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>WallaBook - Perfil</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.1, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/Wallabook/Perfil/css/estilos.css">
</head>
<body>
<header>
    <div class="contenedor">
        <img class="logo" src="/Wallabook/Perfil/img/Logo-login.png">
 
        <a href="/Wallabook/PaginaPrincipal" class="closesesion-btn">Volver al menú principal</a>
                <nav class="menu">
                    <a href="/Wallabook/ObtenerLibrosUsuario?usr=${usuario.nickname}">Libros de ${usuario.nickname}</a>
                    
                    
                </nav>
     </div>
</header>
<main>    
    <div class="avatar">
    <img src="${usuario.avatar.getLinkAvatar()}"/>
    </div>
<div class="info-perfil">
    <p>Usuario: ${usuario.nickname}</p>
    <c:choose>
    	<c:when test="${usuario.nombreReal != null}"><p>Nombre real: ${usuario.nombreReal}</p></c:when>
    	<c:otherwise><p>Nombre real: - </p></c:otherwise>
    </c:choose>    
    <p>Localidad: ${usuario.localidad}</p>
    <p>Correo: ${usuario.correo}</p>
    <c:choose>
    	<c:when test="${usuario.telefono != null}"><p>Teléfono: ${usuario.telefono}</p></c:when>
    	<c:otherwise><p>Teléfono: - </p></c:otherwise>
    </c:choose>
       
        
    </div>
    
</main>
    </body>
    <footer>
            <div class="contenedor">
                <p class="copy">WallaBook &copy; 2018 Dev: Moha</p>
                
            </div>
        </footer>
</html>