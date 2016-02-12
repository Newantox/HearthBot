package Game.Cards.Spells.TargettedSpell;

import Game.BoardState;
import Game.Character;
import Game.MyTurnState;
import Game.TargetsType;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;

public class ArmorPlating extends TargettedSpell {

	public ArmorPlating() {
		super("Armor Plating", 1);
	}

	@Override
	public MyTurnState playCard(BoardState oldstate, Character target) {
		return oldstate.applyBuff(((Minion) target).getId(),((Minion) target).getName(), new AdditiveBuff(-1,0,1,0));
	}

	@Override
	public TargetsType getTargets() {
		return TargetsType.ALLMINIONS;
	}

}
