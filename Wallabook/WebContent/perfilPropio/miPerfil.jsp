<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>WallaBook - Perfil</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.1, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/Wallabook/perfilPropio/css/estilos.css">
    <script type="text/javascript" src="/Wallabook/perfilPropio/miPerfil.js"></script>
</head>
<body onload="checkEditOk(${editok})">
<header>
	<div class="modal-wrapper" id="popup">
    <div class="popup-contenedor">
            <a class="popup-cerrar" href="#">✖</a>
      	    <h2>¿Estás seguro?</h2>
            <p>Piénsatelo dos veces...</p>
            <a class="si-button" href="#">Continuar</a>
            <a class="no-button" href="#">Cancelar</a>
    </div>
</div>
    <div class="contenedor">
        <img class="logo" src="/Wallabook/perfilPropio/img/Logo-login.png">
        <a href="/Wallabook/CerrarSesion" class="closesesion-btn">Cerrar Sesión</a>
                <nav class="menu">
                    <a href="/Wallabook/ObtenerLibros">Mis libros</a>
                    <a href="/Wallabook/ObtenerPerfil?edit=pending">Editar perfil</a>
                    <a href="#popup" class="popup-link">Eliminar cuenta</a>                  
                </nav>
     </div>
</header>
<main>
    
    <div class="avatar">
    <img src="${usuario.avatar.getLinkAvatar()}"/>
    <a href="/Wallabook/CambiarAvatar"><input type="button" value="Cambiar Avatar"/></a>
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