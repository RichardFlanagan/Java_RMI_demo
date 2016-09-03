package data;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * CPU.java
 * The CPU item. Contains information about a CPU.
 * @author RichardFlanagan - A00193644
 */
public class CPU implements Serializable, DataItem {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String manufacturer;
	private Date releaseDate;
	private int cores;
	private double clockSpeed;	
	private double price;
	
	
	/**
	 * Constructor. Does nothing. Use construct() instead.
	 */
	public CPU(){}
	
	
	/**
	 * Initialize this objects data members
	 * @param name The name/model of the chip
	 * @param manufacturer The company that makes the chip
	 * @param date The date the chip was released. ("yyyy-MM")
	 * @param cores The number of cores in the chip
	 * @param clockSpeed The chip's clock speed in GH
	 * @param price The retail price(RRP) of the chip on release.
	 * @return This CPU object
	 */
	public CPU construct(String name, String manufacturer, String date, int cores, double clockSpeed, double price){
		this.name = name;
		this.manufacturer = manufacturer;
		this.cores = cores;
		this.clockSpeed = clockSpeed;		
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
		System.out.println("   Clock Speed:  " + clockSpeed);
		System.out.println("   Cores:        " + cores);
		System.out.println("   Retail Price: " + price);
	}
	
	
	/**
	 * Return the objects information in an array of strings.
	 * @return The array containing the information belonging to the DataItem
	 */
	@SuppressWarnings("deprecation")
	public String[] getDataArray(){
		return new String[]{getName(),getManufacturer(), (releaseDate.getYear()+1900) + "-" + (releaseDate.getMonth()+1)/*getReleaseDate().toString()*/, ""+getCores(), ""+getClockSpeed(), ""+getPrice()};
	}
	
	
	/**
	 * Return the column header list for this type of object
	 * @return The column header list for CPU
	 */
	public String[] getHeader(){
		return CPUHeader.getInstance().getHeader();
	}
	
	
	/**
	 * Return the class type as a string
	 * @return The class type as a string
	 */
	@Override
	public String getItemType() {
		return "CPU";
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
	 * @return the clockSpeed
	 */
	public double getClockSpeed() {
		return clockSpeed;
	}
	
	
	/**
	 * @param clockSpeed the clockSpeed to set
	 */
	public void setClockSpeed(double clockSpeed) {
		this.clockSpeed = clockSpeed;
	}
	
	
	/**
	 * @return the cores
	 */
	public int getCores() {
		return cores;
	}
	
	
	/**
	 * @param cores the cores to set
	 */
	public void setCores(int cores) {
		this.cores = cores;
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