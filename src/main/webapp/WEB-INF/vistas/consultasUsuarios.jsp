<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consultas de usuarios</title>
 <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>
</head>
<body>

	<%@ include file="menuCuentaVeterinario.jsp"%>
	</div>
<h4 class="text-center container p-3">Responde las dudas de nuestros usuarios</h4>
   <c:forEach items="${consultas}" var="c">
	   <c:if test="${not empty c.asunto}">
	   <c:if test="${not empty c.descripcion}">
		<div class="card p-3">
		  <div class="card-header">
		    ${c.asunto}
		  </div>
		  <div class="card-body container">
		    <blockquote class="blockquote mb-0">
		      <p class="display-5">${c.descripcion}</p>
		      <footer class="blockquote-footer">Realizada por <cite title="Source Title">${c.usuario.user} </cite></footer>
		    </blockquote>
		  </div>
		    <div class="card-footer text-muted">
		    	<c:if test="${not empty c.comentario}">
		    	
		    		  <form method="post" action="responderConsultas" class="d-flex flex-column m-3" style="border-left:3px solid #17a2b8">
								<p class="text-left ml-2">${c.respuesta.descripcion} - <cite title="Source Title"><strong>${c.userRespuesta}</strong></cite></p>
								<input type="hidden" value="${c.usuario.id}" name="usuarioRespuesta">
								<textarea name="comentario" placeholder="comentar" class="mr-3"></textarea>
								<input type="hidden" value="${c.respuesta.id}" name="idRespuesta">
								<button type="submit" class="btn btn-link text-decoration-none" id="responder">Responder</button>
						</form>
			
		    	</c:if>
		    	 <form method="post" action="responderConsultas">
					        <input type="hidden" value="${c.id}" name="idConsulta">
							<textarea class="form-control" rows="3" placeholder="comentar" name="comentario"></textarea>
							<input type="hidden" value="true" name="notificacion">
						    <button type="submit" class="btn btn-primary btn-sm">Responder</button>
				</form>
				
  			</div>
		</div>
		</c:if>
		</c:if>
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