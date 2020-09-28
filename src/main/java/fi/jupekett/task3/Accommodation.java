package fi.jupekett.task3;


/**
 * A class to represent an accommodation
 * @author Juho Kettunen (jupekett)
 *
 */
public class Accommodation {
	private int id;
	private String name;
	
	
	public Accommodation() {
		this.id = 0;
		this.name = "";
	}
	
	public Accommodation(int id, String name) {
		this.id = id;
		this.name = name;
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


	public void setName(String name) throws IllegalArgumentException{
//		if (name == null || name.isEmpty()) {
//			throw new IllegalArgumentException("Cannot set empty name to an accommodation");
//		}
		this.name = name;			
	}
}
