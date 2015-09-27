package Game.Minions;

public class StormpikeCommando extends Minion {

	public StormpikeCommando(int target) {
		super("Stormpike Commando",target,5,4,2);
		battlecrys.add(new StormpikeCommandoBC());
	}
	
	public StormpikeCommando(Minion m) {
		super(m);
	}
	
}