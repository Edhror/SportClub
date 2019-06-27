<%@page import="it.capgemini.SportsClub.model.Reservation"%>
<%@page
	import="it.capgemini.SportsClub.dao.implementations.FileReservationDAO"%>
<%@page import="it.capgemini.SportsClub.dao.abstractions.ReservationDAO"%>
<%@page import="java.io.InputStreamReader"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista delle prenotazioni</title>
</head>
<body>
	<%
		ReservationDAO res = new FileReservationDAO();
		Iterable<Reservation> reservations = res.allReservations();
	%>
	<table>
		<tr>
			<th>ID</th>
			<th>Start</th>
			<th>End</th>
			<th>Client</th>
			<th>Court</th>
			<th>Cost</th>
		</tr>
		<%
			for (Reservation r : reservations) {
				//out.println("<tr>"); stessa cosa della riga 35
		%>
		<tr>
			<td><%=r.getId()%></td>
			<td><%=r.getStart()%></td>
			<td><%=r.getEnd()%></td>
			<td><%=r.getClient().getName()%></td>
			<td><%=r.getCourt().getName()%></td>
			<td><%=r.getCost()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>