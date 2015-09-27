package Game.Inspires;

import Game.BoardState;
import Game.MyTurnState;
import Game.ViewType;

public abstract class WeaponInspire extends Inspire {

public MyTurnState trigger(MyTurnState oldstate) {
	return oldstate.performInspire(this);
}

public abstract MyTurnState perform(BoardState oldstate);

}
