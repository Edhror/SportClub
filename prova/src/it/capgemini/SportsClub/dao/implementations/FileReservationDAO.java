package it.capgemini.SportsClub.dao.implementations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.capgemini.SportsClub.dao.abstractions.ReservationDAO;
import it.capgemini.SportsClub.model.Client;
import it.capgemini.SportsClub.model.Court;
import it.capgemini.SportsClub.model.Reservation;
import it.capgemini.SportsClub.model.SportClubException;
import it.capgemini.SportsClub.model.FileDataSource;

public class FileReservationDAO implements ReservationDAO {

	// private String fileName;

	@Override
	public Iterable<Reservation> allReservations() {
		return FileDataSource.uniqueInstance().getReservations();
	}

	@Override
	public Reservation add(Reservation newReservation) {
		FileDataSource.uniqueInstance().putReservation(newReservation);
		return newReservation;
	}

	@Override
	public Reservation update(Reservation newReservation) {

		FileDataSource.uniqueInstance().updateReservation(newReservation);
		return newReservation;
	}

	@Override
	public Reservation delete(int idReservation) {
		return FileDataSource.uniqueInstance().deleteReservation(idReservation);
	}

}
