package Game.Minions;

public class WrathOfAirTotem extends Minion {

	public WrathOfAirTotem(int target) {
		super("Snake",target,1,0,2);
		setRace(Race.TOTEM);
		setSpelldamage(1);
	}
	
	public WrathOfAirTotem(Minion m) {
		super(m);
	}
	
}