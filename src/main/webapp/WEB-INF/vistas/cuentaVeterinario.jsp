<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hola!</title>
</head>
<body>

<h4>Mi perfil</h4>
   <a href="verPefil">ver mi perfil</a>
   <br><br>
  <h4>Hola ${usuario.user} esta es tu cuenta</h4>
  <h4>Mi agenda</h4>
  
  <form action="verTurnosPacientes" method="post">
    <input type="hidden" name="veterinarioId" value="${usuario.id}"/>
   	<button type="submit">turnos</button>
   </form>
 
  <br>
   <form action="verPacientes" method="post">
    <input type="hidden" name="veterinarioId" value="${usuario.id}"/>
   	<button type="submit">mis pacientes</button>
   </form>
   
   <br><br>
  <a href="loginVeterinaria">cerrar sesion</a>
  

</body>
</html>