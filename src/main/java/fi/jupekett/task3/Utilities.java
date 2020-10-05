package fi.jupekett.task3;

import java.util.Random;
import java.util.regex.Pattern;


/**
 * Various helper functions.
 */
public class Utilities {
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
	
	
	private static String[] EMAIL_PROVIDERS =
		{
				"yahoo.com"
				,"gmail.com"
				,"luukku.com"
				,"sunpoint.fi"
				,"outlook.com"
				,"hotmail.com"
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
	 * Returns a semi-random email address based on name.
	 * @param name
	 * @return
	 */
	public static String getRandomEmail(String name) {
		Random rand = new Random();
		String convertedName = name.toLowerCase().replaceAll(" ", ".");
		String provider = EMAIL_PROVIDERS[rand.nextInt(EMAIL_PROVIDERS.length)];
		return convertedName + "@" + provider;
		
	}
	
	
	/**
	 * Checks for null, empty of whitespace string values.
	 * @param array strings to be checked.
	 * @return true if a string array contains null, empty or whitespace values.
	 */
	public static boolean someStringsAreNullOrBlank(String[] array) {
		for (String str : array) {
			if (str == null) return true;
			if (str.isBlank()) return true;
		}
		return false;
	}
	
	
	/**
	 * Validates email address format
	 * @param email
	 * @return true if valid.
	 */
	public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
	
	
	/**
	 * Returns the email if valid, and throws exception if not.
	 * @param email
	 * @return
	 */
	public static String getValidEmailOrThrow(String email) {
		boolean emailIsValid = isValid(email);
		if (!emailIsValid) {
			throw new IllegalArgumentException("Email address format is not valid.");
		}
		return email;
		
	}
	
	
}
