package Game.Minions;

import Game.Battlecrys.NoviceEngineerBC;

public class NoviceEngineer extends Minion {

	public NoviceEngineer(int target) {
		super("Novice Engineer",target,2,1,1);
		battlecrys.add(new NoviceEngineerBC());
	}
	
	public NoviceEngineer(Minion m) {
		super(m);
	}
	
}