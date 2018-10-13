package online.dragon.ultima.misc;

public class UDOLConst {

	public UDOLConst() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// system related
	public static final int saveWorld 	= 1;
	public static final int getWorld 	= 2;
	
	// account related
	public static final int lvlPlayer 	= 11;
	public static final int lvlAdmin 	= 12;
	public static final int lvlOwner 	= 13;
	
	public static final int pending 	= 14;
	public static final int active 		= 15;
	public static final int suspend 	= 16;
	public static final int block		= 17;	
	
	public static final int register	= 17;
	public static final int login		= 18;
	public static final int logout		= 19;
	
	public static final int saveAccount	= 20;
	public static final int getAccount	= 21;
	
	//player action
	public static final int move 		= 1000;
	public static final int talk 		= 2000;
	public static final int attack 		= 3000;
	public static final int useItem 	= 4000;
	public static final int useSkill 	= 5000;
	public static final int castSpell 	= 6000;
	public static final int openBag 	= 7000;
	public static final int readMap 	= 8000;
	public static final int status 		= 9000;
	public static final int quest 		= 10000;
	public static final int help 		= 11000;
	
	//direction
	public static final int up 			= 1001;
	public static final int down		= 1002;
	public static final int left 		= 1003;
	public static final int right 		= 1004;
	
	// npc AI
	public static final int normal 		= 1005;
	public static final int animal 		= 1006;
	public static final int monster 	= 1007;
	public static final int vendor 		= 1008;
}
