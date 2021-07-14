<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="shortcut icon" type="image/png" href="img/logo.png">
  <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
  
  <div class="container">
        <div class="row vh-100 justify-content-center align-items-center">
            <div class="col-auto bg-light p-5">
                <form:form action="procesarDatosVeterinario" method="POST" modelAttribute="usuario">
                
                	<div class="input-group p-2">
                        <h3>Registrar Veterinario</h3>
                        <form:hidden path="id"/>
                        <form:hidden path="rol" value="veterinario"/>
                    </div>


					<div class="form-row">
					   <div class="form-group col-md-6">
							<form:input class="form-control" placeholder="email" path="email"/>
					   </div>
					   <div class="form-group col-md-6">
							<form:input class="form-control" placeholder="user" path="user"/>
					   </div>
					</div>
					                    
					<div class="form-row">
					   <div class="form-group col-md-6">
							<form:input class="form-control" type="password" placeholder="password" path="password"/>
					   </div>
					   <div class="form-group col-md-6">
							<input type="password" class="form-control" name="re-password" placeholder="re ingresar contraseña">
					   </div>
					</div>
                    
					<hr/><br>
                    
                   <div class="form-row">
					   <div class="form-group col-md-6">
							<form:input class="form-control" path="nombre" placeholder="nombre"/>
					   </div>
					   <div class="form-group col-md-6">
							<form:input class="form-control" path="apellido" placeholder="apellido"/>
					   </div>
					</div>
					<div class="form-row">
					   <div class="form-group col-md-6">
							<form:input class="form-control" path="telefono" placeholder="telefono"/>
					   </div>
					   <div class="form-group col-md-6">
							<form:input type="number" class="form-control" placeholder="precio de una Sesion" path="precioSesion"/>
					   </div>
					</div>
                                               

                    <div class="form-group col-md-12">
                    <select class="form-control" name="id_especialidad">
				       <option >Seleccione Especialidad</option> 
				       <c:forEach items="${listadoEspecialidad}" var="especialidad">
				          <option value="${especialidad.id}">${especialidad.descripcion}</option>
				       </c:forEach>
					</select>
					</div>


					<hr/><br>

                    
                   <div class="form-row">
					   <div class="form-group col-md-6">
							<input type="text" class="form-control" placeholder="calle" name="calle"/>
					   </div>
					   <div class="form-group col-md-6">
							<input type="text" class="form-control" placeholder="nro calle" name="numero"/>
					   </div>
					</div>
					<div class="form-row">
					   <div class="form-group col-md-6">
							<input type="text" class="form-control" placeholder="piso" name="piso"/>
					   </div>
					   <div class="form-group col-md-6">
							<input type="text" class="form-control" placeholder="departamento" name="departamento"/>
					   </div>
					</div>                             
					
					<div class="form-group col-md-12">
					<select class="form-control" name="id_zona">
				       <option >Seleccione Zona</option> 
				       <c:forEach items="${listadoZonas}" var="zona">
				          <option value="${zona.id}">${zona.descripcion}</option>
				       </c:forEach>
					</select>
					</div>
					
					<br><br>													
										
					
					<input type="submit" class="btn btn-success w-100" value="registrar">
					<br><br>
					<h4><span>${error}</span></h4>
                </form:form>
            </div>
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