package Game;

import Search.Action;

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
	public MyTurnState result(BoardState oldstate) {
		return null;
	}

	@Override
	public void print() {
		System.out.println("Random case "+index);

	}
	
	public String output() {
		return "";
	}

}
