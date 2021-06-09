<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
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
      <br>
       Nombre:
       <br>
      <input type="text" name="nombreMascota" value="${mascota.nombre}">
      <br>
       Fecha de Nacimiento:
       <br>
       <input type="date" name="fechaNacimiento">
       <br>
       Tipo de Animal:
       <br>
       <select name="tipoMascota">
		 <option> ${mascota.tipo.descripcion} </option> 
			<c:forEach items="${tipos}" var="t">
			  <option value="${t.descripcion}">${t.descripcion}</option>
		   </c:forEach>
	 	</select>
	 	<br>
	   <input type="hidden" value="${mascota.id}" name="idMascota">
       <br>
       Datos de contacto:
       <br>
       Duenio:
       <br>
       Nombre:
       <br>
       <input type="text" name="dNombre" value="${duenio.nombre}">
       <br>
       Apellido :
       <br>
       <input type="text" name="dApellido" value="${duenio.apellido}">
       <br>
       Telefono de contacto:
       <br>
       <input type="text" name="telefonoDuenio" value="${duenio.telefono}">
       <br>
       E-mail:
       <br>
       <input type="text" name="emailDuenio" value="${duenio.email}">
       <br>
       Direccion:
       <br>
       <input type="text" name="direccionDuenio" value="${duenio.direccion.calle} ${duenio.direccion.numero}">
       <br>
       Localidad:
       <br>
       <input type="text" name="localidadDuenio">
       <input type="hidden" value="${duenio.id}" name="idDuenio" value="${duenio.direccion.localidad.descripcion}">
       <br>
      <label>Veterinario: </label>
       <br>
       Nombre:
       <br>
      <input type="text" name="vNombre" value="${veterinario.nombre}">
      <br>
       Apellido:
       <br>
      <input type="text" name="vApellido" value="${veterinario.apellido}">
      <input type="hidden" value="${veterinario.id}" name="idVeterinario">
      <br>
      <label>Fecha: </label>
      <br>
      <input type="date" name="fechaHC">
      <br>
      <label>Diagnostico: </label>
      <br>
      <input type="text" name="diagnostico">
      <br>
      <label>Tratamiento: </label>
      <br>
      <textarea name="tratamiento" ></textarea>
      <br>
      <button type="submit">guardar</button>
   
   </form>

</body>
</html>