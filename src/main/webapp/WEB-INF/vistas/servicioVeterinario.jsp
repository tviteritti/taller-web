<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Encontra el especialista y solicita un turno</title>
</head>
<body>
  <h2>Encontra el especialista y solicita un turno</h2>
  

			     <c:forEach items="${veterinarios}" var="v">
			     
				     <form:form action="generarTurno" method="post" modelAttribute="veterinario">
					    <p> 
					   	 <c:out value="${v.nombre} ${v.apellido}" />
					    </p>
					    <p>Calificacion</p>
					    <p>Descripcion: </p>
					    <br>
					    <!--  <p><c:out value="${v.descripcion}" /></p>-->
					    <p>Dias y Horarios disponibles</p>
					    <input type="date" name="fecha">
					    <input type="time" name="hora">
					    <p>Ubicacion:</p> <p name="direccion"><c:out value="${v.direccion}"/></p>
					    <p name="localidad">${v.direccion.zona.localidad.descripcion}</p>
					    <input type="hidden" value="${servicio}" name="servicio">
					    <input type="hidden" value="${zona}" name="zona">
					    <p>Turnos</p>
					    <br>
					     <c:forEach items="${turnosPorVT}" var="t">
					     
						     <c:if test="${t.veterinario.id eq v.id}">
						    
							     
							    <input type="radio" name="turno" id="turno" value="turno${t.id}">
							    <input type="hidden" name="idTurno" id="turno" value="${t.id}">
							    <label for="turno">
							      <p>Fecha: 
							        <p name="fecha">
							         <c:out value="${t.fecha}" />
							         </p> 
							       </p>
							      <p>Hora: 
							        <p name="horario"> 
							           <c:out value="${t.horario}" /> 
							         </p> 
							     </p>
							    </label><br>
							    
							</c:if>      
					    </c:forEach>
					    <input type="hidden" value="${duenio.id}" name="idDuenio">
					    <button type="submit">solicitar turno</button>
  					</form:form>
  					<br>
  					
				</c:forEach>
</body>
</html>