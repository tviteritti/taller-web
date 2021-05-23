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
	
	<div class="container mt-3">
    	<div class="row">
    		<div class="col-12">
				<table class="table table-striped ">
	  				<thead class="thead-inverse">
	  					<tr>
					  		<th>id</th>
					  		<th>email</th>
					  		<th>password</th>
					  		<th>rol</th>
					  		<th>user</th>
					  		<th>modificar</th>
	  					</tr>
					</thead>
					
				  	<c:forEach var="usuario" items="${listaUsuarios}">
				  	
				  		<c:url var="linkActualizar" value="/formActualizar">
				  		
				  			<c:param name="usuarioId" value="${usuario.id }"/>
				  		
				  		</c:url>
				  		
					  	<tr>
					  		<td>${usuario.id}</td>
					  		<td>${usuario.email}</td>
					  		<td>${usuario.password}</td>
					  		<td>${usuario.rol}</td>
					  		<td>${usuario.user}</td>
					  		<td><a href="${linkActualizar }"><input type="button" value="modificar"/></a></td>
					  	</tr>
				  	
				  	</c:forEach>
	  	
	  			</table>
			</div>
		</div>
	</div>
	
	<div class="container mt-3"><h3><a class="btn btn-success w-100" href="formRegistrar">ingresar usuario</a></h3></div>
	
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