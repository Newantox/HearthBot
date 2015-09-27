package Game.Minions;

import Game.Battlecrys.ElvenArcherBC;

public class ElvenArcher extends Minion {

	public ElvenArcher(int target) {
		super("Elven Archer",target,1,1,1);
		battlecrys.add(new ElvenArcherBC());;
	}
	
	public ElvenArcher(Minion m) {
		super(m);
	}
	
}