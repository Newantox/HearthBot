package Game;

import Search.Action;
import Search.Printing;
import Search.State;

public class GamePrinting extends Printing {

	@Override
	public void print(Action action) {
		action.print();
		
	}

	@Override
	public void print(State state) {
		state.print();
		
	}

}
