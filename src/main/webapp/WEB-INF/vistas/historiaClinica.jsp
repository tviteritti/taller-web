<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Historia Clinica</title>
</head>
<body>
   

  <c:if test="${not empty hc}">		  
	<form action="actualizarHistoriaClinica" method="post">
		 <p>Nombre Responsable: <p>${duenio.nombre}</p> </p>
		 <p>Paciente: </p><p>${mascota.nombre}<p/>	</p>
		 <p>Fecha Nacimiento: </p>${mascota.fecha_nacimiento}</p> </p>
		 <br>
			<c:forEach items="${hc}" var="hc">
			     <p>Cod: ${hc.id} </p>
			     <p>Veterinario:  ${hc.veterinario.nombre} ${hc.veterinario.apellido}</p>
				 <p>Fecha: <p> ${hc.fecha}</p></p>
				 <br>
				 <p>Diagnostico: <p>${hc.diagnostico}</p> </p>
				 <br>
				 <p>Tratamiento: <p>${hc.tratamiento}</p> </p>
				 <br><br>
			</c:forEach>
	
		 <input type="hidden" name="duenio" value="${duenio.id}"/>
		 <input type="hidden" name="mascota" value="${mascota.id}"/>
		 <input type="hidden" name="veterinario" value="${veterinario.id}"/>
		 <button type="submit">actualizar</button>
    </form>
    
  </c:if>
   <c:if test="${empty hc}">
      <p>El paciente no se encuentra registrado en ninguna historia clinica</p>
      <br>
      <form action="cargarHistoriaClinica" method="post">
         <input type="hidden" name="duenio" value="${duenio.id}"/>
		 <input type="hidden" name="mascota" value="${mascota.id}"/>
		 <input type="hidden" name="veterinario" value="${veterinario.id}"/>
		 <button>cargar historia clinica</button>
      </form>
   
   </c:if>
   					
</body>
</html>