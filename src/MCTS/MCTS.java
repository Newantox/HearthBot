package MCTS;

import Game.MyTurnState;
import Main.PlayOut;
import Search.GoalTest;
import Search.Search;
import Search.State;

public class MCTS implements Search {

	private Search search;
	private Search enemysearch;
	private boolean bias;

	public MCTS(Search search, Search enemysearch, boolean bias) {
		this.search = search;
		this.enemysearch = enemysearch;
		this.bias = bias;
	}
	
	// Ideas: Make play game version that is enterable from anywhere. Make process loop until we hit "Leaf" to get full move segment

	@Override
	public MCTSNode solution(State initialConfig, GoalTest goalTest,int step) {
		//StopWatch timer = new StopWatch();
		//MCTSNode best = new Node(null,null,null);
		//double bestscore = 1000;
		MCTSNode startNode = new MCTSNode(null,null,initialConfig,bias);
		if (startNode.isLeaf()) {
			((Game.BoardState) initialConfig).print();
			System.out.println(initialConfig.isTurnEnded());
			System.out.println(initialConfig.getApplicableActions(true).size());
			throw new Error("looking for solution for leaf");
		}
		MCTSNode currentNode = startNode;
		
		int i=0;
		while (i<20000) {
			currentNode = TreePolicy(startNode);
			int delta = DefaultPolicy((MyTurnState) currentNode.getState());
			Backup(currentNode,delta);
			i++;
		}
		i=0;
		
		/*if (matchingBestVisited) {
			while (!startNode.selectBestChild().equals(startNode.mostVisitedChild())) {
				currentNode = TreePolicy(currentNode);
				int delta = DefaultPolicy((MyTurnState) currentNode.getState());
				Backup(currentNode,delta);
				i++;
			}
		}*/
		
		currentNode = startNode.selectBestChild();
		if (currentNode==null) throw new Error("best child is null");
		if (currentNode.getAction()==null) throw new Error("best action is null");
		return currentNode;
		
	}
	
	private MCTSNode TreePolicy(MCTSNode n) {
		while (!n.isLeaf()) {
			if (!n.fullyExpanded()) {
				return n.Expand();
			}
			else {
				n = n.selectBestChild();
			}
		}
		return n;
	}
	
	private int DefaultPolicy(MyTurnState s) {
	//	System.out.println("Entering playout");
		PlayOut playout = new PlayOut(s,search,enemysearch);
		return playout.play();
	}
	
	private void Backup(MCTSNode n, int delta) {
		if (n!=null) {
			n.setVisits(n.getVisits()+1);
			n.setEstWins(n.getEstWins()+delta);
			Backup(n.getParent(),delta);
		}
	}

}
