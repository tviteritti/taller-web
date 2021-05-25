<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<c:forEach items="${turnos}" var="t">
			  
					<div>
					   <p>Servicio: </p>
					   <c:out value="" />	
					   <button type="submit">cancelar</button>
					</div>	     
						  
			  					
			 </c:forEach>
</body>
</html>