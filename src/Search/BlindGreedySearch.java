package Search;

public class BlindGreedySearch implements Search {
	
	private Frontier frontier;

	public BlindGreedySearch(Frontier frontier) {
		this.frontier = frontier;
	}

	@Override
	public Node solution(State initialConfig, GoalTest goalTest,int step) {
		State currentState = initialConfig;
	
		frontier.add(new Node(null,null,currentState));
		
		Node current = new Node(null,null,currentState);
		
		while(!currentState.isTurnEnded()) {
			Node n = frontier.remove();
			if ((n.state).isGameWon()) {frontier.clear(); return n;}
			frontier.clear();
			
			if (n.state.getApplicableActions().isEmpty()) return n;
			
			for (Action action : n.state.getApplicableActions()) {
				//if (action==null) {frontier.clear(); return current;}
				State newState = n.state.getActionResult(action);
				frontier.add(new Node(n, action, newState));
			}
			current = n;
			if (current.state.isTurnEnded()) return current;
		}
		frontier.clear();
		return current;

	}

}
