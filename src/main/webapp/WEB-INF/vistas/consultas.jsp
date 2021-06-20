<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <br><br>
   <h4>Consultas</h4>
   <c:param name="duenioId" value="${duenio.id}"/>
   <a href="consultar">realizar consulta</a>
</body>
</html>