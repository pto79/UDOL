package online.dragon.ultima;

import online.dragon.ultima.module.*;
import online.dragon.ultima.module.item.*;
import online.dragon.ultima.module.character.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("")
public class UDOLService {
	private static int counter = 0;
	private static List<Object> objWorld = null;
	
	//getClasses will trig this every time while getSingletons will not
	public UDOLService() {
		//read the world file here for only once
		//connect to database and create DAO
		counter++;
		
		objWorld = new ArrayList<Object>();
		
		try {
			objWorld = UDOLUtil.loadWorldFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GET
	public int getCounter() {
		return counter++;
	}
	
	@POST
	@Path("item")
	public void createItem() {
		UDOLItem cItem = new UDOLItem();
		cItem.setsName("apple");
		cItem.setiAmount(1);
		Date d = new Date();
		cItem.setiCreationTime(d.toString());

		// add to world object in memory
		objWorld.add(cItem);
		cItem.setiItemID(objWorld.indexOf(cItem));
		objWorld.set(cItem.getiItemID(), cItem);		
	}
	
	@POST
	@Path("world")
	public void saveWorld() {
		try {
			UDOLUtil.saveWorldFile(objWorld);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("character")
	public void createCharacter() {
		UDOLPlayer cPlayer = new UDOLPlayer();
		
		UDOLSkill cSkill = new UDOLSkill();
		cSkill.setsMeleeWeapon(100);
		cSkill.setsArchery(100);
		cSkill.setsMagery(100);
		
		cPlayer.setsName("pto");
		cPlayer.setiStr(100);
		cPlayer.setiDex(100);
		cPlayer.setiInt(100);
		cPlayer.setcSkill(cSkill);
		
		objWorld.add(cPlayer);
		cPlayer.setiCharacterID(objWorld.indexOf(cPlayer));
		objWorld.set(cPlayer.getiCharacterID(), cPlayer);
	}
	
	@GET
	@Path("world")
	public Response getWorld() {
		return Response.status(Status.OK).entity(objWorld).build();
	}
}
