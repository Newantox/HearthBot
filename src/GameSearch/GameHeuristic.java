package GameSearch;

import Game.BoardState;
import Search.Node;
import Search.NodeFunction;

public class GameHeuristic implements NodeFunction {
	public double getValue(Node n) {
		int k = 0;
		BoardState current = (BoardState) n.state;
		for (int i = 0; i<7; i++) {
			if (current.getOppSide()[i] != null) k += current.getOppSide()[i].getHP();
		}
		k += current.getEnemy().getHP();
		for (int i = 0; i<7; i++) {
			if (current.getMySide()[i] != null) k -= current.getMySide()[i].getHP();
		}
		k -= current.getHero().getHP();
		return k;
	}
}
