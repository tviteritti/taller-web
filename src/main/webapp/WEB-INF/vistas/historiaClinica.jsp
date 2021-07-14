<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Historia Clinica</title>
<link rel="shortcut icon" type="image/png" href="img/logo.png">
 <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>

</head>
<body>


  <c:if test="${not empty hc}">	
  
  	<div class="card container" style="width: 50rem;">	  
	<form action="actualizarHistoriaClinica" method="post">
		 
			  <div class="card-body">
			    <h5 class="card-title">Paciente: ${mascota.nombre}</h5>
			    <p class="card-text">Nombre Responsable: ${duenio.nombre}</p>
			    <p class="card-text">Fecha Nacimiento: ${mascota.fecha_nacimiento}</p>
			  </div>
		 <br>
			<c:forEach items="${hc}" var="hc">
			
			    <ul class="list-group list-group-flush">
			    <li class="list-group-item"><strong>Cod: ${hc.id}</strong></li>
			    <li class="list-group-item">Veterinario:  ${hc.veterinario.nombre} ${hc.veterinario.apellido}</li>
			    <li class="list-group-item">Fecha: ${hc.fecha}</li>
			    <li class="list-group-item">Diagnostico: ${hc.diagnostico}</li>
			    <li class="list-group-item">Tratamiento: ${hc.tratamiento}</li>
			  </ul>
			  
			</c:forEach>
	
	 <div class="card-body">
			   
			  </div>
			  
			
		 <input type="hidden" name="duenio" value="${duenio.id}"/>
		 <input type="hidden" name="mascota" value="${mascota.id}"/>
		 <input type="hidden" name="veterinario" value="${veterinario.id}"/>
		 <button type="submit" class="btn btn-success  btn-block">Actualizar</button>
    </form>
    
    </div>
    
  </c:if>
   <c:if test="${empty hc}">
   
	   <div class="alert alert-warning" role="alert">
	  		El paciente no se encuentra registrado en ninguna historia clinica
		</div>
      
      <br>
      <form action="cargarHistoriaClinica" method="post">
         <input type="hidden" name="duenio" value="${duenio.id}"/>
		 <input type="hidden" name="mascota" value="${mascota.id}"/>
		 <input type="hidden" name="veterinario" value="${veterinario.id}"/>
		 <button class="btn btn-success">cargar historia clinica</button>
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