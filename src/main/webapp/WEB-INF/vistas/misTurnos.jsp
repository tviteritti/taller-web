<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis Turnos</title>
</head>
<body>
      <h3>Turnos Solicitados</h3>
      <br>
		 <c:forEach items="${turnos}" var="t">
			  
					<form action="cancelarTurno" method="post">
					   <p>Fecha: </p><p name="fechaTurno"><c:out value="${t.fecha}" />	</p>
					   <p>Hora: </p><p name="fechaHora"><c:out value="${t.horario}" />	</p>
					   <p>Servicio: </p><p name="servicioTurno"><c:out value="${t.servicio}" />	</p>
					   <p>Veterinario: </p><p name="veterinarioTurno"><c:out value="${t.veterinario}" /></p>
					   <br>
					   <input type="hidden" name="id_turno" value="${t.id}"/>
					   <button type="submit">cancelar turno</button>
					</form>	     
						  
			  					
			 </c:forEach> <!---->
</body>
</html>