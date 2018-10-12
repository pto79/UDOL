package online.dragon.ultima.module.character;

import java.io.Serializable;

import online.dragon.ultima.module.player.UDOLSkill;

public class UDOLCharacter implements Serializable {
	private int		iCharacterID = 0;
	private String 	sName = "";
	private String	sImage = "";
	
	private int		iLocationX = 0;
	private int 	iLocationY = 0;
	
	private	String	strCreationTime = "";
	private int		iCreationBy	= 0;
	
	private int		iStr = 0;
	private int		iDex = 0;
	private int		iInt = 0;
	
	private UDOLBrain 	cBrain;
	private UDOLSkill	cSkill;

	public int getiCharacterID() {
		return iCharacterID;
	}

	public void setiCharacterID(int iCharacterID) {
		this.iCharacterID = iCharacterID;
	}

	public String getsImage() {
		return sImage;
	}

	public void setsImage(String sImage) {
		this.sImage = sImage;
	}

	public String getstrCreationTime() {
		return strCreationTime;
	}

	public void setstrCreationTime(String string) {
		this.strCreationTime = string;
	}

	public int getiCreationBy() {
		return iCreationBy;
	}

	public void setiCreationBy(int iCreationBy) {
		this.iCreationBy = iCreationBy;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public int getiLocationX() {
		return iLocationX;
	}

	public void setiLocationX(int iLocationX) {
		this.iLocationX = iLocationX;
	}

	public int getiLocationY() {
		return iLocationY;
	}

	public void setiLocationY(int iLocationY) {
		this.iLocationY = iLocationY;
	}

	public int getiStr() {
		return iStr;
	}

	public void setiStr(int iStr) {
		this.iStr = iStr;
	}

	public int getiDex() {
		return iDex;
	}

	public void setiDex(int iDex) {
		this.iDex = iDex;
	}

	public int getiInt() {
		return iInt;
	}

	public void setiInt(int iInt) {
		this.iInt = iInt;
	}

	public UDOLBrain getcBrain() {
		return cBrain;
	}

	public void setcBrain(UDOLBrain cBrain) {
		this.cBrain = cBrain;
	}

	public UDOLSkill getcSkill() {
		return cSkill;
	}

	public void setcSkill(UDOLSkill cSkill) {
		this.cSkill = cSkill;
	}
	
	public void move(UDOLDirection direction) {
		switch (direction) {
		case up:
			this.setiLocationY(this.getiLocationY()-1);
			break;
		case down:
			this.setiLocationY(this.getiLocationY()+1);
			break;
		case left:
			this.setiLocationX(this.getiLocationX()-1);
			break;
		case right:
			this.setiLocationX(this.getiLocationX()+1);
			break;
		}
		//UDOLService.getMyWorld(this.getiLocationX(), this.getiLocationY());
	}
}
