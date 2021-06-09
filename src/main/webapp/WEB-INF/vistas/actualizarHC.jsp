<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Actualizar Historia Clinica</title>
</head>
<body>
  Agregue los datos necesarios para actualizar la Historia Clinica
  
   <form action="guardarHistoriaClinica" method="post">
      
      <label>Mascota: </label>
      <br>
       Nombre:
       <br>
      <input type="text" value="${mascota.nombre}">
      <input type="hidden" value="${mascota.id}" name="idMascota">
      <br>
       Fecha de Nacimiento:
       <br>
       <input type="text" value="${mascota.fecha_nacimiento}">
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
       Datos de contacto:
       <br>
       Duenio:
       <br>
       Nombre y Apellido :
       <input type="text" value="${duenio.nombre} ${duenio.apellido}">
       <br>
       Telefono de contacto:
       <br>
       <input type="text" value="${duenio.telefono}">
       <br>
       E-mail:
       <br>
       <input type="text" value="${duenio.email}">
       <br>
       Direccion:
       <br>
       <input type="text" value="${duenio.direccion.calle} ${duenio.direccion.numero}">
       <br>
       Localidad:
       <br>
       <input type="text" value="${duenio.direccion.localidad.descripcion}">
       <input type="hidden" value="${duenio.id}" name="idDuenio">
       <br>
      <c:forEach items="${historiaClinica}" var="hc">
      		
       <label>Veterinario: </label>
       <br>
       Nombre:
      <input type="text" name="vNombre" value="${hc.veterinario.nombre}">
       Apellido:
      <input type="text" name="vApellido" value=" ${hc.veterinario.apellido}">
      <br>
      <label>Fecha: </label>
      <input type="date" name="fecha" value="${hc.fecha}">
      <br>
      <label>Diagnostico: </label>
      <br>
      <input type="text" name="diagnostico" value="${hc.diagnostico}">
      <br>
      <label>Tratamiento: </label>
      <br>
      <textarea name="tratamiento" >${hc.tratamiento}</textarea>
      <br>
      </c:forEach>
      <br>
      <label>Veterinario: </label>
        Nombre:
      <input type="text" name="vNombre" value="${veterinario.nombre}">
       Apellido:
      <input type="text" name="vApellido" value="${veterinario.apellido}">
      <input type="hidden" value="${veterinario.id}" name="idVeterinario">
      <br>
      <label>Fecha: </label>
      <input type="date" name="fechaHC">
      <label>Diagnostico: </label>
      <input type="text" name="diagnostico">
      <label>Tratamiento: </label>
      <textarea name="tratamiento" ></textarea>
      <br>
      <button type="submit">guardar</button>
   
   </form>

</body>
</html>