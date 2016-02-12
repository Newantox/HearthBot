package Game.Minions;

public class SouthseaDeckhand extends Minion {

	public SouthseaDeckhand() {
		super("Southsea Deckhand",1,2,1);
		setRace(Race.PIRATE);
	}
	
	public SouthseaDeckhand(Minion m) {
		super(m);
	}

}
