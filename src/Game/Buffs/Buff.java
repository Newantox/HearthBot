package Game.Buffs;

import Game.Card;
import Game.CardType;
import Game.Minions.Minion;

public abstract class Buff implements Card {

	@Override
	public String getName() {
		return "Buff";
	}

	@Override
	public CardType getType() {
		return CardType.BUFF;
	}
	
	public abstract double getID();
	
	public abstract void apply(Minion minion);
	
	public abstract void remove(Minion minion);

}
