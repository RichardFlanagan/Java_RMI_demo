package server;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.concurrent.Callable;

import data.CPU;
import data.DataItem;
import data.HDD;
import data.RemoteServerInterface;


/**
 * DataManager.java
 * Operates as an interface tier between the data and the GUI and handles base functionality
 * @author RichardFlanagan - A00193644
 */
public class DataManager extends UnicastRemoteObject implements RemoteServerInterface {

	private static final long serialVersionUID = 1L;
	private static HashMap<String, DataItem> data = new HashMap<String, DataItem>();
	private static DataManager instance = null;
	private final static String DATA_TYPE = "HDD";
	
	
	/**
	 * Constructor.
	 */
	DataManager() throws RemoteException{
		super();
	}
	
	
	/**
	 * Return a reference to this object
	 * @return A reference to this object
	 */
	public static DataManager getInstance() throws RemoteException{
		if(instance == null){
			instance = new DataManager();
			loadDataFromMemory();
			return instance;
		}
		else{
			return instance;
		}
	}
	
	
	/**
	 * Load the data from the serialization file
	 */
	@SuppressWarnings("unchecked")
	private static void loadDataFromMemory(){
		try{
			FileInputStream aFileInStream = new FileInputStream(DATA_TYPE + ".ser");
			ObjectInputStream aObjectInStream = new ObjectInputStream(aFileInStream);
			data = (HashMap<String, DataItem>)aObjectInStream.readObject();
			aObjectInStream.close();
			System.out.println("The data has successfully been loaded from memory!"); 
		}
		catch(Exception e){
			System.out.println("Error: Data not found, creating new data files...");
			try{
				DataBuilder.buildData(DATA_TYPE);
				System.out.println("Data created!");
				loadDataFromMemory();
			}catch(IOException IOE){
				System.out.println("Data creation failed");
			}
		}
	}
	
	
	/**
	 * Return the hash map of data.
	 * @return The hash map of data
	 */
	public HashMap<String, DataItem> getData(){
		return data;
	}
	
	
	/**
	 * Create and return a new DataItem. Calling this function creates a new Callable thread which returns the DataItem, null if it fails
	 * @param type The type of object to make
	 * @param paramList The list of parameters used to create the object
	 * @return The object
	 */
	public DataItem createItem(final String type, final String[] paramList) {
		/* old code
		if(type.equalsIgnoreCase("CPU")){
			return new CPU().construct(paramList[0], paramList[1], paramList[2], Integer.parseInt(paramList[3]), Double.parseDouble(paramList[4]), Double.parseDouble(paramList[5]));
		}
		return null;
		 */
		try {
			return new Callable<DataItem>(){
				@Override
				public DataItem call() throws Exception {
					if(type.equalsIgnoreCase("CPU")){
				    	return new CPU().construct(paramList[0], paramList[1], paramList[2], Integer.parseInt(paramList[3]), Double.parseDouble(paramList[4]), Double.parseDouble(paramList[5]));
				    }
					else if(type.equalsIgnoreCase("HDD")){
				    	return new HDD().construct(paramList[0], paramList[1], paramList[2], Integer.parseInt(paramList[3]), Double.parseDouble(paramList[4]));
				    }
					return null;
				}
			}.call();
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 * Creates a new runnable thread which adds the item to the data list and re-serializes
	 * @param item The item to add to the list
	 */
	public void addAndSerialize(final DataItem item) /*throws IOException*/ {
		/* old code
		data.put(item.getDataArray()[0], item);
		serializeData(FILENAME);
		*/
		new Thread(new Runnable(){
			@Override
			public void run() {
				data.put(item.getDataArray()[0], item);
				try {
					serializeData(DATA_TYPE);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	
	/**
	 * Create a new runnable thread which edits an existing object and re-serializes
	 * @param item The item to edit
	 * @param paramList The list of new parameters for the object
	 */
	public void editAndSerialize(final DataItem item, final String[] paramList) /*throws IOException*/ {
		/* old code
		 * CPU c = null;
		
		if (item.getItemType().equals("CPU")){
			data.remove(item.getDataArray()[0]);
			
			c = (CPU)item;
			c.setName(paramList[0]);
			c.setManufacturer(paramList[1]);
			c.setReleaseDate(paramList[2]);
			c.setCores(Integer.parseInt(paramList[3]));
			c.setClockSpeed(Double.parseDouble(paramList[4]));
			c.setPrice(Double.parseDouble(paramList[5]));
			
			data.put(c.getDataArray()[0], c);
			serializeData(FILENAME);
		}
		*/
		new Thread(new Runnable(){
			@Override
			public void run() {
				
				if (item.getItemType().equals("CPU")){
					data.remove(item.getDataArray()[0]);
					CPU c = null;
					c = (CPU)item;
					c.setName(paramList[0]);
					c.setManufacturer(paramList[1]);
					c.setReleaseDate(paramList[2]);
					c.setCores(Integer.parseInt(paramList[3]));
					c.setClockSpeed(Double.parseDouble(paramList[4]));
					c.setPrice(Double.parseDouble(paramList[5]));
					
					data.put(c.getDataArray()[0], c);
					try {
						serializeData(DATA_TYPE);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				else if (item.getItemType().equals("HDD")){
					data.remove(item.getDataArray()[0]);
					HDD c = null;
					c = (HDD)item;
					c.setName(paramList[0]);
					c.setManufacturer(paramList[1]);
					c.setReleaseDate(paramList[2]);
					c.setStorage(Integer.parseInt(paramList[3]));
					c.setPrice(Double.parseDouble(paramList[4]));
					
					data.put(c.getDataArray()[0], c);
					try {
						serializeData(DATA_TYPE);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	
	/**
	 * Delete an item form the list and re-serialize
	 * @param item The item to delete
	 * @throws IOException
	 */
	public void deleteAndSerialize(final DataItem item) /*throws IOException*/ {
		/* old code
		data.remove(item.getDataArray()[0]);
		serializeData(FILENAME);
		*/
		new Thread(new Runnable(){
			@Override
			public void run() {
				data.remove(item.getDataArray()[0]);
				try {
					serializeData(DATA_TYPE);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	
	/**
	 * Serialize the data list. Is synchronized to avoid concurrency issues.
	 * @param filename The name of the serialized file
	 * @throws IOException
	 */
	private synchronized void serializeData(String filename) throws IOException{
		FileOutputStream outFile = new FileOutputStream(filename+".ser");
		ObjectOutputStream outStream = new ObjectOutputStream(outFile);
		outStream.writeObject(data);
		outStream.close();
	}
	
	
	/**
	 * Return the type of data this manager is handling
	 * @return The object type this manager handles
	 */
	public String getDataType()throws RemoteException{
		return DATA_TYPE;
	}

}