package fi.jupekett.task3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A class to represent a customer
 * @author Juho Kettunen (jupekett)
 *
 */
@XmlRootElement
public class Customer {
	private int id;
	private String name;
	private String email;
	private List<Reservation> reservations = new ArrayList<>();
	private List<Link> links = new ArrayList<>();
	
	
	public Customer() {
		// default
	}
	
	
	public Customer(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	
	public Customer(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	
	public Customer(int id, String name, String email, List<Reservation> reservations) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.reservations = reservations;
	}
	
	
	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}
	
	
	/**
	 * Creates a Link object to store url and rel.
	 * @param url
	 * @param rel
	 */
	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		this.links.add(link);
	}
	
	
	@Override
	public String toString() {
		return "Customer ID: " + 
				this.id + 
				", name: " + 
				this.name + 
				", reservations: " + 
				this.reservations;
	}
	
	
	/* GETTERS AND SETTERS*/

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the reservations
	 */
	public List<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * @param reservations the reservations to set
	 */
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}


	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}


	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	

}
