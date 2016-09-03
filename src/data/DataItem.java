package data;


/**
 * DataItem.java
 * The interface for a single DataItem.
 * @author RichardFlanagan - A00193644
 */
public interface DataItem {
	
	/**
	 * Return the class type as a string
	 * @return The class type as a string
	 */
	public String getItemType();
	
	/**
	 * Returns the objects information in an array of strings.
	 * @return The array containing the information belonging to the DataItem
	 */
	public String[] getDataArray();
	
	/**
	 * Return the column header list for this type of object
	 * @return The column header list for CPU
	 */
	public String[] getHeader();
	
}
