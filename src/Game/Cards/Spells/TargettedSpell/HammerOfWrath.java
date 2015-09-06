package Game.Cards.Spells.TargettedSpell;

import Game.BoardState;
import Game.TargetsType;
import Search.State;

public class HammerOfWrath extends TargettedSpell {
	private int damage = 3;
	private int cost = 4;

	@Override
	public String getName() {
		return "Hammer of Wrath";
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public State playCard(BoardState oldstate, int target) {
		State tempstate = oldstate.damageTarget(target, damage);
		
		return tempstate.drawCard();
	}

	@Override
	public TargetsType getTargets() {
		return TargetsType.ALL;
	}
}
