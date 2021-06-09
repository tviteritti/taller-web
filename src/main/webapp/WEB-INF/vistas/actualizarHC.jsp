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
       Nombre:
      <input type="text" value="${mascota.nombre}">
      <input type="hidden" value="${mascota.id}" name="idMascota">
       Fecha de Nacimiento:
       <input type="text" value="${mascota.fecha_nacimiento}">
       Tipo de Animal:
       <select>
        <option></option>
       </select>
       
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
       Nombre y Apellido :
       <input type="text" value="${duenio.nombre} ${duenio.apellido}">
       Telefono de contacto:
       <input type="text" value="${duenio.telefono}">
       E-mail:
       <input type="text" value="${duenio.email}">
       Direccion:
       <input type="text" value="${duenio.direccion.calle} ${duenio.direccion.numero}">
       Localidad:
       <input type="text" value="${duenio.direccion.localidad.descripcion}">
       <input type="hidden" value="${duenio.id}" name="idDuenio">
       <br>
      <c:forEach items="${historiaClinica}" var="hc">
      		
       <label>Veterinario: </label>
       Nombre:
      <input type="text" name="vNombre" value="${hc.veterinario.nombre}">
       Apellido:
      <input type="text" name="vApellido" value=" ${hc.veterinario.apellido}">
      <label>Fecha: </label>
      <input type="date" name="fecha" value="${hc.fecha}">
      <label>Diagnostico: </label>
      <input type="text" name="diagnostico" value="${hc.diagnostico}">
      <label>Tratamiento: </label>
      <textarea name="tratamiento" >${hc.tratamiento}</textarea>
      
      </c:forEach>
      <br>
      <label>Veterinario: </label>
        Nombre:
      <input type="text" name="vNombre">
       Apellido:
      <input type="text" name="vApellido">
      <input type="hidden" value="${veterinario.id}" name="idVeterinario">
      <br>
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