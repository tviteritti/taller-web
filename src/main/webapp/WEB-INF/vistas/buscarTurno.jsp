<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscar turno</title>
</head>
<body>
      
      <form action="buscarServicioVeterinario" method="post" name="formTurno">
      
		      <p>Servicio</p>
		      <select name="servicio" form="formTurno">
		       	<option>Rayos X</option>
		       	<option>Laboratorio</option>
		       	<option>Ecografias</option>
		       	<option>Castraciones</option>
		       	<option>Cirugia</option>
		       	<option>Cardiologia</option>
		       	<option>Planes Nutricionales</option>
		      </select>
		     <!--   <form action="buscarTurno"  method="post">-->
			      <p>Zona</p>
			      <select name="zona">
			       	<option value="oeste">Oeste</option>
			       	<option values="norte">Norte</option>
			       	<option values="sur">Sur</option>
			      </select>
			      <!-- <button type="submit">mostrar localidades</button>
		     </form>
		      
		       <p name="localidades">Localidad</p>
		    <c:if test="${not empty localidades}">
		      <c:forEach var = "i" begin = "1" end = "4">
		      
			      <select name="localidades" form="formTurno">
			       	<option value="${localidades}">${localidades}</option>
			      </select>
		       
		      </c:forEach> 
		      </c:if>-->
		      <button type="submit">Buscar turno</button>
		      
     </form>

</body>
</html>