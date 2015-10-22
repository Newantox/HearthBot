package Game.Cards.Spells.Untargetted;

import Game.BoardState;
import Game.Character;
import Game.MyTurnState;
import Game.TargetsType;

public class AvengingWrath extends UntargettedSpell {
	
	public AvengingWrath() {
		super("Avenging Wrath", 6);
	}

	private int damage = 8;

	@Override
	public MyTurnState playCard(BoardState oldstate, Character target) {
	
		MyTurnState tempstate = oldstate;
		for (int i = 0; i<damage+oldstate.getTotalAlliedSpellDamage(); i++) {
			tempstate = tempstate.damageRandomHittable(TargetsType.ENEMYCHAR,1);
			
		}
		return tempstate;
	}

}
