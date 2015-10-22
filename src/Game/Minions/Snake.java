package Game.Minions;

public class Snake extends Minion {

	public Snake() {
		super("Snake",-1,0,1,1);
		setRace(Race.BEAST);
	}
	
	public Snake(Minion m) {
		super(m);
	}
	
}
