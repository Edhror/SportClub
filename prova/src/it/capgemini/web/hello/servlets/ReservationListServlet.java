package it.capgemini.web.hello.servlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.capgemini.SportsClub.dao.abstractions.ReservationDAO;
import it.capgemini.SportsClub.dao.implementations.FileReservationDAO;
import it.capgemini.SportsClub.model.Reservation;

@WebServlet("/ReservationListServlet")
public class ReservationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html"); // mimetype stringa che indica il tipo di documento
		PrintWriter pw = response.getWriter();
		
		ReservationDAO res = new FileReservationDAO();
		pw.println("<html>");
		pw.println("<body>");
		pw.println("<table>");
		pw.println("<tr>");
		pw.println("<th>ID</th>");
		pw.println("<th>Start</th>");
		pw.println("<th>End</th>");
		pw.println("<th>Client</th>");
		pw.println("<th>Court</th>");
		pw.println("<th>Cost</th>");
		pw.println("</tr>");
		for (Reservation r : res.allReservations()) {
			pw.println("<tr>");
			pw.println("<td>" + r.getId() + "</td>");
			pw.println("<td>" + r.getStart() + "</td>");
			pw.println("<td>" + r.getEnd() + "</td>");
			pw.println("<td>" + r.getClient().getName() + "</td>");
			pw.println("<td>" + r.getCourt().getName() + "</td>");
			pw.println("<td>" + r.getCost() + "</td>");
			pw.println("</tr>");
		}
		pw.println("</table>");
		pw.println("</body>");
		pw.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
