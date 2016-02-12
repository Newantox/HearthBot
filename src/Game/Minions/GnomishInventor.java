package Game.Minions;

import Game.Battlecrys.GnomishInventorBC;

public class GnomishInventor extends Minion {

	public GnomishInventor() {
		super("Gnomish Inventor",4,2,4);
		battlecrys.add(new GnomishInventorBC());
	}
	
	public GnomishInventor(Minion m) {
		super(m);
	}
	
}