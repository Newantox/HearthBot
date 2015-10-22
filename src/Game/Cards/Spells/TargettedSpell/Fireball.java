package Game.Cards.Spells.TargettedSpell;

import Game.BoardState;
import Game.Character;
import Game.MyTurnState;
import Game.TargetsType;

public class Fireball extends TargettedSpell {
	
	public Fireball() {
		super("Fireball", 4);
	}

	private int damage = 6;

	@Override
	public MyTurnState playCard(BoardState oldstate, Character target) {
		return oldstate.spellDamageTarget(target, damage);
	}

	@Override
	public TargetsType getTargets() {
		return TargetsType.ALL;
	}
}
