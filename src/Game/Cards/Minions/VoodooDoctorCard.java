package Game.Cards.Minions;

import Game.Minions.VoodooDoctor;
import Game.Minions.Minion;

public class VoodooDoctorCard extends MinionCard {

	public VoodooDoctorCard() {
		super("Voodoo Doctor",1);
	}
	
	public Minion makeNew(int target) {
		return new VoodooDoctor(target);
	}

}