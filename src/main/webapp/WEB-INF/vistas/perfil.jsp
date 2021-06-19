<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Mi perfil</title>
</head>
<body>
    <h4 class="text-center container p-2">Mi perfil</h4>
    <br>
    
    <form action="modificarPerfil" method="post" class="container">
    	<div class="row  container">
    	
    		<div class="col">
    		
    		Nombre:<br>
	    	<input type="text" value="${duenio.nombre}" class="form-control" name ="nombre">
    		
    		
    			Apellido:<br>
	     	  <input type="text" value="${duenio.apellido}" class="form-control" name ="apellido">
    		 </div>
    		 <div class="col">
    			Email:<br>
	     		<input type="text" value="${duenio.email}" class="form-control" name ="email">
    		
    			Telefono:<br>
	     		<input type="text" value="${duenio.telefono}" class="form-control" name ="telefono">
    		 </div>
    		 <div class="col">
		      Calle:
		     <input type="text" value="${duenio.direccion.calle}" class="form-control" name ="calle">
		      Nro:
		     <input type="text" value="${duenio.direccion.numero}" class="form-control" name ="numero">
    		</div>
    		<div class="col">	
    		 Localidad:
	    	 <input type="text" value="${duenio.direccion.localidad.descripcion}" class="form-control" name ="localidad">
    		</div>
	   
    	</div>
       <br>
       <div class="container">
     <button type="submit" class="btn btn-success btn-lg">MODIFICAR</button>
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