package GameSearch;

import Game.BoardState;
import Game.ChoiceState;
import Game.RandomState;
import Game.StateProbabilityPair;
import Search.Node;
import Search.NodeFunction;
import Search.State;

public class GameHeuristic implements NodeFunction {
	public double getValue(Node n) {
		State state = n.state;
		switch (state.getStatetype()) {
			case BOARD:
				int k = 0;
				BoardState current = (BoardState) state;
				for (int i = 0; i<7; i++) {
					if (current.getOppSide()[i] != null) {
						if (current.getOppSide()[i].isDivineShield()) k += 1.6*current.getOppSide()[i].getHP();
						else k += current.getOppSide()[i].getHP();
						k += 0.8*current.getOppSide()[i].getAtk();
					}
				}
				k += 0.3*current.getEnemy().getHP();
				for (int i = 0; i<7; i++) {
					if (current.getMySide()[i] != null) {
						if (current.getMySide()[i].isDivineShield()) k -= current.getMySide()[i].getHP();
						else k -= 0.6*current.getMySide()[i].getHP();
						k -= 0.4*current.getMySide()[i].getAtk();
					}
				}
				k -= 0.1*current.getHero().getHP();
				return k;
				
			case RANDOM:
				int j = 0;
				RandomState currentrand = (RandomState) state;
				for (int i = 0; i < currentrand.getSize(); i++) {
					StateProbabilityPair pair = currentrand.getPair(i);
					Node node = new Node(n.parent,n.action,pair.getState());
					j += pair.getProbability()*getValue(node);
				}
				return (double) j / currentrand.getSize();
			
			case CHOICE:
				int m = 0;
				BoardState actualstate = ((ChoiceState) state).getState();
				for (int i = 0; i<7; i++) {
					if (actualstate.getOppSide()[i] != null) m += actualstate.getOppSide()[i].getHP();
				}
				m += actualstate.getEnemy().getHP();
				for (int i = 0; i<7; i++) {
					if (actualstate.getMySide()[i] != null) m -= 0.9*actualstate.getMySide()[i].getHP();
				}
				m -= 0.2*actualstate.getHero().getHP();
				return m;
				
		default:
			return 1000;
				
		}
	}
}
