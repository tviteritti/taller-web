<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis Consultas</title>
<link rel="shortcut icon" type="image/png" href="img/logo.png">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>

</head>
<body>
		
		<%@ include file="menuCuentaDuenio.jsp"%>
		</div>
		
	  <h4 class="text-center container p-2">Mis consultas</h4>
      <br>
      
      <c:forEach items="${consultas}" var="c">
      	<c:if test="${not empty c.asunto}">
      	<c:if test="${not empty c.descripcion}">
      	<div class="card text-center p-3 container">
			    <blockquote class="blockquote mb-0">
			     <h5 class="card-title">${c.asunto}</h5>
			      <p>${c.descripcion}</p>
			      <footer class="blockquote-footer ">
			        <small class="text-dark">
			          Realizado por <cite title="Source Title">${c.usuario.user}</cite>
			        </small>
			      </footer>
			    </blockquote>      
  		</div>
  		</c:if>
  		</c:if>
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