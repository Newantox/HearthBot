package Game.Minions;

public class WarGolem extends Minion {

	public WarGolem(int target) {
		super("War Golem",target,7,7,7);
	}
	
	public WarGolem(Minion m) {
		super(m);
	}
	
}
