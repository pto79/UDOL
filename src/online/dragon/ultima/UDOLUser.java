package online.dragon.ultima;

public class UDOLUser {
	private	String	sUsername = "";
	private	String	sPassword = "";
	private String	sEmail = "";
	private	int		iCreationTime = 0;
	private int		iLastLogin = 0;
	private UDOLUserLevel		cLevel = UDOLUserLevel.player;
	private UDOLAccountStatus 	cStatus = UDOLAccountStatus.pending;
}
