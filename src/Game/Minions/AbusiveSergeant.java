package Game.Minions;

import Game.Battlecrys.AbusiveSergeantBC;

public class AbusiveSergeant extends Minion {

	public AbusiveSergeant() {
		super("Abusive Sergeant",1,2,1);
		battlecrys.add(new AbusiveSergeantBC());
	}
	
	public AbusiveSergeant(Minion m) {
		super(m);
	}
}
