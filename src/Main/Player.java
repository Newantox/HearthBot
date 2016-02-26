package Main;

import Game.GameGoalTest;
import Game.MyTurnState;
import Game.Heroes.Hero;
import Search.Node;
import Search.Search;
import Search.State;

public class Player {
	
	GameGoalTest test = new GameGoalTest();
	
	//Frontier frontier = new BestFirstFrontier(new AStarFunction(new GameHeuristic()));
	
	//Search search = new GraphSearch(frontier);
	
	Search search;

	private Hero initialHero;
	
	public Player(Search search, Hero initialHero) {
		this.initialHero = initialHero;
		this.search = search;
	}
	
	public Node getSolution(State state) {
		return search.solution(state, test, 1);
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
