<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Turnos</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>

</head>
<body>

		<%@ include file="menuCuentaVeterinario.jsp"%>
		</div>
	    <h4 class="text-center container mt-4">Listado de Turnos</h4>
		<div class="container">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">Fecha</th>
			      <th scope="col">Hora</th>
			      <th scope="col">Servicio</th>
			      <th scope="col">Solicitado por</th>
			      <th scope="col">Contactar</th>
			     
			    </tr>
			  </thead>
			  <tbody>
			 <c:forEach items="${turnos}" var="t">
			  <tr>
					<form action="contactarPaciente" method="post">
					   <td><c:out value="${t.fecha}" />	</td>
					   <td><c:out value="${t.horario}" /></td>
					   <td><c:out value="${t.servicio}" /></td>
					   <td><c:out value="${t.duenio.nombre} ${t.duenio.apellido}" /></td>
					   <br>
					   <td>
						   <input type="hidden" name="duenio" value="${t.duenio.id}"/>
						   <button type="submit" class="btn btn-success">contactar</button>
					   </td> 
					  </tr>
					</form>	     
						  
			  					
			 </c:forEach> 
			 
			 </tbody>
		</table>	
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