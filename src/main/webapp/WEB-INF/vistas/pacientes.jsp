<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis pacientes</title>
</head>
<body>
		<h3>Mis pacientes</h3>
		
		 <c:forEach items="${mascotas}" var="m">
			  
					<form action="verHistoriaClinica" method="post">
					   <p>Nombre del paciente: <c:out value="${m.nombre}"/></p> 
					   <p>Fecha de nacimiento: </p><p >${m.fecha_nacimiento}</p>
					   <p>Duenio: </p><p>${m.duenio.nombre}</p>
					   <br>
					   <input type="hidden" name="duenioId" value="${m.duenio.id}"/>
					   <input type="hidden" name="mascotaId" value="${m.id}"/>
					   <input type="hidden" name="veterinarioId" value="${veterinario.id}">
					   <button type="submit">ver historia clinica</button>
					</form>	     
						  
			  					
			 </c:forEach>
</body>
</html>