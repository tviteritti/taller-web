<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mi consulta</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>     <div class="p-3"></div>
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
						     <input type="hidden" name="duenioId" value="${duenio.id}"/>
						     <button type="submit" class="btn btn-success btn-block">Enviar</button>
					     </div>
			     </div>
			    </form>
    
			  </div>
			</div>
			
			<a href="consultas" class="btn btn-success btn-lg">volver</a>
			<br>
			
			<hr class="my-4">
			
			<h4 class="text-center container p-4">Otras consultas que te pueden interesar</h4>
			
		<c:if test="${not empty todasLasConsultas}">
			
			<c:forEach items="${todasLasConsultas}" var="tc">
      	
		      	<div class="card text-center p-3 container">
					    <blockquote class="blockquote mb-0 p-3">
					     <h5 class="card-title">${tc.asunto}</h5>
					      <p>${tc.descripcion}</p>
					      <footer class="blockquote-footer ">
					        <small class="text-dark">
					          Realizado por <cite title="Source Title">${tc.duenio.user}</cite>
					        </small>
					      </footer>
					    </blockquote>
					    <c:if test="${not empty tc.comentario}">
				    		<div class="border-left-info">
				    		  <p>${tc.comentario}</p>
				    		</div>
		    			</c:if>
					    <form method="post" action="miConsulta">
							<textarea class="form-control" rows="3" placeholder="comentar" name="comentario"></textarea>
						    <button type="button" class="btn btn-primary btn-sm">Responder</button>
					   </form>
		  		</div>
		  		<br>
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