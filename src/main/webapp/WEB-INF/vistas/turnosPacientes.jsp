<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Turnos</title>
</head>
<body>
			<h3>Listado de Turnos</h3>
			<br>
			 <c:forEach items="${turnos}" var="t">
			  
					<form action="contactarPaciente" method="post">
					   <p>Fecha: </p><p name="fechaTurno"><c:out value="${t.fecha}" />	</p>
					   <p>Hora: </p><p name="fechaHora"><c:out value="${t.horario}" />	</p>
					   <p>Servicio: </p><p name="servicioTurno"><c:out value="${t.servicio}" />	</p>
					   <p>Solicitado por: </p><p name="usuarioTurno"><c:out value="${t.duenio.nombre} ${t.duenio.apellido}" /></p>
					   <br>
					   <input type="hidden" name="duenio" value="${t.duenio.id}"/>
					   <button type="submit">contactar</button>
					</form>	     
						  
			  					
			 </c:forEach> <!---->

</body>
</html>