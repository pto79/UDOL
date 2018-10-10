package online.dragon.ultima;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("")
public class UDOLService {
	private static int counter = 0;
	
	//getClasses will trig this every time while getSingletons will not
	public UDOLService() {
		//read the world file here for only once
		//connect to database and create DAO
		counter++;
	}
	
	@GET
	public int getCounter() {
		return counter++;
	}
	
	@POST
	public void createItem() {
		
	}
	
}
