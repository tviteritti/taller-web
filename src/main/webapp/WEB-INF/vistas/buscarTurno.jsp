<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscar turno</title>
 <!-- Bootstrap CSS -->
 <link rel="shortcut icon" type="image/png" href="img/logo.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>
</head>
<body>
      <h4 class="text-center container p-2">Busque un turno mas cercano</h4>
      <form:form action="buscarServicioVeterinario" method="post" name="formTurno" modelAttribute="zona" class="container">
        <div class="form-group">
		    <label for="exampleFormControlSelect1">Servicio</label>
			<select class="form-control" name="id_especialidad">
				  <option >Seleccione Especialidad</option> 
				  <c:forEach items="${listadoEspecialidad}" var="especialidad">
				       <option value="${especialidad.id}">${especialidad.descripcion}</option>
				 </c:forEach>
			</select>
		 </div>
		 <br>

					

		   <div class="form-group">
			<label for="exampleFormControlSelect1">Zona</label>
			<select class="form-control" name="id_zona">
				 <option >Seleccione Zona</option> 
				  <c:forEach items="${listadoZonas}" var="zona">
				      <option value="${zona.id}">${zona.descripcion}</option>
				  </c:forEach>
			</select>
            </div>
			      
			      <br><br>
			  <input type="hidden" value="${duenioId}" name="idDuenio">
		      <button type="submit" class="btn btn-success">Buscar turno</button>
		      
     </form:form>


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