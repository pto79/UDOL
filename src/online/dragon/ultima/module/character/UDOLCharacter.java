package online.dragon.ultima.module.character;

import online.dragon.ultima.module.UDOLSkill;

public class UDOLCharacter {
	private int		iCharacterID = 0;
	private String 	sName = "";
	private String	sImage = "";
	
	private int		iLocationX = 0;
	private int 	iLocationY = 0;
	
	private	int		iCreationTime = 0;
	private int		iCreationBy	= 0;
	
	private int		iStr = 0;
	private int		iDex = 0;
	private int		iInt = 0;
	
	private UDOLSkill	cSkill;

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

	public UDOLSkill getcSkill() {
		return cSkill;
	}

	public void setcSkill(UDOLSkill cSkill) {
		this.cSkill = cSkill;
	}
	
	
}
