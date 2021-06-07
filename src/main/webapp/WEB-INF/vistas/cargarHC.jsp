<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>cargar HC</title>
</head>
<body>
   <h3>Complete los campos para cargar la historia clinica</h3>
   <br>
   <form action="guardarHistoriaClinica" method="post">
      
      <label>Mascota: </label>
      <input type="text" value="${mascota.nombre}">
      <input type="hidden" value="${mascota.id}" name="idMascota">
      <input type="hidden" value="${duenio.id}" name="idVeterinario">
      <label>Veterinario: </label>
      <input type="text" value="${veterinario.nombre}"> 
      <input type="text" value="${veterinario.apellido}">
      <input type="hidden" value="${veterinario.id}" name="idVeterinario">
      <label>Fecha: </label>
      <input type="date" name="fecha">
      <label>Diagnostico: </label>
      <input type="text" name="diagnostico">
      <label>Tratamiento: </label>
      <textarea name="tratamiento" ></textarea>
      
      <button type="submit">guardar</button>
   
   </form>

</body>
</html>