package online.dragon.ultima.module.item;

import java.io.Serializable;

public class UDOLItem implements Serializable {
	private int		iItemID = 0;
	private String 	sName	= "";
	
	private int		iLink 	= 0;	//ID for container
	private int		iRef	= 0;	//for scroll ref to spell
	private int		iAmount	= 0;	//amount = 0 for item which can not overlap. default is 1
	
	private String	iCreationTime = "";
	private int		iCreationBy	= 0;
	
	private int		iLocationX = 0;	// if item does not in any container, then location is required
	private int		iLocationY = 0;
	
	private boolean	bBlessed = false;
	private int		iPrice = 0;
	
	public UDOLItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getiItemID() {
		return iItemID;
	}

	public void setiItemID(int iItemID) {
		this.iItemID = iItemID;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public int getiLink() {
		return iLink;
	}

	public void setiLink(int iLink) {
		this.iLink = iLink;
	}

	public int getiRef() {
		return iRef;
	}

	public void setiRef(int iRef) {
		this.iRef = iRef;
	}

	public int getiAmount() {
		return iAmount;
	}

	public void setiAmount(int iAmount) {
		this.iAmount = iAmount;
	}

	public String getiCreationTime() {
		return iCreationTime;
	}

	public void setiCreationTime(String string) {
		this.iCreationTime = string;
	}

	public int getiCreationBy() {
		return iCreationBy;
	}

	public void setiCreationBy(int iCreationBy) {
		this.iCreationBy = iCreationBy;
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

	public boolean isbBlessed() {
		return bBlessed;
	}

	public void setbBlessed(boolean bBlessed) {
		this.bBlessed = bBlessed;
	}

	public int getiPrice() {
		return iPrice;
	}

	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}
	
	
}
