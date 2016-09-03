package client;

import java.util.Locale;


/**
 * ClientMain.java
 * Launches the client GUI application
 * @author RichardFlanagan - A00193644
 */
public class ClientMain {
	
	/**
	 * Main
	 * @param args The list of command line arguments
	 */
	public static void main(String[] args) {
		new GUIManager(new Locale("en", "EN"));
	}
	
}