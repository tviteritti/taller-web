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
  <form:form action="procesarDatosVeterinario" method="post" modelAttribute="usuario">
     <form:input type="text" name="usuario" placeholder="usuario" path="user"/><br>
     <form:input type="text" name="email" placeholder="email" path="email"/><br>
     <form:input type="text" name="password" placeholder="contrase�a" path="password"/><br>
     <input type="text" name="re-password" placeholder="re ingresar contrase�a"><br>
     <label>Especialidad</label>
     <select name="select">
	  <option value="value1">Especialidades clinicas</option>
	  <option value="value2" selected>Especialidades por especie</option>
	  <option value="value3">Especialidades con �nfasis acad�mico</option>
	  <option value="value4">Especialidades en relaci�n a ayudas diagn�sticas</option>
	  <option value="value5">Especialidades con �nfasis acad�mico</option>
	</select>
	<br>
      
     <button type="submit">REGISTRARSE</button>
  </form:form>

</body>
</html>