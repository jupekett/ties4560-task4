package fi.jupekett.task3;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a reservation.
 * @author Juho Kettunen (jupekett)
 */
public class Reservation {
	private int id;
	private int accommodationId;
	private List<Link> links = new ArrayList<>();
	
	
	public Reservation() {
		//
	}
	
	
	public Reservation(int id, int accommodationId) {
		this.id = id;
		this.accommodationId = accommodationId;
	}
	
	
	@Override
	public String toString() {
		return "Reservation ID: " + this.getId() + ", accommodation ID: " + this.accommodationId;
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
	
	
	/* GETTERS AND SETTERS */

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
	 * @return the accommodationId
	 */
	public int getAccommodationId() {
		return accommodationId;
	}


	/**
	 * @param accommodationId the accommodationId to set
	 */
	public void setAccommodationId(int accommodationId) {
		this.accommodationId = accommodationId;
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
