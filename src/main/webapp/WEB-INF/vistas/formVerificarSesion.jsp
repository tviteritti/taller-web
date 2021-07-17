<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Iniciar sesion</title>
 <link rel="shortcut icon" type="image/png" href="img/logo.png">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

	<div class="container">
        <div class="row vh-100 justify-content-center align-items-center">
            <div class="col-auto bg-light p-5">
                <form action="procesarDatosSesion" method="POST" >
                	<div class="input-group p-2">
                        <h3>Iniciar sesion</h3>
                    </div><br>
                    <div class="input-group p-2">
                        <input type="hidden" name="id"/>
                    </div><br>
                   <div class="input-group p-2">
                        <input type="hidden" name="id_turno" value="${id_turno }"/>
                    </div><br>
                	<div class="input-group p-2">               
                        <input type="text" class="form-control" name="user" placeholder="usuario"/>
                    </div>
                    <div class="input-group p-2">
                        <input type="password" class="form-control" name="password" placeholder="contrase�a"/>
                    </div><br>  
                    <a href="registrarUsuario">�no tines cuenta? Registrarse</a>                 
                    <br>
                    <br>
                    <input type="submit" class="btn btn-success w-100" value="registrar">
            		<h4><span>${error}</span></h4>
     			</form>             
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