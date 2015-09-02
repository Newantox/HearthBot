package Game.Cards.Spells.TargettedSpell;

import Game.BoardState;
import Game.TargetsType;
import Search.State;

public class ArmorPlating extends TargettedSpell {

	public String getName() {
		return "Armor Plating";
	}

	@Override
	public int getCost() {
		return 1;
	}

	@Override
	public State playCard(BoardState oldstate, int target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TargetsType getTargets() {
		return TargetsType.ALLMINIONS;
	}

}
