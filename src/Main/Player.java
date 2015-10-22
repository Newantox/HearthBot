package Main;

import Game.GameGoalTest;
import Game.MyTurnState;
import Game.Heroes.Hero;
import GameSearch.GameHeuristic;
import Search.AStarFunction;
import Search.BestFirstFrontier;
import Search.Frontier;
import Search.GraphSearch;
import Search.Node;
import Search.RandomPlayout;
import Search.Search;
import Search.State;

public class Player {
	
	GameGoalTest test = new GameGoalTest();
	
	//Frontier frontier = new BestFirstFrontier(new AStarFunction(new GameHeuristic()));
	
	//Search search = new GraphSearch(frontier);
	
	Search search = new RandomPlayout();

	private Hero initialHero;
	
	public Player(Hero initialHero) {
		this.initialHero = initialHero;
	}
	
	public Node getSolution(State mulliganStates) {
		return search.solution(mulliganStates, test, 1);
	}
	
	public boolean goalTest(MyTurnState currentState) {
		return test.isGoal(currentState);
	}

	public Hero getInitialHero() {
		return initialHero;
	}

	public void setHero(Hero initialHero) {
		this.initialHero = initialHero;
	}
	
}
