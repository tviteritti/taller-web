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
<link rel="shortcut icon" type="image/png" href="img/logo.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>
</head>
<body>

      <%@ include file="menuCuentaDuenio.jsp"%>
      </div>
      
      <h4 class="text-center container p-3">Turnos Solicitados</h4>
      <br>
       <c:if test="${not empty turnos}">
       	<table class="table container">
		  <thead>
		    <tr>
		      <th scope="col">Fecha</th>
		      <th scope="col">Hora</th>
		      <th scope="col">Servicio</th>
		      <th scope="col">Veterinario</th>
		      <th scope="col">Cancelar turno</th>
		      <th scope="col">Calificar</th>
		    </tr>
		  </thead>
		  <tbody>
		  
		 <c:forEach items="${sinVoto}" var="t">
			  
					<form action="cancelarTurno" method="post">
					
					<tr>
				   
				      <td><p name="fechaTurno"><c:out value="${t.fecha}" /></p></td>
				   
				      <td> <p name="fechaHora"><c:out value="${t.horario}" /></p></td>
				    
				      <td><p name="servicioTurno"><c:out value="${t.servicio}" /></p></td>
				   
				      <td><p name="veterinarioTurno"><c:out value="${t.veterinario.nombre} ${t.veterinario.apellido}" /></p></td>
				    
				      <td>
				       <input type="hidden" name="id_turno" value="${t.id}"/>
					   <button type="submit" class="btn btn-danger">Cancelar turno</button>
					   </td>
					   
					   
					   	<td>
							<button type="button" id="btnModal" class="btn btn-primary" data-id="${t.veterinario.id}" data-toggle="modal" data-target="#exampleModal">
								Calificar veterinario
							</button>
	
						</td>
	
						
				    </tr>
					   
					</form>	     
						  
					 
			 </c:forEach>
			 <c:forEach items="${conVoto}" var="t">
			  
					<form action="cancelarTurno" method="post">
					
					<tr>
				   
				      <td><p name="fechaTurno"><c:out value="${t.fecha}" /></p></td>
				   
				      <td> <p name="fechaHora"><c:out value="${t.horario}" /></p></td>
				    
				      <td><p name="servicioTurno"><c:out value="${t.servicio}" /></p></td>
				   
				      <td><p name="veterinarioTurno"><c:out value="${t.veterinario.nombre} ${t.veterinario.apellido}" /></p></td>
				    
				      <td>
				       <input type="hidden" name="id_turno" value="${t.id}"/>
					   <button type="submit" class="btn btn-danger">Cancelar turno</button>
					   </td>
					   
	
						
				    </tr>
					   
					</form>	     
						  
					 
			 </c:forEach>  
			
			 </tbody>
		</table>
		<!-- Modal -->
							<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog" role="document">
							    <div class="modal-content">
							      	<div class="modal-header">
								        <h4 class="modal-title" id="exampleModalLabel">Calificar</h4>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
							      	</div>
							      	<form action="calificar" method="post">
								      <div class="modal-body">
								        <input type="hidden" name="id_veterinario" id="idV"/>
								        <h5>Calificar de 0 a 10:</h5>
								        <input type="number" name="calificacion" min="0" max="10"/>
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
								        <button type="submit" class="btn btn-primary">Calificar</button>
							       </form>
							      </div>
							    </div>
							  </div>
							</div>			 
	</c:if>
	<c:if test="${empty turnos}">
	   
	   <div class="alert alert-warning " role="alert">
			 <h3 class="text-center container p-2">Aun no posee turnos</h3>
			 <hr>
		     <p  class="text-center"> ¿Desea solicitar turno?</p>
		</div>
	   <form action="buscarTurno" method="get">
	      <input type="hidden" name="duenioId" value="${duenio.id}"/>
	     <button class="btn btn-success mx-3">Solcitar turno</button>
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
    							<script type="text/javascript">
								$(document).on("click", "#btnModal", function(){
									var idV=$(this).data('id');
									$("#idV").val(idV);
								})
							</script>
</body>
</html>