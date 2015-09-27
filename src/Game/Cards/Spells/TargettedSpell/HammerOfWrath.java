package Game.Cards.Spells.TargettedSpell;

import Game.BoardState;
import Game.MyTurnState;
import Game.TargetsType;

public class HammerOfWrath extends TargettedSpell {
	
	public HammerOfWrath() {
		super("Hammer of Wrath", 4);
	}

	private int damage = 3;

	@Override
	public MyTurnState playCard(BoardState oldstate, int target) {
		MyTurnState tempstate = oldstate.spellDamageTarget(target, damage);
		
		return tempstate.drawCard();
	}

	@Override
	public TargetsType getTargets() {
		return TargetsType.ALL;
	}
}
