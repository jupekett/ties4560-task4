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
	
	
}
