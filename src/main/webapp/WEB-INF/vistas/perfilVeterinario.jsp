<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Perfil veterinario</title>
    <!-- Required meta tags -->
    <link rel="shortcut icon" type="image/png" href="img/logo.png">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

    <form action="volverCardVeterinario" method="post" class="container">
    	<div class="row  container">
    	
    		<div class="col">
    		
	    		Nombre:<br>
		    	<input type="text" value="${veterinario.nombre}" class="form-control" name ="nombre">
		    	
    			Apellido:<br>
	     	  <input type="text" value="${veterinario.apellido}" class="form-control" name ="apellido">
    		 </div>
    		 
    		 <div class="col">
    			Email:<br>
	     		<input type="text" value="${veterinario.email}" class="form-control" name ="email">
    		
    			Telefono:<br>
	     		<input type="text" value="${veterinario.telefono}" class="form-control" name ="telefono">
    		 </div>
    		 
    		 <div class="col">
		      Calle:
		     <input type="text" value="${veterinario.direccion.calle}" class="form-control" name ="calle">
		      Nro:
		     <input type="text" value="${veterinario.direccion.numero}" class="form-control" name ="numero">
		     <input type="hidden" value="${veterinario.direccion.id}" name="idDireccion"/>
    		</div>
    		
	   
    	</div>
       <br>
       <div class="container">
       <input type="hidden" value="${id_zona}" name="id_zona"/>
       <input type="hidden" value="${id_especialidad}" name="id_especialidad"/>
       <input type="hidden" value="${duenioId}" name="duenioId"/>
       <!--<button type="submit" class="btn btn-success btn-lg">Modificar</button>-->
     	<input type="submit" class="btn btn-success btn-lg" value="Volver"></input>
     </div>
    </form>

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