<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis notificaciones</title>
</head>
<body>
   <c:forEach items="${consultas}" var="c">
    <c:if test="${notificacion.consulta.id eq c.id}">
        <p> ${c.asunto} </p>
        <p> ${c.descripcion} </p>
        <p> ${c.respuesta.descripcion} </p>
    </c:if>
    </c:forEach>
    
</body>
</html>