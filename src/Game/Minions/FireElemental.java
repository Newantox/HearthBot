package Game.Minions;

import Game.Battlecrys.FireElementalBC;

public class FireElemental extends Minion {

	public FireElemental(int target) {
		super("Fire Elemental",target,6,6,5);
		battlecrys.add(new FireElementalBC());
	}
	
	public FireElemental(Minion m) {
		super(m);
	}
	
}