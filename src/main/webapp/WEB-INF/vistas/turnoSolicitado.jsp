<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Turno solicitado con exito!</h2>

<div>
  <p>Veterinario: ${veterinario}</p>
  <p>Servicio solicitado: ${especialidad}</p>
  <p>Ubicado en ${localidad} , ${direccion}</p>
  <p>Para el dia ${fecha}, a las ${hora} horas.</p>
</div>
<a href="volverACuenta">volver a mi cuenta</a>

</body>
</html>