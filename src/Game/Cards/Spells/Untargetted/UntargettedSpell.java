package Game.Cards.Spells.Untargetted;

import Game.CardType;
import Game.Cards.Spells.SpellCard;

public abstract class UntargettedSpell extends SpellCard {

	public UntargettedSpell(String name, int cost) {
		super(name, cost);
	}

	public CardType getType() {
		return CardType.UNTARGETTEDSPELL;
	}
}
