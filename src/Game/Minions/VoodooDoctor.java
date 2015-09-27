package Game.Minions;

public class VoodooDoctor extends Minion {

	public VoodooDoctor(int target) {
		super("Voodoo Doctor",target,1,2,1);
		battlecrys.add(new VoodooDoctorBC());
	}
	
	public VoodooDoctor(Minion m) {
		super(m);
	}
	
}