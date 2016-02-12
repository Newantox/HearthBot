package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;

public class LeperGnomeDR extends Deathrattle {

	@Override
	public MyTurnState perform(BoardState oldstate, PlayableCard minion, TargetsType side) {
		if (side.equals(TargetsType.ALLYCHAR)) return (oldstate.getEnemy()).damage(oldstate,2);
		else return (oldstate.getHero()).damage(oldstate,2);
	}

}
