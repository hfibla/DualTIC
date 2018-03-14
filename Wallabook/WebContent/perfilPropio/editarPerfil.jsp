<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>WallaBook - Editar Perfil</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.1, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/Wallabook/perfilPropio/css/estilos.css">
    <script type="text/javascript" src="miPerfil.js"></script>
</head>
<body>
<header>
    <div class="contenedor">
        <img class="logo" src="/Wallabook/perfilPropio/img/Logo-login.png">
        <!--  <a href="/Wallabook/CerrarSesion" class="closesesion-btn">Cerrar Sesi�n</a> -->
                <nav class="menu">
                  <!--    <a href="/Wallabook/ObtenerMisLibros">Mis libros</a> -->
                    <!--  <a href="/Wallabook/ObtenerPerfil?edit=commit">Guardar cambios</a>  -->                  
                </nav>
     </div>
</header>
<main>
    
    <div class="avatar">
    <img src="http://www.yumpabar.co.uk/images/srv/page-myaccount-associated-pages/My%20Account/icon-user.png">
    </div>
<form method="post" action="/Wallabook/ObtenerPerfil">
    <p>Usuario:<input type="text" value="${usuario.nickname}" title="Este atributo no es modificable" disabled/></p>
    <p>Nombre real: <input type="text" name="nombreReal" value="${usuario.nombreReal}" placeholder="Introduce aqu� tu nombre real (Opcional)" 
    title="S�lo caracteres del alfabeto espa�ol (o cualesquiera lenguas oficiales)" pattern="[a-zA-Z��������������������\s]+"/></p>
    <p>Localidad: <input type="text" name="localidad" value="${usuario.localidad}" placeholder="Introduce aqu� tu localidad (Obligatorio)"
     title="S�lo caracteres del alfabeto espa�ol (o cualesquiera lenguas oficiales)" pattern="[a-zA-Z��������������������\s]+" required/></p>
    <p>Correo: <input type="text" value="${usuario.correo}" title="Este atributo no es modificable" disabled/></p>
    <p>Tel�fono: <input type="text" name="telefono" value="${usuario.telefono}" placeholder="Introduce aqu� tu tel�fono fijo o m�vil (Opcional)"
    title="9 caracteres num�ricos" pattern="[0-9]+"/></p>
    <input type="submit" value="Guardar cambios"/>
</form>
    
       
        
    </div>
    
</main>
    </body>
    <footer>
            <div class="contenedor">
                <p class="copy">WallaBook &copy; 2018 Dev: Moha</p>
                
            </div>
        </footer>
</html>