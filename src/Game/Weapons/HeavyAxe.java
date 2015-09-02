package Game.Weapons;

public class HeavyAxe extends Weapon {

	public HeavyAxe() {
		super("Heavy Axe", 1, 1, 3);
	}
	
	public HeavyAxe(Weapon w) {
		super(w);
	}

}