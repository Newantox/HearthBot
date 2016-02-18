package Game.Cards.Spells.TargettedSpell;

import Game.BoardState;
import Game.Character;
import Game.MyTurnState;
import Game.TargetsType;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;

public class BlessingOfMight extends TargettedSpell {

	public BlessingOfMight() {
		super("Blessing of Might", 1);
	}

	@Override
	public MyTurnState playCard(BoardState oldstate, Character target) {
		return oldstate.applyBuff(((Minion) target).getId(), new AdditiveBuff(-1,3,0,0));
	}

	@Override
	public TargetsType getTargets() {
		return TargetsType.ALLMINIONS;
	}

}
