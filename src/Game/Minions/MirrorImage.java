package Game.Minions;

public class MirrorImage extends Minion {
	
	public MirrorImage() {
		super("Mirror Image",-1,0,0,2);
		setTaunt(true);
	}
	
	public MirrorImage(Minion m) {
		super(m);
	}

}
