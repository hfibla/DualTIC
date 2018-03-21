<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" href="/Wallabook/MostrarMisLibros/img/favicon.png" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>WallaBook - Editar Perfil</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.1, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/Wallabook/perfilPropio/css/estilos.css">
    <script type="text/javascript" src="miPerfil.js"></script>
    <link rel="shortcut icon" href="/Wallabook/MostrarMisLibros/img/favicon.png" />
</head>

<header>
    <div class="contenedor">
        <img class="logo" src="/Wallabook/perfilPropio/img/Logo-login.png">
        <!--  <a href="/Wallabook/CerrarSesion" class="closesesion-btn">Cerrar Sesión</a> -->
                
                  <!--    <a href="/Wallabook/ObtenerMisLibros">Mis libros</a> -->
                    <!--  <a href="/Wallabook/ObtenerPerfil?edit=commit">Guardar cambios</a>  -->                  
               
     </div>
</header>
<main>
<<<<<<< HEAD

    

   

=======
>>>>>>> 0a050090af6301cefaf8d0a39865af9e6aa0e755
    <div class="avatar">
    <img src="${usuario.avatar.getLinkAvatar()}">

    </div>
    <section class="formulario">
            <form method="post" action="/Wallabook/ObtenerPerfil">
                <p class="formu">Usuario:<input class="input" type="text" value="${usuario.nickname}" title="Este atributo no es modificable" disabled/></p>
                <p class="formu">Nombre real: <input class="input" type="text" name="nombreReal" value="${usuario.nombreReal}" placeholder="Introduce aquí tu nombre real (Opcional)" 
                    title="Sólo caracteres del alfabeto español (o cualesquiera lenguas oficiales)" pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚàèòÀÈÒçÇ\s]+"/></p>
                <p class="formu">Localidad: <input class="input" type="text" name="localidad" value="${usuario.localidad}" placeholder="Introduce aquí tu localidad (Obligatorio)"
                    title="Sólo caracteres del alfabeto español (o cualesquiera lenguas oficiales)" pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚàèòÀÈÒçÇ\s]+" required/></p>
                <p class="formu">Correo: <input class="input" type="text" value="${usuario.correo}" title="Este atributo no es modificable" disabled/></p>
                <p class="formu">Teléfono: <input class="input" type="text" name="telefono" value="${usuario.telefono}" placeholder="Introduce aquí tu teléfono fijo o móvil (Opcional)"
                    title="9 caracteres numéricos" pattern="[0-9]+"/></p>
            <input class="save" type="submit" value="Guardar cambios"/>
            <input class="discard" type="button" value="Cancelar"/>
            </form>
</section>
    
</main>
    
    
              
            
            <div class="social">
<img class="social-icon" src="/Wallabook/perfilPropio/img/socialfacebook.png">
<img class="social-icon" src="/Wallabook/perfilPropio/img/socialtwitter.png">
<img class="social-icon" src="/Wallabook/perfilPropio/img/socialgoogle.png">
<img class="social-icon" src="/Wallabook/perfilPropio/img/sicialdropbox.png">
<img class="social-icon" src="/Wallabook/perfilPropio/img/socialgps.png">
</div>
                  
            
        
        
</html>