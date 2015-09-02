package Game.Minions;

import Game.Battlecrys.AbusiveSergeantBC;

public class AbusiveSergeant extends Minion {

	public AbusiveSergeant(int target) {
		super("Abusive Sergeant",target,1,2,1,1);
		battlecrys.add(new AbusiveSergeantBC());
		
	}
	
	public AbusiveSergeant(Minion m) {
		super(m);
	}
}
