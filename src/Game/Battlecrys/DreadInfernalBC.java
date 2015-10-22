package Game.Battlecrys;

import java.util.ArrayList;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;
import Game.Minions.Minion;

public class DreadInfernalBC extends Battlecry {
	
	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		ArrayList<Minion> exception = new ArrayList<Minion>();
		exception.add((Minion) minion);
		
		return oldstate.simultaneousDamage(TargetsType.ALL,1,exception);
	}

}
