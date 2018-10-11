package online.dragon.ultima;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

public class UDOLUtil {
	// save uploaded file to new location
	public static void writeToFile(InputStream uploadedInputStream,	String uploadedFileLocation) 
	{
		try 
		{
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	// save object list to file
	public static void saveWorldFile(List<Object> extrList) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\save\\world.bin"));
		oos.writeObject(extrList);
		oos.flush();
		oos.close();		
	}
	
	public static List<Object> loadWorldFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Object> worldObject = null;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\save\\world.bin"));
		worldObject = (List<Object>) ois.readObject();
		ois.close();
		return worldObject;
	}
}
