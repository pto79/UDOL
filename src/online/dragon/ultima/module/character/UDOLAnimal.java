package online.dragon.ultima.module.character;

import online.dragon.ultima.module.player.UDOLSkill;

public class UDOLAnimal extends UDOLCharacter {
	
	private int	iOwner = 0;
	private int iHunger = 0;
	
	public UDOLAnimal() {
		super();
		// TODO Auto-generated constructor stub
		this.setcBrain(UDOLBrain.animal);
	}
}
