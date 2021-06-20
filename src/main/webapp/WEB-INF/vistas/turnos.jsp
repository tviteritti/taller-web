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
<title>Turno</title>
</head>
<body>

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