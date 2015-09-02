package Game.Cards.Spells.TargettedSpell;

import Game.BoardState;
import Game.TargetsType;
import Game.Minions.Minion;
import Search.State;

public class BlessingOfMight extends TargettedSpell {

	int cost = 1;

	@Override
	public String getName() {
		return "Blessing of Might";
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public State playCard(BoardState oldstate, int target) {
		Minion defender;
		if (target<7) defender = oldstate.getMySide()[target];
		else defender = oldstate.getOppSide()[target];
		return defender.changeAtk(oldstate, 3);
	}

	@Override
	public TargetsType getTargets() {
		return TargetsType.ALLMINIONS;
	}

}
