<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>WallaBook - Buscar Avanzado</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.1, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/Wallabook/Buscar/css/estilos.css">
     <link rel="shortcut icon" href="/Wallabook/MostrarMisLibros/img/favicon.png" />
</head>
    <body>
	<IMG id="logo-login" src="/Wallabook/Buscar/img/Logo-login.png" title="WallaBook">
	
	<form id="loginform" method="post" action="/Wallabook/ConsultarLibrosAvanzado">
		<input type="text" class="input" placeholder="Buscar por título" name="titulo" title="Título del libro" > 
		<input type="text" name="autor"	class="input" placeholder="Buscar por autor" title="Autor del libro"> 
<SELECT class="input" NAME="categoria" SIZE=1> 
<OPTION VALUE="default">Buscar por categoría</OPTION>
<c:forEach items="${categorias}" var="categoria">
	<OPTION VALUE="${categoria.nombreCategoria}">${categoria.nombreCategoria}</OPTION>
</c:forEach>
</SELECT>
			<input type="submit" class="loginbutton" value="BUSCAR" />
	</form>
	<a id="registro-btn" href="/Wallabook/PaginaPrincipal">Volver a la página principal</a>
	</body>
</html>