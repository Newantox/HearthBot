package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;

public class ZombieChowDR extends Deathrattle {

	@Override
	public MyTurnState perform(BoardState oldstate, PlayableCard minion, TargetsType side) {
		if (side.equals(TargetsType.ALLYCHAR)) return (oldstate.getEnemy()).heal(oldstate, 5);
		else return (oldstate.getHero()).heal(oldstate, 5);
	}

}
