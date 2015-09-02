package Main;

import Game.BoardState;
import Game.GameGoalTest;
import Game.GamePrinting;
import Game.Hand;
import Game.Minions.AcidicSwampOoze;
import Game.Minions.LeperGnome;
import Game.Minions.Wisp;
import Game.Cards.Spells.TargettedSpell.BlessingOfMight;
import Game.Cards.Spells.TargettedSpell.Fireball;
import Game.Cards.Spells.Untargetted.AvengingWrath;
import Game.Cards.Spells.Untargetted.Consecration;
import Game.Heroes.Hero;
import Game.Heroes.Uther;
import Game.Minions.Minion;
import Game.Weapons.FieryWarAxe;
import GameSearch.GameHeuristic;
import Search.AStarFunction;
import Search.BestFirstFrontier;
import Search.Frontier;
import Search.GraphSearch;
import Search.Node;
import Search.Search;

public class Test {

	public static void main(String[] args) {
		GamePrinting Printer = new GamePrinting();
		GameGoalTest goalTest = new GameGoalTest();
		
		Minion[] initOpp = new Minion[7];
		Minion[] initMy = new Minion[7];
		Hand myHand = new Hand();
		
		initOpp[0] = new Wisp(7);
		initOpp[1] = new Wisp(8);
		initOpp[2] = new Minion("Magma Rager",9,3,5,1,1);
		initOpp[3] = new Minion("Argent Squire",10,1,1,1,1);
		//initOpp[3] = new Minion("Token1",10,3,3,3,3);
		initOpp[4] = new Minion("Token2",11,3,3,3,3);
		initOpp[5] = new Minion("Token3",12,3,3,3, 0);

		//initMy[0] = new Wisp(0);
		initMy[0] = new LeperGnome(0);
	//	initMy[2] = new Minion("Wisp",2,0,1,1,1, true, false, false, false, false);
	//	initMy[1] = new Minion("Argent Squire",1,1,1,1,1,true,true,false,false);
		//initMy[3] = new Minion("Argent Squire",3,1,1,1,1,true,true,false,false, false);
		//initMy[3] = new Minion("Abomination",3,5,4,4,4, true, false, false, false);
		//initMy[4] = new Minion("Deathwing",4,10,10,10, 0, true, false, false, false, false);
		
		myHand.add(new Fireball());
		
	
		
		BoardState config = new BoardState(new Uther("Uther",14,20,30,0,10,10,0,null),new Hero("Anduin",15,2,30,0,10,10,0, new FieryWarAxe()),initOpp,initMy,null,myHand);
		Frontier frontier = new BestFirstFrontier(new AStarFunction(new GameHeuristic()));
		
		Search search = new GraphSearch(frontier);
		
		System.out.println("A Star graph search on game");
		System.out.println();
		
		Node solution1 = search.solution(config, goalTest,1);
		Printer.printSolution(solution1);
		System.out.println("Total Nodes: "+(search.lastSearch()));
		System.out.println("Max Nodes: "+(frontier).maxNodes());
		
		
	
	}
}
