package online.dragon.ultima.module.character;

import online.dragon.ultima.misc.UDOLConst;

public class UDOLPlayer extends UDOLCharacter {
	
	private int	iLevel = UDOLConst.lvlPlayer;

	public UDOLPlayer() {
		super();
		// TODO Auto-generated constructor stub
		this.setcBrain(UDOLConst.normal);
	}

	public int getiLevel() {
		return iLevel;
	}

	public void setiLevel(int iLevel) {
		this.iLevel = iLevel;
	}

}
