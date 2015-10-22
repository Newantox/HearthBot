package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;

public class AcidicSwampOozeBC extends Battlecry {

		@Override
		public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
			return (oldstate.getEnemy()).destroyWeapon(oldstate);
		}

	}

