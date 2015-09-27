package Game.Minions;

public class FrostwolfWarlord extends Minion {

	public FrostwolfWarlord(int target) {
		super("Frostwolf Warlord",target,5,4,4);
		battlecrys.add(new FrostwolfWarlordBC());
	}
	
	public FrostwolfWarlord(Minion m) {
		super(m);
	}
	
}
