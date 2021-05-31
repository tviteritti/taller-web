<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

	<jsp:include page="navSinLog.jsp" flush="true" />
	
    <!-- <h1>Veterinaria</h1>
    <a href="iniciarSesion">INCIAR SESION</a>
    <a href="registrarUsuario">REGISTRARME</a> -->


	<div class="container mt-3">
    	<div class="row">
    		<div class="col-12">
				<table class="table table-striped ">
	  				<thead class="thead-inverse">
	  					<tr>
					  		<th>veterinario</th>
					  		<th>fecha</th>
					  		<th>horario</th>
					  		<th>servicio</th>
					  		<th>solicitar turno</th>
	  					</tr>
					</thead>
					
				  	<c:forEach var="turno" items="${listaTurnos}">
				  		
				  		<c:url var="linkTomarUnTurno" value="/tomarUnTurno">
				  		
				  			<c:param name="turnoId" value="${turno.id }"/>
				  		
				  		</c:url>
				  		
					  	<tr>
					  		<td>${turno.veterinario}</td>
					  		<td>${turno.fecha}</td>
					  		<td>${turno.horario}</td>
					  		<td>${turno.servicio}</td>
					  		<td><a href="${linkTomarUnTurno }"><input type="button" class='btn btn-success ms-auto' value="tomar turno" 
					  		onClick="if(!(confirm('¿Estas seguro que quieres tomar este turno?'))) return false"/></a></td>
					  	</tr>
				  	
				  	</c:forEach>
	  	
	  			</table>
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