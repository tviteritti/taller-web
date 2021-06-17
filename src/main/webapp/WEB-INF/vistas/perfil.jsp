<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
    <h4>Mi perfil</h4>
    <br>
    <form action="modificarPerfil" method="post">
     nombre:<br>
     <input type="text" value="${duenio.nombre}" name ="nombre">
     <br>
     apellido:<br>
     <input type="text" value="${duenio.apellido}" name ="apellido">
     <br>
     email:<br>
     <input type="text" value="${duenio.email}" name ="email">
     <br>
     telefono:<br>
     <input type="text" value="${duenio.telefono}" name ="telefono">
     <br>
      Direccion:
      <br>
      calle:
     <input type="text" value="${duenio.direccion.calle}" name ="calle">
     <br>
      nro:
     <input type="text" value="${duenio.direccion.numero}" name ="numero">
     <br>
      Localidad:
     <input type="text" value="${duenio.direccion.localidad.descripcion}" name ="localidad">
     <br>
     <button type="submit">modificar</button>
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