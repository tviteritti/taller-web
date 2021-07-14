<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mi consulta</title>
<!-- Bootstrap CSS -->
<link rel="shortcut icon" type="image/png" href="img/logo.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>
</head>
<body>

 <div class="card text-center">
	<div class="row card-header">
	    	<div class="col-3"> <a href="loginVeterinaria"><i class="fas fa-home display-6 text-body"></i></a></div>
	    	<div class="col-2 d-flex flex-row">  <i class="fas fa-bell display-6 mr-3"> </i>
	    	
	    	<div class="dropdown" style="line-height:16px">
								  <a class="btn btn-secondary dropdown-toggle p-0" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="height:20px;width:50px">
								    <p style="font-size:14px">  ${cantidadNotificaciones} </p>
								  </a>
								
								  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								  
								  		<c:forEach items="${notificacion}" var="n">
								    	<c:if test="${not empty n.usuario.id }">
								    	  <c:if test="${n.usuario.id eq usuario.id}">
								  
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



     <div class="p-3"></div>
			<div class="card border-dark mb-3 container" style="max-width: 50rem;">
			  <div class="card-header">
			  		<h5 class="card-title text-center">  Mi consulta</h5>
			  </div>
			  <div class="card-body text-dark">
			    
				   <form action="miConsulta" method="post" class="container">
			    	<div class="col  container">
			    	
				    		<div class="row p-3">
					    		<input type="text"  class="form-control" name ="asunto" placeholder="Asunto">
				    		 </div>
				    		 
				    		 <div class="row">
					    		<textarea type="text"  class="form-control" name ="consulta" placeholder="Escriba su consulta"></textarea>
				    		 </div>
				    	
					       <br>
					       <div class="container">
						     <input type="hidden" name="duenioId" value="${usuario.id}"/>
						     <button type="submit" class="btn btn-success btn-block">Enviar</button>
					     </div>
			     </div>
			    </form>
    
			  </div>
			</div>
			
			<a href="consultas" class="btn btn-success btn-lg"><i class="fas fa-caret-left"></i> volver</a>
			<br>
			
			<hr class="my-4">
			
			<h4 class="text-center container p-4">Otras consultas que te pueden interesar</h4>
			
			
		<c:if test="${not empty todasLasConsultas}">
			
			<c:forEach items="${todasLasConsultas}" var="tc">
			
			<c:if test="${not empty tc.asunto}">
      		<c:if test="${not empty tc.descripcion}">
      		
		      	<div class="card text-center p-3 container">
					    <blockquote class="blockquote mb-0 p-3">
					     <h5 class="card-title">${tc.asunto}</h5>
					      <p class="display-5">${tc.descripcion}</p>
					      <footer class="blockquote-footer ">
					        <small class="text-dark">
					          Realizado por <cite title="Source Title">${tc.usuario.user}</cite>
					        </small>
					      </footer>
					    </blockquote>
					    <c:if test="${not empty tc.comentario}">
					  
						  <form method="post" action="miConsulta" class="d-flex flex-column m-3" style="border-left:3px solid #17a2b8">
								<p class="text-left ml-2">${tc.respuesta.descripcion} - <cite title="Source Title"><strong>${tc.userRespuesta}</strong></cite></p>
								<input type="hidden" value="${tc.usuario.id}" name="usuarioRespuesta">
								<textarea name="comentario" placeholder="comentar" class="mr-3"></textarea>
								<input type="hidden" value="${tc.respuesta.id}" name="idRespuesta">
								<button type="submit" class="btn btn-link text-decoration-none" id="responder">Responder</button>
						</form>
	
		    			</c:if>
					    <form method="post" action="miConsulta">
					        <input type="hidden" value="${tc.id}" name="idConsulta">
							<textarea class="form-control" rows="3" placeholder="comentar" name="comentario"></textarea>
							<input type="hidden" value="true" name="notificacion">
						    <button type="submit" class="btn btn-primary btn-sm">Responder</button>
					   </form>
		  		</div>
		  		<br>
		  		
		  		</c:if>
		  		</c:if>
       		</c:forEach>
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