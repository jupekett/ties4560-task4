package fi.jupekett.task3;

import java.util.ArrayList;
import java.util.List;


/**
 * A class to represent an accommodation owner
 * @author Juho Kettunen (jupekett)
 *
 */
public class Owner {
	private int id;
	private String name;
	private List<Accommodation> accommodations = new ArrayList<>();
		
	public Owner() {
		// default constructor
	}
	
	public Owner(String name) {
		this.name = name;
	}
	
	public Owner(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Owner(int id, String name, List<Accommodation> accommodations) {
		this.id = id;
		this.name = name;
		this.accommodations = accommodations;
	}
	
	public void addAccommodation(Accommodation accommodation) {
		this.accommodations.add(accommodation);
		// TODO what to return? Owner, List or maybe boolean?
	}
	

	@Override
	public String toString() {
		return "ID: " + this.id + ", name: " + this.name + ", accommodations: " + this.accommodations;
	}
	
	
	/* GETTERS AND SETTERS */

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

	public List<Accommodation> getAccommodations() {
		return accommodations;
	}

	public void setAccommodations(List<Accommodation> accommodations) {
		// TODO verification
		this.accommodations = accommodations;
	}

	
}
