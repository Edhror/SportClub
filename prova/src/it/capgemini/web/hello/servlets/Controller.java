package it.capgemini.web.hello.servlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.capgemini.SportsClub.dao.abstractions.ReservationDAO;
import it.capgemini.SportsClub.dao.implementations.FileReservationDAO;
import it.capgemini.SportsClub.model.Client;
import it.capgemini.SportsClub.model.Court;
import it.capgemini.SportsClub.model.Reservation;
import it.capgemini.SportsClub.model.FileDataSource;
import it.capgemini.SportsClub.model.TennisCourt;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationDAO dao = new FileReservationDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] tokens = request.getRequestURI().split("[/.]");
		String action = tokens[tokens.length - 2];
		switch (action.toLowerCase()) {
		case "listreservations":
			listReservations(request, response);
			break;
		case "addreservation":
			addReservations(request, response);
			break;
		case "showreservationform":
			showReservationForm(request, response);
			break;
		default:
			showHomePage(request, response);
		}
	}

	private void showHomePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher disp = request.getRequestDispatcher("jsp/Homepage.jsp");
		disp.forward(request, response);

	}

	private void addReservations(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String startString = request.getParameter("start");
		String endString = request.getParameter("end");
		String clientString = request.getParameter("clientId");
		String courtString = request.getParameter("courtId");
		LocalDateTime start = LocalDateTime.parse(startString);
		LocalDateTime end = LocalDateTime.parse(endString);
		int clientId = Integer.parseInt(clientString);
		int courtId = Integer.parseInt(courtString);
		Client client = FileDataSource.uniqueInstance().findClientById(clientId);
		Court court = FileDataSource.uniqueInstance().findCourtById(courtId);

		Reservation res = new Reservation(0, start, end, client, court, 10.0);
//		InputStreamReader isr = new InputStreamReader(
//				getServletContext().getResourceAsStream("/WEB-INF/reservations.txt"));

		dao.add(res);
		response.sendRedirect("listreservations.do");
	}

	private void showReservationForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("jsp/AddReservationForm.jsp");
		disp.forward(request, response);
	}

	private void listReservations(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Iterable<Reservation> res = dao.allReservations();
		request.setAttribute("reservations", res);
		RequestDispatcher disp = request.getRequestDispatcher("jsp/ListReservations.jsp");
		disp.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
