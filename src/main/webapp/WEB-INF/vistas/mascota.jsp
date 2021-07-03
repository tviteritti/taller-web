<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mi mascota</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>
</head>
<body>


	<%@ include file="menuCuentaDuenio.jsp"%>
	
	</div>
	
 <h4 class="text-center container p-4">Mi mascota</h4>
 <div class="d-flex justify-content-center flex-column">
	 
			 <div class="row">
			  <div class="col">
			    <div class="card">
			      <div class="card-body">
			        <h5 class="card-title">Cargar Mascota</h5>
			        <p class="card-text">Ingresa los datos de tu mascota y dalo de alta</p>
			        
			        <form action="cargarMascota" method="post">
				   <input type="hidden" name="duenioId" value="${duenio.id}"/>
				    <button type="submit" class="btn btn-success">cargar mascota</button>
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
			        <h5 class="card-title">Ver perfil de mi mascota</h5>
			        <p class="card-text">Podes ver el perfil de tu mascota y modificarlo</p>
			        
			        <form action="perfilMascota" method="post">
				   <input type="hidden" name="duenioId" value="${duenio.id}"/>
				    <button type="submit" class="btn btn-success">ver perfil</button>
			  	 </form>
			  	 
			      </div>
			    </div>
			  </div>
			  </div>

	   </div>
 
 <input type="hidden" name="duenioId" value="${duenio.id}"/>
 
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