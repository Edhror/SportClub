package it.capgemini.SportsClub.model;

import java.time.LocalDate;

public class Client {
	private int id;
	private String name;
	private String lastname;
	private LocalDate dateofbirth;

	public Client(int id, String name, String lastname, LocalDate dateofbirth) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.dateofbirth = dateofbirth;
	}

	public Client() {
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + "]";
	}
    
}
