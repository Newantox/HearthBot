package Game.Cards.Spells.TargettedSpell;

import Game.BoardState;
import Game.MyTurnState;
import Game.TargetsType;
import Game.Minions.Minion;

public class ArmorPlating extends TargettedSpell {

	public ArmorPlating() {
		super("Armor Plating", 1);
	}

	@Override
	public MyTurnState playCard(BoardState oldstate, int target) {
		Minion defender;
		if (target<7) defender = (oldstate.getMySide()).get(target);
		else defender = (oldstate.getOppSide()).get(target);
		return defender.changeAtkHP(oldstate,0,1);
	}

	@Override
	public TargetsType getTargets() {
		return TargetsType.ALLMINIONS;
	}

}
