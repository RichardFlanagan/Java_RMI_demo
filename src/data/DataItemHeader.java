package data;


/**
 * DataItemHeader.java
 * The interface for a single DataItemHeader. Used to get the column names for an object type;
 * @author RichardFlanagan - A00193644
 */
public interface DataItemHeader {
	
	/**
	 * Return the column header list for this type of object
	 * @return The column header list for a DataItem
	 */
	public String[] getHeader();
	
}
