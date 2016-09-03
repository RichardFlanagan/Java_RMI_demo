package lang;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * Messages.java
 * Handles internationalization/localization. 
 * (This exists because I applied for Erasmus in France and I wanted to try it out in case I was accepted.)
 * @author RichardFlanagan - A00193644
 */
public class Messages {
	private static String bundleName = "lang.messages";
	private static Locale locale = new Locale("en", "IE");
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName, locale);

	
	/**
	 * Return the string belonging to the specified key
	 * @param key The localization key
	 * @return The string message belonging to the key
	 */
	public static String getString(String key) {
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	
	/**
	 * Set the current localization area (language)
	 * @param localePar The locale and language
	 */
	public static void setLocale(Locale localePar){
		locale = localePar;
		resourceBundle = ResourceBundle.getBundle(bundleName, locale);
	}
	
	
	/**
	 * Return the current localization locale
	 * @return The current locale
	 */
	public static Locale getLocale(){
		return locale;
	}
	
}