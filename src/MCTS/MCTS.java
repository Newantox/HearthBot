package MCTS;

import Game.MyTurnState;
import Main.PlayOut;
import Search.GoalTest;
import Search.Search;
import Search.State;

public class MCTS implements Search {

	private Search search;
	private Search enemysearch;

	public MCTS(Search search, Search enemysearch) {
		this.search = search;
		this.enemysearch = enemysearch;
	}
	
	// Ideas: Make play game version that is enterable from anywhere. Make process loop until we hit "Leaf" to get full move segment

	@Override
	public MCTSNode solution(State initialConfig, GoalTest goalTest,int step) {
		//StopWatch timer = new StopWatch();
		//MCTSNode best = new Node(null,null,null);
		//double bestscore = 1000;
		MCTSNode startNode = new MCTSNode(null,null,initialConfig);
		if (startNode.isLeaf()) {
			((Game.BoardState) initialConfig).print();
			System.out.println(initialConfig.isTurnEnded());
			System.out.println(initialConfig.getApplicableActions().size());
			throw new Error("looking for solution for leaf");
		}
		MCTSNode currentNode = startNode;
		int i=0;
		while (i<1000) {
		//	System.out.println(i);
			currentNode = TreePolicy(currentNode);
			int delta = DefaultPolicy((MyTurnState) currentNode.getState());
			Backup(currentNode,delta);
			i++;
		}
		i=0;
		MCTSNode returnedNode = startNode.selectBestChild();
		if (returnedNode==null) throw new Error("best child is null");
		return returnedNode;
	/*	if (returnedNode.isLeaf()) return returnedNode;
		else {
			MCTSNode temp = solution(returnedNode.getState(),goalTest,step+1);
			return new MCTSNode(returnedNode,temp.getAction(),temp.getState());
		}*/
		
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

	@Override
	public int lastSearch() {
		return -1;
	}

}
