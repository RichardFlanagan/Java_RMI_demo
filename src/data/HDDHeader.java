package data;


/**
 * HDDHeader.java
 * The interface for a single HDDHeader. Used to get the column names for the HDD object type;
 * @author RichardFlanagan - A00193644
 */
public class HDDHeader implements DataItemHeader{
	
	private static DataItemHeader instance = null;
	
	
	/**
	 * Constructor
	 */
	HDDHeader(){
		
	}
	
	
	/**
	 * Return a reference to this object
	 * @return A reference to this object
	 */
	public static DataItemHeader getInstance(){
		if (instance == null){
			instance = new HDDHeader();
		}
		return instance;
	}
	
	
	/**
	 * Return the column header list for this type of object
	 * @return The column header list for a HDD
	 */
	public String[] getHeader(){
		return new String[]{"Model", "Manufacturer", "Release Date", "Storage", "Price"};
	}
}

