package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Minions.Minion;

public class LeperGnomeDR extends Deathrattle {

	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		if (((Minion) minion).getMyPos() < 7) return (oldstate.getEnemy()).damage(oldstate,2);
		else return (oldstate.getHero()).damage(oldstate,2);
	}

}
