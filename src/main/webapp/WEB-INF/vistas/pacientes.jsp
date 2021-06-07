<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis pacientes</title>
</head>
<body>
		<h3>Mis pacientes</h3>
		
		 <c:forEach items="${pacientes}" var="p">
			  
					<form action="verHistoriaClinica" method="post">
					   <p>Nombre del paciente: </p><p name="nombreMascota">${p.nombre}	</p>
					   <p>Fecha de nacimiento: </p><p name="fechaNacimiento">${p.fecha_nacimiento}</p>
					   <p>Duenio: </p><p name="duenioNombre">${p.duenio.nombre}</p>
					   <br>
					   <input type="hidden" name="duenioId" value="${p.duenio.id}"/>
					   <input type="hidden" name="mascotaId" value="${p.id}"/>
					   <input type="hidden" name="veterinarioId" value="${veterinario.id}">
					   <button type="submit">ver historia clinica</button>
					</form>	     
						  
			  					
			 </c:forEach>
</body>
</html>