package Game.Minions;

public class StoneclawTotem extends Minion {

	public StoneclawTotem(int target) {
		super("Stoneclaw Totem",target,1,0,2);
		setRace(Race.TOTEM);
		setTaunt(true);
	}
	
	public StoneclawTotem(Minion m) {
		super(m);
	}
	
}