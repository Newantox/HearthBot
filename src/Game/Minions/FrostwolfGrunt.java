package Game.Minions;

public class FrostwolfGrunt extends Minion {

	public FrostwolfGrunt() {
		super("Frostwolf Grunt",2,2,2);
		setTaunt(true);
	}
	
	public FrostwolfGrunt(Minion m) {
		super(m);
	}
	
}