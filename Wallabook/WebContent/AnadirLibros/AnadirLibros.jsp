<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>WallaBook - A�adir Libro</title>
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.1, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="css/estilos.css">
</head>
<body>
	<IMG id="logo-login" SRC="img/Logo-add-book.png" title="WallaBook???"></IMG>
	<form id="loginform" method="post" action="/Wallabook/AnadirLibros/anadir">
		<input type="text" class="input" placeholder="T�tulo"
			title="T�tulo del libro" required> <input type="text"
			class="input" placeholder="Autor" title="Autor del libro" required>
		<input type="text" class="input" placeholder="Editorial (Opcional)"
			title="Editorial del libro"> <input type="text" class="input"
			placeholder="Idioma" title="Idioma del libro" required> 
			<SELECT class="input" NAME="selCombo" SIZE=1 onChange="javascript:alert('prueba');"> 
<OPTION VALUE="link pagina 1">-</OPTION>
<c:forEach items="${categorias}" var="categoria">
	<OPTION VALUE="${categoria.idCategoria}">${categoria.nombreCategoria}</OPTION>
</c:forEach>
</SELECT>
			<input
			type="submit" class="loginbutton" value="A�ADIR LIBRO" />
	</form>
	<a id="registro-btn" href="/Wallabook/">Volver a la p�gina principal</a>

</body>
</html>