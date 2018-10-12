package online.dragon.ultima.account;

public class UDOLAccount {
	private	String	sUsername = "";
	private	String	sPassword = "";
	private String	sEmail = "";
	private	int		iCreationTime = 0;
	private int		iLastLogin = 0;
	private int		iPlayerID = 0;
	private UDOLAccountLevel		cLevel = UDOLAccountLevel.player;
	private UDOLAccountStatus 	cStatus = UDOLAccountStatus.pending;
}
