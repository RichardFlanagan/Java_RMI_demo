package data;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;


/**
 * RemoteServerInterface.java
 * The interface required for the server-side implementation of the application.
 * @author RichardFlanagan - A00193644
 */
public interface RemoteServerInterface extends Remote{
	
	/**
	 * Create and return a new DataItem
	 * @param type The type of object to make
	 * @param paramList The list of parameters used to create the object
	 * @return The object
	 */
	public DataItem createItem(String type, String[] paramList) throws RemoteException;
	
	/**
	 * Add the item to the data list and re-serialize
	 * @param item The item to add to the list
	 * @throws IOException
	 */
	public void addAndSerialize(DataItem item) throws IOException, RemoteException;
	
	/**
	 * Edit an existing object and re-serialize
	 * @param item The item to edit
	 * @param paramList The list of new parameters for the object
	 * @throws IOException
	 */
	public void editAndSerialize(DataItem item, String[] paramList) throws IOException, RemoteException;
	
	/**
	 * Delete an item form the list and re-serialize
	 * @param item The item to delete
	 * @throws IOException
	 */
	public void deleteAndSerialize(DataItem item) throws IOException, RemoteException;
	
	/**
	 * Return the hash map of data.
	 * @return The hash map of data
	 */
	public HashMap<String, DataItem> getData() throws RemoteException;
	
	/**
	 * Return the type of data this manager is handling
	 * @return The object type this manager handles
	 */
	public String getDataType() throws RemoteException;
	
}
