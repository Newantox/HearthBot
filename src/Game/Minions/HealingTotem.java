package Game.Minions;

public class HealingTotem extends Minion {

	public HealingTotem(int target) {
		super("Healing Totem",target,1,0,2);
		setRace(Race.TOTEM);
		addEndTurnEffect(new HealingTotemET());
	}
	
	public HealingTotem(Minion m) {
		super(m);
	}
	
}