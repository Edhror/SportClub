package it.capgemini.SportsClub.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;


public abstract class Court {
	private int id;
	private String name;
	private Surface surface;
	private Set<Reservation> reservations = new HashSet<>();
    private int numPlayers;
    private double cost;
    
    public Court(int id, String name, Surface surface, int numPlayers, double cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		if(Arrays.stream(allowedSurfaces()).noneMatch(s -> s == surface)) {
			throw new SportClubException("Surface not allowed for this court");
		}
		this.surface = surface;
		if(numPlayers < 0 || numPlayers > maxPlayers()) {
			throw new SportClubException("Ilegal number of players for this court");
		}
		this.numPlayers = numPlayers;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Surface getSurface() {
		return surface;
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}


//	public Reservation makeReservation(int idClient, LocalDateTime start, LocalDateTime end) {
//		Reservation newRes = new Reservation(0, start, end, new Client(idClient));
//		for (Reservation r : reservations) {
//			if (newRes.overlapsWith(r)) {
//				return null;
//			}
//		}
//		reservations.add(newRes);
//        return newRes;
//	}
	
	protected abstract Surface[] allowedSurfaces();
	protected abstract int maxPlayers();
	
	public Reservation makeReservation(int idClient, LocalDateTime start, LocalDateTime end) {
		Reservation newRes = new Reservation(0, start, end, FileDataSource.uniqueInstance().findClientById(idClient), this,10);
//		for (Reservation r : reservations) {
//			if (newRes.overlapsWith(r)) {
//				return null;
//			}
//		}
//		ReservationPredicate rp = new ReservationPredicate(newRes);
//		boolean overlap = reservations.stream().anyMatch( new Predicate<Reservation> () {
//			@Override
//			public boolean test(Reservation t) {
//				// TODO Auto-generated method stub
//				return false;
//			}			
//		});
		
		Predicate<Reservation> pr =  r -> r.overlapsWith(newRes);
		
		boolean overlap = reservations.stream().anyMatch(  r -> r.overlapsWith(newRes));	
		
		if(overlap) {
			return null;
		}
		reservations.add(newRes);
        return newRes;
	}
	@Override
	public String toString() {
		return "Court [name=" + name + "]";
	}
	
	
}

class ReservationPredicate implements Predicate<Reservation> {

	private Reservation newReservation;
	public ReservationPredicate(Reservation newReservation) {
		this.newReservation = newReservation;
	}


	@Override
	public boolean test(Reservation t) {
		return newReservation.overlapsWith(t);
	}
	
}
