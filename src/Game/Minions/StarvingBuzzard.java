package Game.Minions;

import Game.SummonEffects.StarvingBuzzardSE;

public class StarvingBuzzard extends Minion {

	public StarvingBuzzard() {
		super("Starving Buzzard",-1,5,3,2);
		setRace(Race.BEAST);
		addSummonEffect(new StarvingBuzzardSE());
	}
	
	public StarvingBuzzard(Minion m) {
		super(m);
	}
	
}