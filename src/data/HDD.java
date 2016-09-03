package data;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * HDD.java
 * The HDD item. Contains information about a HDD.
 * @author RichardFlanagan - A00193644
 */
public class HDD implements Serializable, DataItem {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String manufacturer;
	private Date releaseDate;
	private int storage;
	private double price;
	
	
	/**
	 * Constructor. Does nothing. Use construct() instead.
	 */
	public HDD(){}
	
	
	/**
	 * Initialize this objects data members
	 * @param name The name/model of the drive
	 * @param manufacturer The company that makes the drive
	 * @param date The date the drive was released. ("yyyy-MM")
	 * @param storage The amount of storage space on the drive
	 * @param price The retail price(RRP) of the chip on release.
	 * @return This HDD object
	 */
	public HDD construct(String name, String manufacturer, String date, int storage, double price){
		this.name = name;
		this.manufacturer = manufacturer;
		this.storage = storage;
		this.price = price;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			this.releaseDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	/**
	 * Print the object's details to the console
	 */
	@SuppressWarnings("deprecation")
	public void printDetails(){
		System.out.println("- Model:       " + name);
		System.out.println("   Manufacturer: " + manufacturer);
		System.out.println("   Release Date: " + (releaseDate.getYear()+1900) + "-" + (releaseDate.getMonth()+1));
		System.out.println("   Storage:  " + storage);
		System.out.println("   Retail Price: " + price);
	}
	
	
	/**
	 * Return the objects information in an array of strings.
	 * @return The array containing the information belonging to the DataItem
	 */
	@SuppressWarnings("deprecation")
	public String[] getDataArray(){
		return new String[]{getName(),getManufacturer(), (releaseDate.getYear()+1900) + "-" + (releaseDate.getMonth()+1)/*getReleaseDate().toString()*/, ""+getStorage(), ""+getPrice()};
	}
	
	
	/**
	 * Return the column header list for this type of object
	 * @return The column header list for HDD
	 */
	public String[] getHeader(){
		return HDDHeader.getInstance().getHeader();
	}
	
	
	/**
	 * Return the class type as a string
	 * @return The class type as a string
	 */
	@Override
	public String getItemType() {
		return "HDD";
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	
	/**
	 * @return the releaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	
	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(String releaseDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			this.releaseDate = sdf.parse(releaseDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @return the storage
	 */
	public int getStorage() {
		return storage;
	}
	
	
	/**
	 * @param storage the storage to set
	 */
	public void setStorage(int storage) {
		this.storage = storage;
	}
	
	
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
}