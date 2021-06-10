<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscar turno</title>
</head>
<body>
      
      <form:form action="buscarServicioVeterinario" method="post" name="formTurno" modelAttribute="zona">
      
		      <p>Servicio</p>
		      <select name="servicio" >
		       	<option>Rayos X</option>
		       	<option>Laboratorio</option>
		       	<option>Ecografias</option>
		       	<option>Castraciones</option>
		       	<option>Cirugia</option>
		       	<option>Cardiologia</option>
		       	<option>Planes Nutricionales</option>
		      </select>
		      
			 <p>Zona</p>
			 <form:select name="zona" path="descripcion">
			   <option value="oeste">Oeste</option>
			   <option values="norte">Norte</option>
			   <option values="sur">Sur</option>
			 </form:select>
			      
			      <br><br>
			  <input type="hidden" value="${duenioId}" name="idDuenio">
		      <button type="submit">Buscar turno</button>
		      
     </form:form>

</body>
</html>