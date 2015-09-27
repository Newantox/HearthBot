package Search;

import Game.BoardState;
import Game.MyTurnState;

public interface Action {
	abstract double cost();
	MyTurnState result(BoardState oldstate);
	abstract void print();

}
