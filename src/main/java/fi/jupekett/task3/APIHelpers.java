package fi.jupekett.task3;

import java.util.Random;


/**
 * Various helper functions.
 * @author Juho Kettunen (jupekett)
 *
 */
public class APIHelpers {
	private static String[] FIRST_NAMES = 
		{
				"James"
				,"Mary"
				,"John"
				,"Patricia"
				,"Robert"
				,"Jennifer"
				,"Michael"
				,"Linda"
				,"William"
				,"Elizabeth"
		};
	private static String[] LAST_NAMES = 
		{
				"Smith"
				,"Johnson"
				,"Williams"
				,"Brown"
				,"Jones"
				,"Garcia"
				,"Miller"
				,"Davis"
				,"Rodriguez"
				,"Martinez"
		};
	
	
	private static String[] RESORT_PREFIXES = 
		{
				"Sunny"
				,"Paradise"
				,"Spooky"
				,"Grandma's"
				,"Comfy"
				,"Deluxe"
				,"True"
				,"Snowy"
				,"Sokos"
				,"K-"
		};
	
	private static String[] RESORT_POSTFIXES = 
		{
				"Glade"
				,"Woods"
				,"Hotel"
				,"Cabins"
				,"River"
				,"Coast"
				,"Motel"
				,"Housing"
		};
	
	
	/**
	 * Returns a random person name.
	 * @return
	 */
	public static String getRandomPersonName() {
		Random rand = new Random();
		String firstName = FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
		String lastName = LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
		return firstName + " " + lastName; 
	}
	
	/**
	 * Returns a random resort name.
	 * @return
	 */
	public static String getRandomResortName() {
		Random rand = new Random();
		String firstPart = RESORT_PREFIXES[rand.nextInt(RESORT_PREFIXES.length)];
		String lastPart = RESORT_POSTFIXES[rand.nextInt(RESORT_POSTFIXES.length)];
		return firstPart + " " + lastPart;
	}
	
	
	/**
	 * Returns an error message as a JSON string.
	 * @param status HTTP status code to be returned.
	 * @param message Error message to be returned.
	 * @return Error message as JSON.
	 */
	public static String getErrorJson(int status, String message) {
		ErrorJson error = new ErrorJson(status, message);
		return error.toString();
	}

}
