package online.dragon.ultima;

import online.dragon.ultima.module.item.*;
import online.dragon.ultima.module.player.*;
import online.dragon.ultima.account.UDOLAccount;
import online.dragon.ultima.misc.UDOLConst;
import online.dragon.ultima.module.character.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("")
public class UDOLService {

	private static final int sizeOfWorld = 50;
	public static volatile List<Object> objWorld = null;
	public static volatile List<UDOLPlayer> lstPlayer = null;
	public static volatile List<UDOLAccount> lstAccount = null;
	private List<Object> myWorld = null;
	private UDOLPlayer myCharacter = null;
	
	//getClasses will trig this every time while getSingletons will not
	public UDOLService() {
		//read the world file here for only once
		//connect to database and create DAO
		
		//objWorld = new ArrayList<Object>();
		lstPlayer = new ArrayList<UDOLPlayer>();
		lstAccount = UDOLUtil.loadAccountFile();
		objWorld = UDOLUtil.loadWorldFile();
	}
	
	@POST
	@Path("item")
	public void createItem() {
		UDOLItem cItem = new UDOLItem();
		cItem.setsName("apple");
		cItem.setiAmount(1);
		cItem.setiCreationTime(UDOLUtil.getTime());

		// add to world object in memory
		objWorld.add(cItem);
		cItem.setiItemID(objWorld.indexOf(cItem));
		objWorld.set(cItem.getiItemID(), cItem);		
	}
	
	@POST
	@Path("world/{action}")
	public Response gameWorld(@PathParam("action") int action) {
		if ((myCharacter == null) || (myCharacter.getiLevel() != UDOLConst.lvlOwner))
			return Response.status(Status.UNAUTHORIZED).build();
		
		switch (action) {
		case UDOLConst.saveWorld:
			UDOLUtil.saveWorldFile(objWorld);
			break;
		case UDOLConst.getWorld:
			break;
		}
		return Response.status(Status.OK).entity(objWorld).build();
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
		cPlayer.setsCreationTime(UDOLUtil.getTime());		
		
		objWorld.add(cPlayer);
		cPlayer.setiCharacterID(objWorld.indexOf(cPlayer));
		objWorld.set(cPlayer.getiCharacterID(), cPlayer);
	}
	

	@GET
	@Path("showMyWorld")
	public Response showMyWorld() {
		return Response.status(Status.OK).entity(myWorld).build();
	}
	
	private void getMyWorld(int iPlayerX, int iPlayerY) 
	{
		Object objTemp = null;
		myWorld.clear();
		
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
					myWorld.add(cItem);
				}
			}
			
			if (objTemp instanceof UDOLCharacter )
			{
				UDOLCharacter cCharacter = (UDOLCharacter) objTemp;
				if ((cCharacter.getiLocationX() <= iPlayerX + sizeOfWorld) &&
					(cCharacter.getiLocationX() >= iPlayerX - sizeOfWorld) &&
					(cCharacter.getiLocationY() <= iPlayerY + sizeOfWorld) &&
					(cCharacter.getiLocationY() >= iPlayerY - sizeOfWorld)) {
					myWorld.add(cCharacter);
				}
			}
		}
	}
	
	//find player in the world file based on player id
	//add player in the online player list	
	private boolean updatePlayerList(int iPlayerID, boolean logout)
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
	@Path("account")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response accountAction(UDOLAccount account) {

		switch (account.getiAction()) {
		case UDOLConst.login:
			int playerID = UDOLAccount.login(lstAccount, account.getsUsername(), account.getsPassword());
			
			if(playerID == -1)
				return Response.status(Status.UNAUTHORIZED).build();
			
			if(!updatePlayerList(playerID, false))
				return Response.status(Status.NOT_FOUND).build();
			
			// get player's world and return to client
			myWorld = new ArrayList<Object>();
			getMyWorld(myCharacter.getiLocationX(), myCharacter.getiLocationY());
			return Response.status(Status.OK).entity(myWorld).build();
			
		case UDOLConst.logout:
			lstPlayer.remove(myCharacter);
			myCharacter = null;
			break;
			
		case UDOLConst.register:
			UDOLAccount newAccount = new UDOLAccount();
			newAccount = account;
			if(!newAccount.register(lstAccount))
				return Response.status(Status.CONFLICT).build();
			
		case UDOLConst.saveAccount:
			UDOLUtil.saveAccountFile(lstAccount);
			break;
		
		case UDOLConst.getAccount:
			return Response.status(Status.OK).entity(lstAccount).build();
		}
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("playerAction")
	public Response playerAction(@QueryParam("actionID") int actionID,
			@QueryParam("direction") int direction)
	{
		if (myCharacter == null)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		
		switch (actionID)
		{
			case UDOLConst.move:
				myCharacter.move(direction);
				getMyWorld(myCharacter.getiLocationX(), myCharacter.getiLocationY());
				break;
			default:
				break;
		}
		return Response.status(Status.OK).entity(myWorld).build(); 
	}
}
