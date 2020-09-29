package fi.jupekett.task3;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent an accommodation
 * @author Juho Kettunen (jupekett)
 *
 */
public class Accommodation {
	private int id;
	private String name;
	private List<Link> links = new ArrayList<>();
	
	
	
	public Accommodation() {
		this.id = 0;
		this.name = "";
	}
	
	public Accommodation(String name) {
		this.name = name;
	}
	
	public Accommodation(int id, String name) {
		this.id = id;
		this.name = name;
	}
	

	@Override
	public String toString() {
		return "Accommodation ID: " + this.getId() + ", name: " + this.getName();
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
		// TODO id validation? Or leave it to the DB?
		this.id = id;
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name){
		this.name = name;			
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
