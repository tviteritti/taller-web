<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Actualizar Historia Clinica</title>
  <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>

</head>
<body>
  <h4 class="alert-heading text-center p-3">Agregue los datos necesarios para actualizar la Historia Clinica</h4>
  
  <div class="container">
		   <form action="guardarHistoriaClinica" method="post">
		      
		      <h5 class="alert-heading text-center p-3">Mascota: </h5>
		      
		       <div class="form-group">
			       Nombre:
			      <input type="text" value="${mascota.nombre}" class="form-control">
			      <input type="hidden" value="${mascota.id}" name="idMascota" >
		      </div>
		      
		      <br>
		       <div class="form-group">
		       Fecha de Nacimiento:
		       <br>
		       <input type="text" value="${mascota.fecha_nacimiento}" class="form-control">
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
		       
		       <h5 class="alert-heading text-center p-3">Datos de contacto:</h5>
		       <br>
		      <h5 class="alert-heading text-center p-3"> Duenio:</h5>
		       <br>
		       
		       <div class="form-group">
		       Nombre y Apellido :
		       <input type="text" value="${duenio.nombre} ${duenio.apellido}" class="form-control">
		       </div>
		       
		       <br>
		       
		       <div class="form-group">
		       Telefono de contacto:
		       <br>
		       <input type="text" value="${duenio.telefono}" class="form-control">
		       </div>
		       
		       <br>
		       
		       <div class="form-group">
		       E-mail:
		       <br>
		       <input type="text" value="${duenio.email}" class="form-control">
		       </div>
		       
		       <br>
		       
		       <div class="form-group">
		       Direccion:
		       <br>
		       <input type="text" value="${duenio.direccion.calle} ${duenio.direccion.numero}" class="form-control">
		       </div>
		       
		       <br>
		       
		       <div class="form-group">
		       Localidad:
		       <br>
		       <input type="text" value="${duenio.direccion.localidad.descripcion}" class="form-control">
		       <input type="hidden" value="${duenio.id}" name="idDuenio">
		       </div>
		       
		       
		       <br>
		      <c:forEach items="${historiaClinica}" var="hc">
		       <strong class="p-3">Cod: ${hc.id}</strong>
		       <br>	
		       <h5 class="alert-heading text-center p-3">Veterinario: </h5>
		       <br>
		       
		       <div class="form-group">
		       Nombre:
		      <input type="text" name="vNombre" value="${hc.veterinario.nombre}" class="form-control">
		      </div>
		      
		      <div class="form-group">
		       Apellido:
		      <input type="text" name="vApellido" value=" ${hc.veterinario.apellido}" class="form-control">
		      </div>
		      
		      <br>
		      
		      <div class="form-group">
		      <label>Fecha: </label>
		      <input type="date" name="fecha" value="${hc.fecha}" class="form-control">
		      </div>
		      
		      <br>
		      
		      <div class="form-group">
		      <label>Diagnostico: </label>
		      <br>
		      <input type="text" name="diagnostico" value="${hc.diagnostico}" class="form-control">
		      </div>
		      
		      <br>
		      
		      <div class="form-group">
		      <label>Tratamiento: </label>
		      <textarea name="tratamiento" class="form-control" >${hc.tratamiento}</textarea>
		      </div>
		      
		      <br>
		      </c:forEach>
		      <br>
		      <label>Veterinario: </label>
		      <div class="form-group">
		        Nombre:
		      <input type="text" name="vNombre" value="${veterinario.nombre}" class="form-control">
		      </div>
		      
		      <div class="form-group">
		       Apellido:
		      <input type="text" name="vApellido" value="${veterinario.apellido}" class="form-control">
		      <input type="hidden" value="${veterinario.id}" name="idVeterinario">
		      </div>
		      
		      <br>
		      
		      <div class="form-group">
		      <label>Fecha: </label>
		      <input type="date" name="fechaHC" class="form-control">
		      </div>
		      
		      <div class="form-group">
		      <label>Diagnostico: </label>
		      <input type="text" name="diagnostico" class="form-control">
		      </div>
		      
		      <div class="form-group">
		      <label>Tratamiento: </label>
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