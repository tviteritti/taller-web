<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Cargar Historia Clinica</title>
<!-- Bootstrap CSS -->
<link rel="shortcut icon" type="image/png" href="img/logo.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>
</head>
<body>

   <h4 class="alert-heading text-center p-3">Complete los campos para cargar la historia clinica</h4>
   
   <br>
   
   <div class="container">
		  <form action="guardarHistoriaClinica" method="post" >
		      
		      <h5 class="alert-heading text-center p-3">Mascota: </h5>
		      
		       <div class="form-group">
		       Nombre:
		        <input type="text" name="nombreMascota" class="form-control" value="${mascota.nombre}">
		       </div>
		     
		      <br>
		      
		       <div class="form-group d-flex justify-content-center">
		       Fecha de Nacimiento:
		       <input type="date" name="fechaNacimiento" class="form-control" value="${mascota.fecha_nacimiento}">
		       </div>
		       
		       <br>
		       
		       <div class="form-group">
		       Tipo de Animal:
		       <br>
		       <select name="tipoMascota" class="form-control">
				 <option> ${mascota.tipo.descripcion} </option> 
					<c:forEach items="${tipos}" var="t">
					  <option value="${t.descripcion}">${t.descripcion}</option>
				   </c:forEach>
			 	</select>
			 	
			 	</div>
			 	
			 	<br>
			 	
			   <input type="hidden" value="${mascota.id}" name="idMascota">
		       <br>
		        <h5 class="alert-heading text-center p-3">Datos de contacto:</h5>
		       <br>
		       <h5 class="alert-heading text-center p-3">Duenio:</h5>
		       <br>
		       
		       <div class="form-group">
		       Nombre:
		       <br>
		       <input type="text" name="dNombre" class="form-control" value="${duenio.nombre}">
		       </div>
		       
		       <br>
		       
		       <div class="form-group">
		       Apellido :
		       <br>
		       <input type="text" name="dApellido" class="form-control" value="${duenio.apellido}">
		       </div>
		       
		       <br>
		       <div class="form-group">
		       Telefono de contacto:
		       <br>
		       <input type="text" name="telefonoDuenio" class="form-control"  value="${duenio.telefono}">
		       </div>
		       
		       <br>
		       
		       <div class="form-group">
		       E-mail:
		       <br>
		       <input type="text" name="emailDuenio" class="form-control" value="${duenio.email}">
		       </div>
		       
		       <br>
		       
		       <div class="form-group">
		       Direccion:
		       <br>
		       <input type="text" name="direccionDuenio" class="form-control" value="${duenio.direccion.calle} ${duenio.direccion.numero}">
		       </div>
		       
		       <br>
		       
		       <div class="form-group">
		       Localidad:
		       <input type="text" name="localidadDuenio" class="form-control" value="${duenio.direccion.localidad.descripcion}">
		       <input type="hidden" value="${duenio.id}" class="form-control" name="idDuenio" >
		       </div>
		       
		       <br>
		      <label>Veterinario: </label>
		       <br>
		       
		       <div class="form-group">
		       Nombre:
		       <br>
		      <input type="text" name="vNombre" class="form-control" value="${veterinario.nombre}">
		      </div>
		      
		      <br>
		      
		      <div class="form-group">
		       Apellido:
		       <br>
		      <input type="text" name="vApellido" class="form-control" value="${veterinario.apellido}">
		      <input type="hidden" name="idVeterinario" value="${veterinario.id}">
		      </div>
		      
		      <br>
		      
		      <div class="form-group">
		      <label>Fecha: </label>
		      <br>
		      <input type="date" class="form-control" name="fechaHC">
		      </div>
		      
		      <br>
		      
		      <div class="form-group">
		      <label>Diagnostico: </label>
		      <br>
		      <input type="text" class="form-control" name="diagnostico">
		      </div>
		      
		      <br>
		      
		      <div class="form-group">
		      <label>Tratamiento: </label>
		      </div>
		      
		      <br>
		      
		      <div class="form-group">
		      <textarea name="tratamiento" class="form-control"></textarea>
		      </div>
		      
		      <br>
		      
		      <div class="form-group container">
		      <button type="submit" class="btn btn-success  btn-block">Guardar</button>
		      </div>
		   
		   </form>
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