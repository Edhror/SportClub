package it.capgemini.SportsClub.dao.abstractions;

import it.capgemini.SportsClub.model.Reservation;

public interface ReservationDAO {
    Iterable<Reservation> allReservations();
    Reservation add(Reservation newReservation);
    Reservation update(Reservation newReservation);
    Reservation delete (int idReservation);
}
