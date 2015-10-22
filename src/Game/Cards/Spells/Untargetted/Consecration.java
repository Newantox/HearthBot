package Game.Cards.Spells.Untargetted;

import java.util.ArrayList;

import Game.BoardState;
import Game.Character;
import Game.MyTurnState;
import Game.TargetsType;
import Game.Minions.Minion;

public class Consecration extends UntargettedSpell {
	
	public Consecration() {
		super("Consecration", 4);
	}

	int damage = 2;

	@Override
	public MyTurnState playCard(BoardState oldstate, Character target) {
		return oldstate.simultaneousSpellDamage(TargetsType.ENEMYCHAR, damage, new ArrayList<Minion>());
	}

}
