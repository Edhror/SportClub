package it.capgemini.SportsClub.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.capgemini.SportsClub.dao.implementations.FileReservationDAO;

public class FileDataSource {

	private Map<Integer, Court> courts = new HashMap<>();
	private Map<Integer, Reservation> reservations;
	private Map<Integer, Client> clients = new HashMap<>();
	private static Reader reader;
	private static FileDataSource club;

	public static int POS_ID = 0;
	public static int POS_CLIENT_ID = 1;
	public static int POS_COURT_ID = 2;
	public static int POS_START = 3;
	public static int POS_END = 4;
	public static int POS_COST = 5;

	private FileDataSource() {

		courts.put(1, new TennisCourt(1, "Bjorn Borg", Surface.CLAY, 4, 10));
		courts.put(2, new TennisCourt(2, "John McEnroe", Surface.GRASS, 4, 12));

		clients.put(1, new Client(1, "Ciccio", "Pasticcio", LocalDate.of(1970, 6, 20)));
		clients.put(2, new Client(1, "Pippo", "Pippis", LocalDate.of(1980, 8, 10)));
		this.reservations = load(reader);
	}

	public static void setReader(Reader r) {
		reader = r;
	}

	public static FileDataSource uniqueInstance() {
		if (club == null) {
			club = new FileDataSource();
		}
		return club;
	}

	public Court findCourtById(int id) {
		return courts.get(id);
	}

	public Client findClientById(int id) {
		return clients.get(id);
	}

	public Map<Integer, Reservation> load(Reader r) {
		try (BufferedReader reader = new BufferedReader(r)) {
			Stream<String> lines = reader.lines();
			Stream<Reservation> streamRes = lines.filter(s -> s.trim().length() > 0).map(s -> s.split(","))
					.map(s -> reservationFromStringArray(s));
			return streamRes.collect(Collectors.toMap(Reservation::getId, Function.identity()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new SportClubException(e.getMessage(), e);
		}

	}

	public Reservation reservationFromStringArray(String[] fields) {
		Client client = findClientById(Integer.parseInt(fields[POS_CLIENT_ID].trim()));
		Court court = findCourtById(Integer.parseInt(fields[POS_COURT_ID].trim()));
		return new Reservation(Integer.parseInt(fields[POS_ID].trim()), LocalDateTime.parse(fields[POS_START].trim()),
				LocalDateTime.parse(fields[POS_END].trim()),client, court, Double.parseDouble(fields[POS_COST]));
	}
	
	private void associateCourtAndClient() {
//		reservations.values().stream().forEach(r -> {
//			
//		});
	}

	public Iterable<Reservation> getReservations() {
		return reservations.values();
	}

	public void putReservation(Reservation r) {
		int key = reservations.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
		key++;
		r.setId(key);
		reservations.put(key, r);
	}

	public void updateReservation(Reservation r) {
		reservations.put(r.getId(), r);
	}

	public Reservation deleteReservation(int id) {
		return reservations.remove(id);
	}

}
