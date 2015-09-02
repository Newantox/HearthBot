package Game;

import Search.Action;
import Search.State;

public class IndexAction implements Action {
	
	private int index;
	
	public IndexAction(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}

	@Override
	public double cost() {
		return 0;
	}

	@Override
	public State result(BoardState oldstate) {
		return null;
	}

	@Override
	public void print() {
		System.out.println("Random case "+index);

	}

}
