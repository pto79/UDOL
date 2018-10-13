package online.dragon.ultima.module.character;

import online.dragon.ultima.misc.UDOLConst;

public class UDOLAnimal extends UDOLCharacter {
	
	private int	iOwner = 0;
	private int iHunger = 0;
	
	public UDOLAnimal() {
		super();
		// TODO Auto-generated constructor stub
		this.setcBrain(UDOLConst.animal);
	}

	public int getiOwner() {
		return iOwner;
	}

	public void setiOwner(int iOwner) {
		this.iOwner = iOwner;
	}

	public int getiHunger() {
		return iHunger;
	}

	public void setiHunger(int iHunger) {
		this.iHunger = iHunger;
	}
	
}
