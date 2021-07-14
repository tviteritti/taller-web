<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hola!</title>


<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="shortcut icon" type="image/png" href="img/logo.png">
  <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>
</head>
<body>

	<%@ include file="menuCuentaDuenio.jsp"%>
	
	 <div class="card-header">
	    
	    
	    <h3 class="card-title">Hola ${usuario.user} esta es tu cuenta</h3>
	  
	  
	    
	  </div>
	  <div class="card-body">
	    <p class="card-text" name="descripcion">
	    	<c:if test="${empty usuario.descripcion}">
			    	
			     </c:if>
			      <c:if test="${not empty usuario.descripcion}">
			    	${usuario.descripcion}
			     </c:if>
	    </p>
	    <form action="verPerfil" method="post">
		   <input type="hidden" name="idUsuario" value="${usuario.id}"/>
		   <div class="text-center display-4" >
		   <i class="fas fa-user"></i>
		   </div>
		    <button type="submit" class="btn btn-success">Ver perfil</button>
	   </form>
	   <c:if test="${not empty turnos}">
	    <div class="container mt-3" style="matgin-top:50px;">
    	<div class="row">
    		<div class="col-12">
    			<h2>Proximos turnos</h2>
				<table class="table table-striped ">
	  				<thead class="thead-inverse">
	  					<tr>
	  						<th>nombre</th>
	  						<th>apellido</th>
	  						<th>telefono</th>
					  		<th>email</th>
					  		<th>fecha</th>
					  		<th>horario</th>
					  		<th>servicio</th>
	  					</tr>
					</thead>
	     			<c:forEach items="${turnos}" var="turno">
						<tr>
							<td>${turno.veterinario.nombre}</td>
							<td>${turno.veterinario.apellido}</td>
							<td>${turno.veterinario.telefono}</td>
							<td>${turno.veterinario.email}</td>
							<td>${turno.fecha}</td>
							<td>${turno.horario}</td>
							<td>${turno.servicio}</td>
						</tr> 
					</c:forEach>
	  	
	  			</table>
			</div>
		</div>
	</div>
	</c:if> 
	</div>
</div>

 	
   
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