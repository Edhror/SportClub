package it.capgemini.SportsClub.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Reservation {
	private int id;
	private LocalDateTime start;
	private LocalDateTime end;
	private Client client;
    private Court court;
    private double cost;
    
	public Reservation(int id, LocalDateTime start, LocalDateTime end, Client client, Court court, double cost) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.client = client;
		this.court = court;
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Reservation() {

	}

	public boolean overlapsWith(Reservation other) {
		return start.isBefore(other.getEnd()) && end.isAfter(other.getStart());
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	
	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		Reservation other = (Reservation) obj;
		return this.id == other.id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", start=" + start + ", end=" + end + ", client=" + client + ", court=" + court
				+ ", cost=" + cost + "]";
	}

	
}
