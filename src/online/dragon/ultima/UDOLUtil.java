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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import online.dragon.ultima.account.UDOLAccount;

public class UDOLUtil {
	
	// get current time
	public static String getTime() {
		Date d = new Date();
		return d.toString();
	}
	
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
	public static void saveWorldFile(List<Object> world) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("D:\\save\\world.bin"));
			oos.writeObject(world);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Object> loadWorldFile() {
		List<Object> worldObject = new ArrayList<Object>();
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("D:\\save\\world.bin"));
			worldObject = (List<Object>) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return worldObject;
	}
	
	public static void saveAccountFile(List<UDOLAccount> account) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("D:\\save\\account.bin"));
			oos.writeObject(account);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static List<UDOLAccount> loadAccountFile() {
		List<UDOLAccount> lstAccount = new ArrayList<UDOLAccount>();
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("D:\\save\\account.bin"));
			lstAccount = (List<UDOLAccount>) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstAccount;
	}
}
