package Game.Minions;

import Game.Battlecrys.ChooseTargetDamageBC;

public class ElvenArcher extends Minion {

	public ElvenArcher() {
		super("Elven Archer",1,1,1);
		battlecrys.add(new ChooseTargetDamageBC(1));;
	}
	
	public ElvenArcher(Minion m) {
		super(m);
	}
	
}