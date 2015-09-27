package Game.Minions;

public class IronbarkProtector extends Minion {

	public IronbarkProtector(int target) {
		super("Ironbark Protector",target,8,8,8);
		setTaunt(true);
	}
	
	public IronbarkProtector(Minion m) {
		super(m);
	}
	
}
