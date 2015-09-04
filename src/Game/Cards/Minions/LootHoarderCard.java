package Game.Cards.Minions;


import Game.Minions.LootHoarder;
import Game.Minions.Minion;

public class LootHoarderCard extends MinionCard {

	public LootHoarderCard() {
		super("Loot Hoarder",1);
	}	
	
	public Minion makeNew(int target) {
		return new LootHoarder(target);
	}
}