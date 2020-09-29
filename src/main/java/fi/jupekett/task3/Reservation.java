package fi.jupekett.task3;


/**
 * A class to represent a reservation.
 * @author Juho Kettunen (jupekett)
 */
public class Reservation {
	private int id;
	private int accommodationId;
	
	
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

}
