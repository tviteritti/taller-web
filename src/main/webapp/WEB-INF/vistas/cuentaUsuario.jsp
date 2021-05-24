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
   <a href="verTurnos">ver mi perfil</a>
   <br><br>
  <h4>Hola ${usuario} esta es tu cuenta</h4>
  <h4>Turno</h4>
   <a href="verTurnos">mis turnos</a>
   <a href="buscarTurno">solicitar turno</a>
   <a href="cancelarTurno">cancelar turno</a>
   <br><br>
   <h4>Consultas</h4>
   <a href="consultar">realizar consulta</a>
   <br><br>
  <a href="loginVeterinaria">cerrar sesion</a>
  
 
</body>
</html>