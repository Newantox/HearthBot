package Game.Minions;

public class SilverbackPatriarch extends Minion {

	public SilverbackPatriarch(int target) {
		super("Silverback Patriarch",target,3,1,4);
		setRace(Race.BEAST);
		setTaunt(true);
	}
	
	public SilverbackPatriarch(Minion m) {
		super(m);
	}
	
}