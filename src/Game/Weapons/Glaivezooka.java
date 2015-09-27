package Game.Weapons;

import Game.Battlecrys.GlaivezookaBC;

public class Glaivezooka extends Weapon {
	
	public Glaivezooka() {
		super("Glaivezooka", 2 , 2, 2);
		battlecrys.add(new GlaivezookaBC());
	}
	
	public Glaivezooka(Weapon w) {
		super(w);
	}

}
