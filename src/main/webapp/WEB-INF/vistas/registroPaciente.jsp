<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
 <!-- <h1>VETERIANARIA</h1>
  <form:form action="procesarDatosPaciente" method="post" modelAttribute="usuario">
     <form:input type="text" name="usuario" placeholder="usuario" path="user"/><br>
     <form:input type="text" name="email" placeholder="email" path="email"/><br>
     <form:input type="text" name="password" placeholder="contraseña" path="password"/><br>
     <input type="text" name="re-password" placeholder="re ingresar contraseña"><br>
     <button type="submit">REGISTRARSE</button>
  </form:form> -->
  
  <div class="container">
        <div class="row vh-100 justify-content-center align-items-center">
            <div class="col-auto bg-light p-5">
                <form:form action="procesarDatosPaciente" method="POST" modelAttribute="usuario">
                	<div class="input-group p-2">
                        <h3>Registrar Paciente</h3>
                    </div>
                    <div class="input-group p-2">
                        <form:hidden path="id"/>
                    </div>
                    <div class="input-group p-2">
                        <form:hidden path="rol" value="paciente"/>
                    </div>
                    <div class="input-group p-2">
                        <form:input class="form-control" placeholder="email" path="email"/>
                    </div>
                    <div class="input-group p-2">
                        <form:input class="form-control" placeholder="user" path="user"/>
                    </div>
                    <div class="input-group p-2">
                        <form:input class="form-control" placeholder="password" path="password"/>
                    </div>
                     <div class="input-group p-2">
                        <input type="text" class="form-control" name="re-password" placeholder="re ingresar contraseña">
                    </div>
					<br><br>
					<input type="submit" class="btn btn-success w-100" value="registrar">
                </form:form>
            </div>
        </div>
    </div>
  <h4><span>${error}</span></h4>
  
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