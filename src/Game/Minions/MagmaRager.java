package Game.Minions;

public class MagmaRager extends Minion {

	public MagmaRager(int target) {
		super("Magma Rager",target,3,5,1);
	}
	
	public MagmaRager(Minion m) {
		super(m);
	}
	
}