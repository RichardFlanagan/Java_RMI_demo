package data;


/**
 * CPUHeader.java
 * The interface for a single CPUHeader. Used to get the column names for the CPU object type;
 * @author RichardFlanagan - A00193644
 */
public class CPUHeader implements DataItemHeader{
	
	private static DataItemHeader instance = null;
	
	
	/**
	 * Constructor
	 */
	CPUHeader(){
		
	}
	
	
	/**
	 * Return a reference to this object
	 * @return A reference to this object
	 */
	public static DataItemHeader getInstance(){
		if (instance == null){
			instance = new CPUHeader();
		}
		return instance;
	}
	
	
	/**
	 * Return the column header list for this type of object
	 * @return The column header list for a CPU
	 */
	public String[] getHeader(){
		return new String[]{"Model", "Manufacturer", "Release Date", "Cores", "Clock Speed", "Price"};
	}
}
