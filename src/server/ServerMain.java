package server;

import java.rmi.Naming;
import data.RemoteServerInterface;


/**
 * ServerMain.java
 * Launches the server-side application
 * @author RichardFlanagan - A00193644
 */
public class ServerMain {

	private static RemoteServerInterface dataManager = null;
	
	/**
	 * Main
	 * @param args The list of command line arguments
	 */
	public static void main(String[] args) {
		try {
			dataManager = DataManager.getInstance();
		} catch (Exception e) {
			System.out.println("ServerError1: Could not create the DataManager");
			e.printStackTrace();
		}
		
		try {
			Naming.rebind("dataManager", dataManager);
	        
		} catch (Exception e) {
			System.out.println("ServerError2: Could not rebind the object");
			e.printStackTrace();
		}
	}

}
