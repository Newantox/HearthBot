package Game.Cards.Spells.TargettedSpell;

import Game.CardType;
import Game.Character;
import Game.TargetsType;
import Game.Cards.Spells.SpellCard;


public abstract class TargettedSpell extends SpellCard {
	
	public TargettedSpell(String name, int cost) {
		super(name, cost);
	}

	public CardType getType() {
		return CardType.TARGETTEDSPELL;
	}
	
	public abstract TargetsType getTargets();
	
	public void playPrint(Character target) {
		System.out.println("Cast "+getName()+" at "+target.getName());
	}
	
	public String playOutput(Character target) {
		return "Cast "+getName()+" at "+target.getName();
	}

}
