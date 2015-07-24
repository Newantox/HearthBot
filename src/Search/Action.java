package Search;

import Game.BoardState;

public interface Action {
	abstract double cost();
	State result(BoardState oldstate);
	abstract void print();
}
