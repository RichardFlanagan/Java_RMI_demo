package server;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.CPU;
import data.DataItem;
import data.HDD;


/**
 * DataBuilder.java
 * Builds and serializes the test data.
 * @author RichardFlanagan - A00193644
 */
public class DataBuilder {
	
	
	/**
	 * Build and serialize the test data
	 * @param filename The name of the file to save the data to
	 * @throws IOException
	 */
	public static void buildData(String filename) throws IOException{
		
		HashMap<String, DataItem> dataList = createDataList(filename);
		
		FileOutputStream outFile = new FileOutputStream(filename+".ser");
		ObjectOutputStream outStream = new ObjectOutputStream(outFile);
		outStream.writeObject(dataList);
		outStream.close();
		
	}
	
	
	/**
	 * Generate the test data
	 * @param filename The name to save the file as
	 * @return The hash map
	 */
	private static HashMap<String, DataItem> createDataList(String filename){
		HashMap<String, DataItem> data = new HashMap<String, DataItem>();
		
		if (filename.equals("CPU")){
			// CPU(name, manufacturer, release date, cores, clock speed, price);
			data.put("Core i7 4930K", new CPU().construct("Core i7 4930K", "Intel", "2013-07", 6, 3.4, 549));
			data.put("Core i7 4770R", new CPU().construct("Core i7 4770R", "Intel", "2013-04", 4, 3.2, 358));
			data.put("Core i7 4771",  new CPU().construct("Core i7 4771", "Intel", "2013-06", 4, 3.5, 315));
			data.put("Core i7 4790",  new CPU().construct("Core i7 4790", "Intel", "2014-05", 4, 3.6, 195));
			data.put("Core i7 3960x", new CPU().construct("Core i7 3960x", "Intel", "2011-11", 6, 3.3, 1073));
			data.put("Core i7 3930k", new CPU().construct("Core i7 3930k", "Intel", "2011-11", 6, 3.2, 624));
			data.put("Core i5 4690s", new CPU().construct("Core i5 4690s", "Intel", "2014-02", 4, 3.2, 195));
			data.put("Core i5 3770s", new CPU().construct("Core i5 3770s", "Intel", "2012-04", 4, 3.1, 312));
			data.put("Core i5 4670k", new CPU().construct("Core i5 4670k", "Intel", "2013-04", 4, 3.4, 234));
			data.put("Core i3 4340",  new CPU().construct("Core i3 4340", "Intel", "2013-07", 2, 3.6, 165));
			data.put("Opteron 6282 SE", new CPU().construct("Opteron 6282 SE", "AMD", "2011-11", 16, 2.6, 1019));
			data.put("Opteron 6276", new CPU().construct("Opteron 6276", "AMD", "2011-11", 16, 2.3, 831));
			data.put("Opteron 6272", new CPU().construct("Opteron 6272", "AMD", "2011-11", 16, 2.1, 1000));
			data.put("FX 9370", new CPU().construct("FX 9370", "AMD", "2013-06", 8, 4.4, 224));
			data.put("FX 9590", new CPU().construct("FX 9590", "AMD", "2013-06", 8, 4.7, 252));
			data.put("FX 8350", new CPU().construct("FX 8350", "AMD", "2012-10", 8, 4.0, 170));
			data.put("FX 8320", new CPU().construct("FX 8320", "AMD", "2012-10", 8, 3.5, 143));
			data.put("FX 6350", new CPU().construct("FX 6350", "AMD", "2012-10", 6, 3.9, 130));
			data.put("A7",  new CPU().construct("A7", "Apple", "2013-09", 2, 1.3, -1));
			data.put("A6x", new CPU().construct("A6x", "Apple", "2012-10", 2, 1.4, -1));
			data.put("A6",  new CPU().construct("A6", "Apple", "2012-09", 2, 1.3, -1));
			data.put("A5x", new CPU().construct("A5x", "Apple", "2012-03", 2, 1.0, -1));
			data.put("A5",  new CPU().construct("A5", "Apple", "2011-03", 2, 1.0, -1));
			data.put("A4",  new CPU().construct("A4", "Apple", "2010-03", 1, 1.0, -1));
		}else if(filename.equals("HDD")){
			// HDD(name, manufacturer, release date, storage, price);
			data.put("Black 1TB", new HDD().construct("Black 1TB", "Western Digital", "2013-10", 1024, 55));
			data.put("Black 2TB", new HDD().construct("Black 2TB", "Western Digital", "2013-10", 248, 97));
			data.put("Desktop SSHD 1TB", new HDD().construct("Desktop SSHD 1TB", "Seagate", "2013-08", 1024, 62));
			data.put("Barracuda 7200.14 2TB", new HDD().construct("Barracuda 7200.14 2TB", "Seagate", "2011-09", 2048, 62));
			data.put("Barracuda 7200.14 1TB", new HDD().construct("Barracuda 7200.14 1TB", "Seagate", "2011-11", 1024, 42));
			data.put("VelociRaptor 1TB", new HDD().construct("VelociRaptor 1TB", "Western Digital", "2012-04", 1024, 165));
			data.put("Red 3TB", new HDD().construct("Red 3TB", "Western Digital", "2012-04", 3072, 103));
			data.put("Desktop HDD 4TB", new HDD().construct("Desktop HDD 4TB", "Seagate", "2013-02", 4096, 109));
		}
		
		return data;
	}
}
