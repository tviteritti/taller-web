<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis Turnos</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
      <h4 class="text-center container p-2">Turnos Solicitados</h4>
      <br>
       <c:if test="${not empty turnos}">
       	<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Fecha</th>
		      <th scope="col">Hora</th>
		      <th scope="col">Servicio</th>
		      <th scope="col">Veterinario</th>
		      <th scope="col">Cancelar turno</th>
		    </tr>
		  </thead>
		  <tbody>
		  
		 <c:forEach items="${turnos}" var="t">
			  
					<form action="cancelarTurno" method="post">
					
					<tr>
				   
				      <td><p name="fechaTurno"><c:out value="${t.fecha}" /></p></td>
				   
				      <td> <p name="fechaHora"><c:out value="${t.horario}" /></p></td>
				    
				      <td><p name="servicioTurno"><c:out value="${t.servicio}" /></p></td>
				   
				      <td><p name="veterinarioTurno"><c:out value="${t.veterinario.nombre} ${t.veterinario.apellido}" /></p></td>
				    
				      <td>
				       <input type="hidden" name="id_turno" value="${t.id}"/>
					   <button type="submit" class="btn btn-danger">cancelar turno</button>
					   </td>
					 
				    </tr>
					   
					</form>	     
						  
			  					
			 </c:forEach> <!---->
			 
			 </tbody>
		</table>		 
	</c:if>
	<c:if test="${empty turnos}">
	   
	   <div class="alert alert-warning " role="alert">
			 <h3 class="text-center container p-2">Aun no posee turnos</h3>
			 <hr>
		     <p  class="text-center"> ¿Desea solicitar turno?</p>
		</div>
	   <form action="buscarTurno" method="get">
	      <input type="hidden" name="duenioId" value="${duenio.id}"/>
	     <button class="btn btn-success mx-3">solcitar turno</button>
	   </form>
	</c:if> 
	
	
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" 
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" 
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>