<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Perfil Mascota</title>
<!-- Bootstrap CSS -->
<link rel="shortcut icon" type="image/png" href="img/logo.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b883f5a3c0.js" crossorigin="anonymous"></script>
</head>
<body>

<h4 class="text-center container p-2">Perfil Mascotas</h4>
    <br>
     <c:forEach var="mascota" items="${mascota}">
    <form action="modificarMascota" method="post" class="container">
   
    	<div class="row  container" style="margin-top:50px;">
    		
    		<div class="col">
    		
    		Nombre:<br>
	    	<input type="text" value="${mascota.nombre}" class="form-control" name ="mascotaNombre">
    		
    		 </div>
    		<div class="col">	
    		 Fecha Nacimiento:
	    	 <input type="date" value="${mascota.fecha_nacimiento}" class="form-control" name ="mascotaFN">
	    	 <input type="hidden" name="mascotaId" value="${mascota.id}"/>
    		</div>
    		
    		<div class="col">	
    		 <label for="exampleFormControlSelect1">Tipo Animal:</label>
	    	   <select name="tipoMascota"  class="form-control" id="exampleFormControlSelect1">
	    	   <option> ${mascota.tipo.descripcion} </option> 
	    	   
	    	   <c:forEach items="${tipos}" var="t">
					  <option value="${t.descripcion}">${t.descripcion}</option>
				   </c:forEach>
				  
		 	</select>
		 
    		</div>
	   
    	</div>
    	
       <br>
       <div class="container">
        
        <input type="hidden" name="duenioId" value="${duenio.id}"/>
        
     	<input type="submit" class="btn btn-success btn-lg" value="Modificar" />
     	
     </div>
     
    </form>
    
    </c:forEach>
    <br>
    <a href="cuentaDuenio" class="btn btn-success btn-lg m-5" name="modificar"><i class="fas fa-caret-left"></i> Volver</a>
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