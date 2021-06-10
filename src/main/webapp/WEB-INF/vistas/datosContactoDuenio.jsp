<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  
  Datos de contacto:
  <br>
  <p>Nombre y Apellido: ${nombre} ${apellido}</p>
  <p>Telefono: ${telefono}</p>
  <p>E-mail: ${email}</p>
  <p>Direccion: ${direccion.calle} ${direccion.numero}</p>
  <p>Localidad: ${direccion.localidad.descripcion} </p>

</body>
</html>