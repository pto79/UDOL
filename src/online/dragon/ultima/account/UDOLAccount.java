package online.dragon.ultima.account;

import java.io.Serializable;
import java.util.List;

import online.dragon.ultima.UDOLUtil;
import online.dragon.ultima.misc.UDOLConst;

public class UDOLAccount implements Serializable {
	private int		iAction = 0;
	private	String	sUsername = "";
	private	String	sPassword = "";
	private String	sEmail = "";
	private	String	sCreationTime = "";
	private String	sLastLogin = "";
	private int		iPlayerID = 0;
	private int		iLevel = UDOLConst.lvlPlayer;
	private int 	iStatus = UDOLConst.pending;
	
	public UDOLAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getiAction() {
		return iAction;
	}

	public void setiAction(int iAction) {
		this.iAction = iAction;
	}

	public String getsUsername() {
		return sUsername;
	}

	public void setsUsername(String sUsername) {
		this.sUsername = sUsername;
	}

	public String getsPassword() {
		return sPassword;
	}

	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsCreationTime() {
		return sCreationTime;
	}

	public void setsCreationTime(String sCreationTime) {
		this.sCreationTime = sCreationTime;
	}

	public String getsLastLogin() {
		return sLastLogin;
	}

	public void setsLastLogin(String sLastLogin) {
		this.sLastLogin = sLastLogin;
	}

	public int getiPlayerID() {
		return iPlayerID;
	}

	public void setiPlayerID(int iPlayerID) {
		this.iPlayerID = iPlayerID;
	}

	public int getiLevel() {
		return iLevel;
	}

	public void setiLevel(int iLevel) {
		this.iLevel = iLevel;
	}

	public int getiStatus() {
		return iStatus;
	}

	public void setiStatus(int iStatus) {
		this.iStatus = iStatus;
	}

	public boolean register(List<UDOLAccount> lstAccount) {
		// validation check whether username or email duplicate?
		UDOLAccount tempAccount = null;
		for (int i = 0; i < lstAccount.size(); i++) {
			tempAccount = lstAccount.get(i);
			if (tempAccount.getsUsername().equals(this.getsUsername()) ||
				tempAccount.getsEmail().equals(this.getsEmail()))
			{
				return false;
			}
		}
		
		lstAccount.add(this);
		this.setiPlayerID(lstAccount.indexOf(this));
		this.setsCreationTime(UDOLUtil.getTime());
		lstAccount.set(this.getiPlayerID(), this);
		return true;
	}
	
	public static int login(List<UDOLAccount> lstAccount, String username, String password) {
		UDOLAccount tempAccount = null;
		for (int i = 0; i < lstAccount.size(); i++)
		{
			tempAccount = lstAccount.get(i);
			if ((tempAccount.getsUsername() == username) && (tempAccount.getsPassword() == password))
			{
				tempAccount.setsLastLogin(UDOLUtil.getTime());
				lstAccount.set(i, tempAccount);
				return tempAccount.getiPlayerID();
			}
		}
		return -1;
	}
}
