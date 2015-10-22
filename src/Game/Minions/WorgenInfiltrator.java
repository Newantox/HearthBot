package Game.Minions;

public class WorgenInfiltrator extends Minion {
	
	public WorgenInfiltrator() {
		super("Worgen Infiltrator",-1,1,2,1);
		setStealth(true);
	}
	
	public WorgenInfiltrator(Minion m) {
		super(m);
	}

}
