package Game.Cards.Spells.TargettedSpell;

import Game.BoardState;
import Game.TargetsType;
import Search.State;

public class Fireball extends TargettedSpell {
	
	private int damage = 6;
	private int cost = 4;

	@Override
	public String getName() {
		return "Fireball";
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public State playCard(BoardState oldstate, int target) {
		return oldstate.damageTarget(target, damage);
	}

	@Override
	public TargetsType getTargets() {
		return TargetsType.ALL;
	}
}
