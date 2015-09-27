package Game.Minions;

public class ChillwindYeti extends Minion {

	public ChillwindYeti(int target) {
		super("Chillwind Yeti",target,4,4,5);
	}
	
	public ChillwindYeti(Minion m) {
		super(m);
	}
	
}