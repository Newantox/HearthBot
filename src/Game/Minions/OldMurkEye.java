package Game.Minions;

import Game.Auras.OldMurkEyeAura;

public class OldMurkEye extends Minion {

	public OldMurkEye() {
		super("Old Murk-Eye",-1,4,2,4);
		setRace(Race.MURLOC);
		setCharge(true);
		auras.add(new OldMurkEyeAura());
	}
	
	public OldMurkEye(Minion m) {
		super(m);
	}
	
}
