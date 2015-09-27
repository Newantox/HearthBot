package Game.Minions;

public class WorgenInfiltrator extends Minion {
	
	public WorgenInfiltrator(int target) {
		super("Worgen Infiltrator",target,1,2,1);
		setStealth(true);
	}
	
	public WorgenInfiltrator(Minion m) {
		super(m);
	}

}
