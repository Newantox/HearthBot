package Game.Minions;

public class SilverbackPatriarch extends Minion {

	public SilverbackPatriarch() {
		super("Silverback Patriarch",-1,3,1,4);
		setRace(Race.BEAST);
		setTaunt(true);
	}
	
	public SilverbackPatriarch(Minion m) {
		super(m);
	}
	
}