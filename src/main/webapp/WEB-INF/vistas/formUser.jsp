<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ingresar</title>
</head>
<body>
  <h1>VETERIANARIA</h1>
  <form:form action="validarLoginUsuario" method="post" modelAttribute="usuario">
     <form:input type="text" name="usuario" path="user"/>
     <form:input type="password" name="password" path="password"/>
     <button type="submit">INGRESAR</button>
  </form:form>
<h4><span>${error}</span></h4>
</body>
</html>