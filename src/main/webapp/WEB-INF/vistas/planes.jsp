<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Planes</title>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>
</head>
<body>
	<div class="card text-center">
	<div class="row card-header">
	    	<div class="col-3"> <a href="loginVeterinaria"><i class="fas fa-home display-6 text-body"></i></a></div>
	    	<div class="col-2">  <i class="fas fa-bell display-6"> <input type="hidden" name="notificaciones"/> </i></div>
	    	
	    		<c:if test="${notificacion.estado}">
	    				<div class="dropdown">
							  <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							    1
							  </a>
							
							  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
							    <a class="dropdown-item" href="#">Action</a>
							    <a class="dropdown-item" href="#">Another action</a>
							    <a class="dropdown-item" href="#">Something else here</a>
							  </div>
						</div>
	    		</c:if>
	    	
	    	<div class="col-2"><i class="fas fa-user"></i></div>
	    	<div class="col-2"><i class="fas fa-moon"></i></div>
	    	<div class="col-3">
		    	<a href="cerrarSesion" class="text-decoration-none text-body">cerrar sesion
		    		<i class="fas fa-sign-out-alt display-5 text-body"></i>
		    	</a>
	    	</div>
	    	<div class="border-bottom"></div>
	    </div>
	    <nav class="navbar navbar-dark bg-dark">
	
		<ul class="navbar-nav mr-auto d-flex flex-row">
	      <li class="nav-item d-inline mr-5">
	       
	        <form action="mascota" method="post">
	          <input type="hidden" name="duenioId" value="${usuario.id}"/>
	          <button type="submit" class="btn btn-link text-decoration-none text-white-50">Mi mascota</button>
	        </form>
	      </li>
	      <li class="nav-item d-inline mr-5">
	        
	        <form action="turnos" method="post">
	          <input type="hidden" name="duenioId" value="${usuario.id}"/>
	          <button type="submit" class="btn btn-link text-decoration-none text-white-50">Turnos</button>
	        </form>
	      </li>
	      <br>
	      <li class="nav-item d-inline mr-5">
	      <form action="planes" method="post">
	          <input type="hidden" name="duenioId" value="${usuario.id}"/>
	          <button type="submit" class="btn btn-link text-decoration-none text-white-50">Planes</button>
	        </form>
	       
	      </li>
	      <br>
	      <li class="nav-item d-inline mr-5">
	        <form action="consultas" method="post">
	          <input type="hidden" name="duenioId" value="${usuario.id}"/>
	          <button type="submit" class="btn btn-link text-decoration-none text-white-50">Consultas</button>
	        </form>
	      </li>
       </ul>
	   
	</nav>
	
	</div>
		 	
 		
   	  	<c:forEach var="contrataciones" items="${listaContrataciones}">
   		<c:choose>
		 	<c:when test = "${contrataciones.duenio.id == duenio.id}">
			 	<c:if test = "${contrataciones.valor == null}">
			 	   <c:if test = "${listaPlanes == null}">
			 		<c:forEach var="plan" items="${listaPlanes}">
							<c:url var="linkTomarUnPlan" value="/tomarUnPlan">
								<c:param name="planId" value="${plan.id }"/>	
								<c:param name="duenioId" value="${duenio.id}"/>
							</c:url>
						   <div class="row row-cols-3 row-cols-md-3 mb-3 mt-5 text-center">
						   <div class="container">	
							<div class="card mb-4 rounded-3 shadow-sm">
						       <div class="card-header py-3">
						          <h4 class="my-0 fw-normal">${plan.descripcion}</h4>
						          </div>
						          <div class="card-body">
						            <h1 class="card-title pricing-card-title">${plan.precio} $<small class="text-muted fw-light">/mes</small></h1>
						            <ul class="list-unstyled mt-3 mb-4">
						              <li>Duracion: ${plan.duracion} meses</li>
						              <hr width="50%"/>
						              <li>Cantidad de turnos: ${plan.cantidadTurnos} turnos</li>
						           </ul>
						           <a href="${linkTomarUnPlan }"><input type="button" class="w-100 btn btn-lg btn-outline-success" value="Tomar plan" 
								onClick="if(!(confirm('¿Estas seguro que quieres tomar este plan?'))) return false"/></a>
						        </div>
						      </div>
						    </div>
						    </div>	
				   	</c:forEach>
				   </c:if>
			   	</c:if>
			   	<c:if test = "${contrataciones.valor != null && contrataciones.valor == contrataciones.pago}">	
			   			<div class="row row-cols-3 row-cols-md-3 mb-3 mt-5 text-center">
						   <div class="container">	
							<div class="card mb-4 rounded-3 shadow-sm">
						       <div class="card-header py-3">
						          <h4 class="my-0 fw-normal">${contrataciones.plan.descripcion}</h4>
						          </div>
						          <div class="card-body">
						            <h1 class="card-title pricing-card-title">${duenio.user} se encuentra al dia con respecto al pago<small class="text-muted fw-light"></small></h1>
						            <ul class="list-unstyled mt-3 mb-4">
						              <li>Tomado el dia: ${contrataciones.desde}</li>
						              <hr width="50%"/>
						              <li>Finaliza el dia: ${contrataciones.hasta}</li>
						              <hr width="50%"/>
						              <li>Turnos tomados: ${contrataciones.cantidadTurnosTomados}</li>
						           </ul>
						        </div>
						      </div>
						    </div>
						  </div>			   				   							   		
			   	</c:if>
			   	<c:if test = "${contrataciones.valor != contrataciones.pago}">
				  		<c:url var="linkPagarPlan" value="/pagarPlan">
							 <c:param name="contratacionId" value="${contrataciones.id }"/>	
						</c:url>
						<div class="row row-cols-3 row-cols-md-3 mb-3 mt-5 text-center">
						   <div class="container">	
							<div class="card mb-4 rounded-3 shadow-sm">
						       <div class="card-header py-3">
						          <h4 class="my-0 fw-normal">${plan.descripcion}</h4>
						          </div>
						          <div class="card-body">
						            <h1 class="card-title pricing-card-title">${contrataciones.valor} $<small class="text-muted fw-light">/mes</small></h1>
						            <ul class="list-unstyled mt-3 mb-4">
						              <li>Tomado el dia: ${contrataciones.desde}</li>
						              <hr width="50%"/>
						              <li>Finaliza el dia: ${contrataciones.hasta}</li>
						           </ul>
						           <a href="${linkPagarPlan }"><input type="button" class="w-100 btn btn-lg btn-outline-success" value="Pagar plan" 
								onClick="if(!(confirm('¿Estas seguro que quieres pagar?'))) return false"/></a>
						        </div>
						      </div>
						    </div>
						  </div>
			   	</c:if>
			   	<c:if test = "${contrataciones.valorExtra != contrataciones.pagoExtra}">
				  		<c:url var="linkPagarPlanExtra" value="/pagarPlanExtra">
							 <c:param name="contratacionId" value="${contrataciones.id }"/>	
						</c:url>
			   			<div class="row row-cols-3 row-cols-md-3 mb-3 mt-5 text-center">
						   <div class="container">	
							<div class="card mb-4 rounded-3 shadow-sm">
						       <div class="card-header py-3">
						          <h4 class="my-0 fw-normal">A tomado turnos extra</h4>
						          </div>
						          <div class="card-body">
						            <h1 class="card-title pricing-card-title">${contrataciones.valorExtra - contrataciones.pagoExtra}<small class="text-muted fw-light">$</small></h1>
						           <a href="${linkPagarPlanExtra }"><input type="button" class="w-100 btn btn-lg btn-outline-success" value="Pagar sesion Extra" 
								onClick="if(!(confirm('¿Estas seguro que quieres pagar?'))) return false"/></a>
						        </div>
						      </div>
						    </div>
						  </div>
			   	</c:if>
			   	
	 		</c:when>
	 		<c:when test = "${contrataciones.duenio.id != duenio.id}">
			 	
			   	
	 		</c:when>				  
		  </c:choose>	
	  </c:forEach>
	  <c:forEach var="plan" items="${listaPlanes}">
				<c:url var="linkTomarUnPlan" value="/tomarUnPlan">
					<c:param name="planId" value="${plan.id }"/>	
					<c:param name="duenioId" value="${duenio.id}"/>
				</c:url>
			   <div class="row row-cols-3 row-cols-md-3 mb-3 mt-5 text-center">
			   <div class="container">	
				<div class="card mb-4 rounded-3 shadow-sm">
			       <div class="card-header py-3">
			          <h4 class="my-0 fw-normal">${plan.descripcion}</h4>
			          </div>
			          <div class="card-body">
			            <h1 class="card-title pricing-card-title">${plan.precio} $<small class="text-muted fw-light">/mes</small></h1>
			            <ul class="list-unstyled mt-3 mb-4">
			              <li>Duracion: ${plan.duracion} meses</li>
			              <hr width="50%"/>
			              <li>Cantidad de turnos: ${plan.cantidadTurnos} turnos</li>
			           </ul>
			           <a href="${linkTomarUnPlan }"><input type="button" class="w-100 btn btn-lg btn-outline-success" value="Tomar plan" 
					onClick="if(!(confirm('¿Estas seguro que quieres tomar este plan?'))) return false"/></a>
			        </div>
			      </div>
			    </div>
			    </div>						
		</c:forEach>
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