package online.dragon.ultima;

import online.dragon.ultima.module.item.*;
import online.dragon.ultima.module.player.*;
import online.dragon.ultima.module.character.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("")
public class UDOLService {

	private static final int sizeOfWorld = 50;
	private static volatile List<Object> objWorld = null;
	private static volatile List<UDOLPlayer> lstPlayer = null;
	private List<Object> objMyWorld = null;
	private UDOLPlayer myCharacter = null;
	
	//getClasses will trig this every time while getSingletons will not
	public UDOLService() {
		//read the world file here for only once
		//connect to database and create DAO
		
		//objWorld = new ArrayList<Object>();
		lstPlayer = new ArrayList<UDOLPlayer>();
		
		objWorld = UDOLUtil.loadWorldFile();
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
		
		Date d = new Date();
		cPlayer.setstrCreationTime(d.toString());		
		
		objWorld.add(cPlayer);
		cPlayer.setiCharacterID(objWorld.indexOf(cPlayer));
		objWorld.set(cPlayer.getiCharacterID(), cPlayer);
	}
	
	@GET
	@Path("world")
	public Response getWorld() {
		return Response.status(Status.OK).entity(objWorld).build();
	}
	
	@GET
	@Path("showMyWorld")
	public Response showMyWorld() {
		return Response.status(Status.OK).entity(objMyWorld).build();
	}
	
	private void getMyWorld(int iPlayerX, int iPlayerY) 
	{
		boolean bResult = false;
		Object objTemp = null;
		objMyWorld.clear();
		
		for (int i = 0; i < objWorld.size(); i++) 
		{
			objTemp = objWorld.get(i);
			
			if (objTemp instanceof UDOLItem )
			{
				UDOLItem cItem = (UDOLItem) objTemp;
				if ((cItem.getiLocationX() <= iPlayerX + sizeOfWorld) &&
					(cItem.getiLocationX() >= iPlayerX - sizeOfWorld) &&
					(cItem.getiLocationY() <= iPlayerY + sizeOfWorld) &&
					(cItem.getiLocationY() >= iPlayerY - sizeOfWorld)) {
						objMyWorld.add(cItem);
				}
			}
			
			if (objTemp instanceof UDOLCharacter )
			{
				UDOLCharacter cCharacter = (UDOLCharacter) objTemp;
				if ((cCharacter.getiLocationX() <= iPlayerX + sizeOfWorld) &&
					(cCharacter.getiLocationX() >= iPlayerX - sizeOfWorld) &&
					(cCharacter.getiLocationY() <= iPlayerY + sizeOfWorld) &&
					(cCharacter.getiLocationY() >= iPlayerY - sizeOfWorld)) {
						objMyWorld.add(cCharacter);
				}
			}
		}
	}
	
	//find player in the world file based on player id
	//add player in the online player list	
	private boolean updatePlayerList(int iPlayerID)
	{
		boolean bResult = false;
		Object objTemp = null;
		for (int i = 0; i < objWorld.size(); i++)
		{
			objTemp = objWorld.get(i);
			if (objTemp instanceof UDOLPlayer)
			{
				UDOLPlayer cPlayer = (UDOLPlayer)objTemp;
				if (cPlayer.getiCharacterID() == iPlayerID)
				{
					lstPlayer.add(cPlayer);
					myCharacter = cPlayer;
					bResult = true;
					break;	// character ID should be unique
				}
			}
		}
		return bResult;
	}
	
	@POST
	@Path("login/{playerID}")
	public Response userLogin(@PathParam("playerID") int iPlayerID) {
		boolean bResult = true;

		if(bResult)
		{
			//find and add player to list
			if(!updatePlayerList(iPlayerID))
				return Response.status(Status.NOT_FOUND).build();
			
			// get player's world and return to client
			objMyWorld = new ArrayList<Object>();
			getMyWorld(myCharacter.getiLocationX(), myCharacter.getiLocationY());
			
			return Response.status(Status.OK).entity(objMyWorld).build();
		}
		else
		{
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
	
	@POST
	@Path("/playerAction")
	public Response playerAction(
			@QueryParam("actionID") @DefaultValue("none") UDOLAction eActionID,
			@QueryParam("direction") @DefaultValue("stop") UDOLDirection eDirection)
	{
		switch (eActionID)
		{
			case move:
				myCharacter.move(eDirection);
				getMyWorld(myCharacter.getiLocationX(), myCharacter.getiLocationY());
				break;
			case none:
				break;
		}
		return Response.status(Status.OK).entity(objMyWorld).build(); 
	}
}
