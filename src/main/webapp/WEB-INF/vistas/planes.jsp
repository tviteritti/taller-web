<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Planes</title>
</head>
<body>

 <div>
   		<c:if test = "${listaContrataciones eq null}">
   			<c:forEach var="plan" items="${listaPlanes}">
   		
			   	<c:url var="linkTomarUnPlan" value="/tomarUnPlan">
					<c:param name="planId" value="${plan.id }"/>	
					<c:param name="duenioId" value="${duenio.id}"/>
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
				  <c:when test="${contrataciones.duenio.id == duenio.id}">
				  	<c:if test = "${contrataciones.valor != contrataciones.pago}">
				  		<c:url var="linkPagarPlan" value="/pagarPlan">
							 <c:param name="contratacionId" value="${contrataciones.id }"/>	
						</c:url>
				   		<p>valor a pagar = ${contrataciones.valor} $</p>
				   		<a href="${linkPagarPlan }"><input type="button" value="pagar plan" 
			   			onClick="if(!(confirm('¿Estas seguro que quieres pagar?'))) return false"/></a>
			   		</c:if>
			   		<c:if test = "${contrataciones.valorExtra != contrataciones.pagoExtra}">
				  		<c:url var="linkPagarPlanExtra" value="/pagarPlanExtra">
							 <c:param name="contratacionId" value="${contrataciones.id }"/>	
						</c:url>
						<p>A tomado turnos extra:</p>
				   		<p>valor a pagar = ${contrataciones.valorExtra} $</p>
				   		<a href="${linkPagarPlanExtra }"><input type="button" value="pagar sesion Extra" 
			   			onClick="if(!(confirm('¿Estas seguro que quieres pagar?'))) return false"/></a>
			   		</c:if>
			   		<c:if test = "${contrataciones.valor == contrataciones.pago}">
			   			<p>${duenio.user} se encuentra al dia con respecto al pago del ${contrataciones.plan.descripcion}, recuerde el plan vence el dia ${contrataciones.hasta}</p>			   			
			   		</c:if>
				  </c:when>
				  <c:when test="${contrataciones.duenio.id != duenio.id}">
				    <c:forEach var="plan" items="${listaPlanes}">
   		
			   			<c:url var="linkTomarUnPlan" value="/tomarUnPlan">
							 <c:param name="planId" value="${plan.id }"/>	
							 <c:param name="duenioId" value="${duenio.id}"/>
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
</body>
</html>