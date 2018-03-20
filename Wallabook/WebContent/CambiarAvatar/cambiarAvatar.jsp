<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WallaBook - Cambiar Avatar</title>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.1, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="/Wallabook/CambiarAvatar/css/estilos.css">
</head>
<body>
<form class="general" method="post" action="/Wallabook/CambiarAvatar">
	<c:forEach items="${avatares}" var="avatar">
        <div class="contenedor-avatar">
            <div class="avatar">
                <img src="${avatar.linkAvatar}">
                <input type="radio" id="radioButton" name="avatar" value="${avatar.idAvatar}">
            </div>
        </div>
    </c:forEach>
    <input id="save" type="submit" value="Guardar">
        <a href="/Wallabook/ObtenerPerfil"><input id="save" type="button" value="Cancelar"></a>
</form>
</body>
</html>