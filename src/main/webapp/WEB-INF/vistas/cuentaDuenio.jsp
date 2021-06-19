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

  <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
 <h4>Hola ${usuario.user} esta es tu cuenta</h4>
 <br>
    <form action="verPerfil" method="post">
	   <input type="hidden" name="duenioId" value="${usuario.id}"/>
	    <button type="submit">ver Perfil</button>
   </form>
   <br><br>
 
  <h4>Turno</h4>
  
    <form action="misTurnos" method="post">
	   <input type="hidden" name="duenioId" value="${usuario.id}"/>
	    <button type="submit">mis turnos</button>
   </form>
   
   <form action="buscarTurno" method="post">
   <input type="hidden" name="duenioId" value="${usuario.id}"/>
    <button type="submit">solicitar turno</button>
   </form>
  
   <a href="cargarMascota">cargar mascota</a>
   
   <div>
   		<c:if test = "${listaContrataciones eq null}">
   			<c:forEach var="plan" items="${listaPlanes}">
   		
			   	<c:url var="linkTomarUnPlan" value="/tomarUnPlan">
					<c:param name="planId" value="${plan.id }"/>	
					<c:param name="duenioId" value="${usuario.id}"/>
				</c:url>
						
			   	<div>
			   		<p>${plan.descripcion}</p>
			   		<p>Duracion: ${plan.duracion} meses</p>
			   		<p>cantidad de turnos: ${plan.cantidadTurnos} turnos</p>
			   		<p>Precio: ${plan.precio} $</p>
			   		<a href="${linkTomarUnPlan }"><input type="button" value="tomar plan" 
			   		onClick="if(!(confirm('¿Estas seguro que quieres tomar este plan?'))) return false"/></a>
			   	</div>	
   			</c:forEach>
   		</c:if>
   		<c:forEach var="contrataciones" items="${listaContrataciones}">
		 	<c:choose>
				  <c:when test="${contrataciones.duenio.id == usuario.id}">
				  	<c:if test = "${contrataciones.valor != contrataciones.pago}">
				  		<c:url var="linkPagarPlan" value="/pagarPlan">
							 <c:param name="contratacionId" value="${contrataciones.id }"/>	
						</c:url>
				   		<p>valor a pagar = ${contrataciones.valor} $</p>
				   		<a href="${linkPagarPlan }"><input type="button" value="pagar plan" 
			   			onClick="if(!(confirm('¿Estas seguro que quieres pagar?'))) return false"/></a>
			   		</c:if>
			   		<c:if test = "${contrataciones.valor == contrataciones.pago}">
			   			<p>${usuario.user} se encuentra al dia con respecto al pago del ${contrataciones.plan.descripcion}, recuerde el plan vence el dia ${contrataciones.hasta}</p>			   			
			   		</c:if>
				  </c:when>
				  <c:when test="${contrataciones.duenio.id != usuario.id}">
				    <c:forEach var="plan" items="${listaPlanes}">
   		
			   			<c:url var="linkTomarUnPlan" value="/tomarUnPlan">
							 <c:param name="planId" value="${plan.id }"/>	
							 <c:param name="duenioId" value="${usuario.id}"/>
						</c:url>
						
			   			<div>
			   				<p>${plan.descripcion}</p>
			   				<p>Duracion: ${plan.duracion} meses</p>
			   				<p>cantidad de turnos: ${plan.cantidadTurnos} turnos</p>
			   				<p>Precio: ${plan.precio} $</p>
			   				<a href="${linkTomarUnPlan }"><input type="button" value="tomar plan" 
			   				onClick="if(!(confirm('¿Estas seguro que quieres tomar este plan?'))) return false"/></a>
			   			</div>	
   					</c:forEach>
				 </c:when>
			</c:choose>
		</c:forEach>
		
   		
   </div>
  
   <br><br>
   <h4>Consultas</h4>
   <a href="consultar">realizar consulta</a>
   <br><br>
  <a href="cerrarSesion">cerrar sesion</a>
  
 
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