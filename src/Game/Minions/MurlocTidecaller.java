package Game.Minions;

import Game.SummonEffects.MurlocTidecallerSE;

public class MurlocTidecaller extends Minion {
	
	public MurlocTidecaller(int target) {
		super("Murloc Tidecaller",target,1,1,2);
		setRace(Race.MURLOC);
		addSummonEffect(new MurlocTidecallerSE());
	}
	
	public MurlocTidecaller(Minion m) {
		super(m);
	}

}
