package fi.jupekett.task3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * A class to represent an accommodation owner
 */
@XmlRootElement
public class Owner {
	private int id;
	private String name;
	private String email;
	
	private List<Accommodation> accommodations = new ArrayList<>();
	private List<Link> links = new ArrayList<>();
		
	
	public Owner(String name, String email) {
		this.name = name;
		this.email = Utilities.getValidEmailOrThrow(email);
	}
	
	
	public Owner(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = Utilities.getValidEmailOrThrow(email);
	}

	
	public Owner(int id, String name, String email, List<Accommodation> accommodations) {
		this.id = id;
		this.name = name;
		this.email = Utilities.getValidEmailOrThrow(email);
		this.accommodations = accommodations;
	}
	
	
	public void addAccommodation(Accommodation accommodation) {
		this.accommodations.add(accommodation);
	}
	

	@Override
	public String toString() {
		return "ID: " + this.id + ", name: " + this.name + ", accommodations: " + this.accommodations;
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
		this.accommodations = accommodations;
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
		this.email = Utilities.getValidEmailOrThrow(email);
	}

	
}
