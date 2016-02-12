package Game.Minions;

public class StoneclawTotem extends Minion {

	public StoneclawTotem() {
		super("Stoneclaw Totem",1,0,2);
		setRace(Race.TOTEM);
		setTaunt(true);
	}
	
	public StoneclawTotem(Minion m) {
		super(m);
	}
	
}