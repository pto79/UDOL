package online.dragon.ultima.module.item;

public class UDOLItem {
	private int		iItemID = 0;
	private String 	sName	= "";
	
	private int		iLink 	= 0;	//ID for container
	private int		iRef	= 0;	//for scroll ref to spell
	private int		iAmount	= 0;	//amount = 0 for item which can not overlap. default is 1
	
	private int		iCreationTime = 0;
	private int		iCreationBy	= 0;
	
	private int		iLocationX = 0;	// if item does not in any container, then location is required
	private int		iLocationY = 0;
	
	private boolean	bBlessed = false;
	private int		iPrice = 0;
}
