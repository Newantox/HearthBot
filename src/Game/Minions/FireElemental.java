package Game.Minions;

import Game.Battlecrys.ChooseTargetDamageBC;

public class FireElemental extends Minion {

	public FireElemental() {
		super("Fire Elemental",6,6,5);
		battlecrys.add(new ChooseTargetDamageBC(3));
	}
	
	public FireElemental(Minion m) {
		super(m);
	}
	
}