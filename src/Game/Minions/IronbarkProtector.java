package Game.Minions;

public class IronbarkProtector extends Minion {

	public IronbarkProtector() {
		super("Ironbark Protector",-1,8,8,8);
		setTaunt(true);
	}
	
	public IronbarkProtector(Minion m) {
		super(m);
	}
	
}
