<%@page import= "java.time.LocalDateTime" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My first JSP file!</title>
</head>
<body>
	<p>Hello, jsp world!</p>
	<p>LocalDateTime.now()</p>
	<%-- expression --%>
	
	<h3><%= LocalDateTime.now()%>
	</h3>
	<%
		for (int i = 0; i <= 10; i++) {
			out.println("iteration " + i + 1);
			out.println("<br></br>");
		}
	%>
</body>
</html>