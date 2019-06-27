<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="addreservation.do">
		Start date <input type="datetime-local" name="start"> <br> End date
		<input type="datetime-local" name="end"> <br> Client id <input
			type="number" name="clientId"> <br> Court id <input
			type="number" name="courtId"> <br> <input type="submit"
			value="Crea nuova reservation">
	</form>
</body>
</html>