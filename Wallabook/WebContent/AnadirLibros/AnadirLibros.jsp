<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>WallaBook - Añadir Libro</title>
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.1, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="/Wallabook/AnadirLibros/css/estilos.css">
</head>
<body>
	<IMG id="logo-login" SRC="/Wallabook/AnadirLibros/img/Logo-add-book.png" title="WallaBook"></IMG>
	<form id="loginform" method="post" action="/Wallabook/AnadirLibros/anadir">
		<input type="text" class="input" placeholder="Título"
			title="Título del libro" name="titulo" required>
		<input type="text" name="autor" pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+"
			class="input" placeholder="Autor" title="Autor del libro" required>
		<input type="text" class="input" placeholder="Editorial (Opcional)"
			title="Editorial del libro" name="editorial"> 
		<input type="text" class="input" name="idioma" pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+"
			placeholder="Idioma" title="Idioma del libro" required> 
			<SELECT class="input" name="categoria" SIZE="1"> 
<OPTION VALUE=0>-</OPTION>
<c:forEach items="${categorias}" var="categoria">
	<OPTION VALUE="${categoria.idCategoria}">${categoria.nombreCategoria}</OPTION>
</c:forEach>
</SELECT>
			<input
			type="submit" class="loginbutton" value="AÑADIR LIBRO" />
	</form>
	<a id="registro-btn" href="/Wallabook/">Volver a la página principal</a>

</body>
</html>