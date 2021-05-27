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
			  
					<form action="verHC" method="post">
					   <p>Nombre: </p><p name="nombreMascota"><c:out value="${p.nombre}" />	</p>
					   <p>Fecha de nacimiento: </p><p name="fechaNacimiento"><c:out value="${p.fecha_nacimiento}" />	</p>
					   <p>Duenio: </p><p name="usuarioDuenioNombre"><c:out value="${p.duenio}" /></p>
					   <br>
					   <input type="hidden" name="usuarioDuenioObj" value="${p.duenio}"/>
					   <button type="submit">ver historia clinica</button>
					</form>	     
						  
			  					
			 </c:forEach>
</body>
</html>