package Game.Battlecrys;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public class AcidicSwampOozeBC extends Battlecry {

		@Override
		public State perform(Minion minion, BoardState oldstate) {
			return (oldstate.getEnemy()).destroyWeapon(oldstate);
		}

	}

