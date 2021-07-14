<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis notificaciones</title>
<link rel="shortcut icon" type="image/png" href="img/logo.png">
 <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>

</head>
<body>
 	<h4 class="text-center container p-4">Mis Notificaciones</h4>
   <c:forEach items="${consultas}" var="c">
    <c:if test="${notificacion.consulta.id eq c.id}">
    		<c:if test="${usuario eq 'duenio'}">
    		
    		<div class="card text-center p-3 container">
					    <blockquote class="blockquote mb-0 p-3">
					     <h5 class="card-title">${c.asunto}</h5>
					      <p class="display-5">${c.descripcion}</p>
					      <footer class="blockquote-footer ">
					        <small class="text-dark">
					          Realizado por <cite title="Source Title">${c.usuario.user}</cite>
					        </small>
					      </footer>
					    </blockquote>
					    
				<div class="d-flex flex-column m-3" style="border-left:3px solid #17a2b8">
				   <p class="text-left ml-2">${c.respuesta.descripcion} - <cite title="Source Title"><strong>${c.userRespuesta}</strong></cite></p>
				</div>
		   </div>
		   
		   
		  </c:if> 
		   
    </c:if>
     </c:forEach>
     
	    <c:if test="${usuario == 'veterinario'}">
	    <table class="table container">
		  <thead>
		    <tr>
		      <th scope="col">Fecha</th>
		      <th scope="col">Hora</th>
		      <th scope="col">Servicio</th>
		      <th scope="col">Solicitado por</th>
		    </tr>
		  </thead>
		  <tbody>
		  		<tr>
				      <td><c:out value="${notificacion.turno.fecha}"/></td>
				   
				      <td> <c:out value="${notificacion.turno.horario}" /></td>
				      
				      <td><c:out value="${notificacion.turno.servicio}" /></td>
				    
				      <td><c:out value="${notificacion.turno.duenio.nombre}"/> <c:out value="${notificacion.turno.duenio.apellido}" /></td>
				 
				</tr>
	
			 </tbody>
		</table>
		  
	    </c:if>
  
    
    <a href="cuentaDuenio" class="btn btn-success btn-lg"><i class="fas fa-caret-left"></i> volver</a>
    
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