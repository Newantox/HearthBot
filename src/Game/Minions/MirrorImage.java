package Game.Minions;

public class MirrorImage extends Minion {
	
	public MirrorImage(int target) {
		super("Mirror Image",target,0,0,2);
		setTaunt(true);
	}
	
	public MirrorImage(Minion m) {
		super(m);
	}

}
