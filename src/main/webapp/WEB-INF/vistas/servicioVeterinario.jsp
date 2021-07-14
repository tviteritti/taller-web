<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Encontra el especialista y solicita un turno</title>
<link rel="shortcut icon" type="image/png" href="img/logo.png">
 <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>
</head>
<body>
  <h4 class="text-center container p-5">Encontra el especialista y solicita un turnoa</h4>
  

	<c:forEach items="${veterinarios}" var="v">
			     
		<form action="generarTurno" method="post">
			
					   	
			<input type="hidden" name="veterinarioId" value="${v.id}"/>
					  
					    
			<div class="jumbotron container p-3" style="width:50%">
				<h4 class="text-center"> <c:out value="${v.nombre} ${v.apellido}" /></h4>
				<c:url var="linkVerPerfil" value="/formVerPerfilVerterinario">
				  	<c:param name="veterinarioId" value="${v.id }"/>
				  	<c:param name="id_zona" value="${id_zona }"/>
				  	<c:param name="id_especialidad" value="${id_especialidad}"/>
				  	<c:param name="duenioId" value="${duenioId}"/>
				</c:url>
				<a href="${linkVerPerfil }"><input type="button" class="btn btn-info container" value="ver perfil"/></a>
				 <p class="lead">
				<c:if test = "${errorSinPlan != null}">
					<div class="input-group p-1"><h6>${errorSinPlan}. </h6></div>
					<div class="input-group p-1"><p> Si continua se le acreditaran a la cuota ${v.precioSesion} $. </p></div>
					<div class="input-group p-1"><p><a href="loginVeterinaria">volver al inicio</a></p></div>
				</c:if>
				<c:if test = "${errorExede != null}">
					<div class="input-group p-1"><h6>${errorExede}. </h6></div>
					<div class="input-group p-1"><p> Si continua se le acreditaran a la cuota ${v.precioSesion} $. </p></div>
					<div class="input-group p-1"><p><a href="loginVeterinaria">volver al inicio</a></p></div>				  		
				</c:if>
				
				<c:forEach items="${calificacion}" var="cal">
					<c:if test = "${v.id == cal.veterinario.id}">				
						<p><p>Calificacion: ${cal.calificacion} / 10</p>
					</c:if>
				</c:forEach>
				<hr class="my-4">
				<p>Ubicacion: <c:out value="${v.direccion.calle} ${v.direccion.numero}"/> , ${v.direccion.localidad.descripcion} </p>
							  
				<input type="hidden" name="direccion" value="${v.direccion.calle} ${v.direccion.numero}" >
				<input type="hidden" name="localidad" value=" ${v.direccion.localidad.descripcion}">
				<input type="hidden" value="${servicio}" name="servicio">
				<input type="hidden" value="${zona}" name="zona">
				<hr class="my-4">
				<p>Turnos</p>
				<c:forEach items="${turnosPorVT}" var="t">
					     
					<c:if test="${t.veterinario.id eq v.id}">
						 <c:if test="${t.estado == false}">
							<input type="radio" name="idTurno" value="${t.id}" id="turno${t.id}" required="required">
									    
							<label for="turno${t.id}">
								<p>Fecha: 
									       
									<c:out value="${t.fecha}" />
									<input type="hidden" value="${t.fecha}" name="fecha">
									  
									  Hora: 
									       
									 <c:out value="${t.horario}" />
									 <input type="hidden" value="${t.horario}" name="hora"> 
									         
								 </p>
							</label><br>
						 </c:if>  
					</c:if>      
				</c:forEach>
					    
					    


				<br>
				<h3>Elija su mascota</h3><br>
				<div class="input-group p-2">
					<select class="form-control" name="id_mascotas">
						<option >Seleccione su mascota</option> 
						<c:forEach items="${listaDeMascotas}" var="mascota">
							<option value="${mascota.id}">${mascota.nombre}</option>
						 </c:forEach>
					</select>
				</div><br>                    


					    
				<input type="hidden" value="${duenio.id}" name="idDuenio">
				<input type="hidden" value="true" name="notificacion">
				<button type="submit" class="btn btn-primary">solicitar turno</button>
			</div>
  		</form>
  		<br>
  					
	</c:forEach>
				
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