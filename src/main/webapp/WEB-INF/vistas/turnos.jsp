<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>
<title>Turno</title>
</head>
<body>

<div class="card text-center">
	<div class="row card-header">
	    	<div class="col-3"> <a href="loginVeterinaria"><i class="fas fa-home display-6 text-body"></i></a></div>
	    	<div class="col-2">  <i class="fas fa-bell display-6"> </i>
	    	
	    	  <div class="dropdown">
								  <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    1
								  </a>
								
								  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								  
								  		<c:forEach items="${notificacion}" var="n">
								    	<c:if test="${not empty n.duenio.id }">
								    	  <c:if test="${n.duenio.id eq usuario.id}">
								  
									    <a class="dropdown-item" href="#"> ${n.mensaje} </a>
									    <script>
								          document.getElementsByClassName("fas fa-bell")[0].classList.add('text-danger');
								        </script>
									    </c:if>	
								    	</c:if>
							    	</c:forEach>
								    
								  </div>
							</div>
	    	
	    	</div>
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
	

 <h4 class="text-center container p-4">Turno</h4>
 
	 <div class="d-flex justify-content-center flex-column">
	 
			 <div class="row">
			  <div class="col">
			    <div class="card">
			      <div class="card-body">
			        <h5 class="card-title">Listado de turnos</h5>
			        <p class="card-text">Aca podra ver el listado de turnos que ha solicitado</p>
			        
			        <form action="misTurnos" method="post">
				   <input type="hidden" name="duenioId" value="${duenio.id}"/>
				    <button type="submit" class="btn btn-success">mis turnos</button>
			  	 </form>
			  	 
			      </div>
			    </div>
			  </div>
			  </div>
			  
			   <div></div>
			  
			   
			    <div class="row">
			  <div class="col">
			    <div class="card">
			      <div class="card-body">
			        <h5 class="card-title">Solicite un turno</h5>
			        <p class="card-text">Si esta buscando un turno, debe ingresar aca</p>
			        
			       <form action="buscarTurno" method="post">
			   <input type="hidden" name="duenioId" value="${duenio.id}"/>
			    <button type="submit" class="btn btn-success">solicitar turno</button>
			   </form>
			  	 
			      </div>
			    </div>
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