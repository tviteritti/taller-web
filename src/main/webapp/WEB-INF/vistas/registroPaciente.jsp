<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h1>VETERIANARIA</h1>
  <form:form action="procesarDatosPaciente" method="post" modelAttribute="usuario">
     <form:input type="text" name="usuario" placeholder="usuario" path="user"/><br>
     <form:input type="text" name="email" placeholder="email" path="email"/><br>
     <form:input type="text" name="password" placeholder="contraseña" path="password"/><br>
     <input type="text" name="re-password" placeholder="re ingresar contraseña"><br>
     <button type="submit">REGISTRARSE</button>
  </form:form>

</body>
</html>