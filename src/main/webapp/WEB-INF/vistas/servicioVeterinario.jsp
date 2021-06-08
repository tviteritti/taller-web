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
			     
				     <form action="generarTurno" method="post">
					    <p> 
					   	 <c:out value="${v.nombre} ${v.apellido}" />
					   	 <input type="hidden" name="veterinarioId" value="${v.id}"/>
					    </p>
					    <p>Calificacion</p>
					    <p>Descripcion: </p>
					    <p>
					    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
						tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
						quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
						consequat.
					    </p>
	
					    <p>Ubicacion:</p> <p >
					    <c:out value="${v.direccion.calle} ${v.direccion.numero}"/>
					   
					     <input type="hidden" name="direccion" value="${v.direccion.calle} ${v.direccion.numero}" >
					    </p>
					    <p >
					     ${v.direccion.localidad.descripcion}
					     <input type="hidden" name="localidad" value=" ${v.direccion.localidad.descripcion}">
					    </p>
					    
					    <input type="hidden" value="${servicio}" name="servicio">
					    <input type="hidden" value="${zona}" name="zona">
					    <p>Turnos</p>
					    <br>
					     <c:forEach items="${turnosPorVT}" var="t">
					     
						     <c:if test="${t.veterinario.id eq v.id}">
						         
						         
						        <input type="radio" name="turno" id="turno"/>
							    <input type="hidden" name="idTurno" value="${t.id}">
							    
							    <label for="turno">
							      <p>Fecha: 
							        <p >
							         <c:out value="${t.fecha}" />
							         <input type="hidden" value="${t.fecha}" name="fecha">
							         </p> 
							       </p>
							      <p>Hora: 
							        <p > 
							           <c:out value="${t.horario}" />
							           <input type="hidden" value="${t.horario}" name="hora"> 
							         </p> 
							     </p>
							    </label><br>
							 
							</c:if>      
					    </c:forEach>
					    <input type="hidden" value="${duenio.id}" name="idDuenio">
					    <button type="submit">solicitar turno</button>
  					</form>
  					<br>
  					
				</c:forEach>
</body>
</html>