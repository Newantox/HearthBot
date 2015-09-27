package Game.Minions;

public class FrostwolfGrunt extends Minion {

	public FrostwolfGrunt(int target) {
		super("Frostwolf Grunt",target,2,2,2);
		setTaunt(true);
	}
	
	public FrostwolfGrunt(Minion m) {
		super(m);
	}
	
}