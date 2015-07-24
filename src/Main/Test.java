package Main;

import Game.BoardState;
import Game.Card;
import Game.GameGoalTest;
import Game.GamePrinting;
import Game.Weapon;
import Game.Minions.AcidicSwampOoze;
import Game.Minions.Wisp;
import Game.Cards.Spells.BlessingOfMight;
import Game.Cards.Spells.Consecration;
import Game.Cards.Spells.Fireball;
import Game.Cards.Weapons.FieryWarAxe;
import Game.Heroes.Hero;
import Game.Heroes.Uther;
import Game.Minions.Minion;
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
		Card[] myHand = new Card[10];
		
		initOpp[0] = new Wisp(7);
		//initOpp[1] = new Wisp(8);
	//	initOpp[2] = new Minion("Magma Rager",9,3,5,1,1, false, false, false, false);
		//initOpp[3] = new Minion("Argent Squire",10,1,1,1,1,false,true,false,false);
	//	initOpp[3] = new Minion("Token1",10,3,3,3,3, false, false, false, false);
		//initOpp[4] = new Minion("Token2",11,3,3,3,3, false, false, false, false);
		//initOpp[5] = new Minion("Token3",3,3,3,3);

		initMy[0] = new Wisp(0);
		//initMy[1] = new Minion("Silverback Patriach",1,3,1,4,4, true, false, false, false);
	//	initMy[2] = new Minion("Wisp",2,0,1,1,1, true, false, false, false);
	//	initMy[1] = new Minion("Argent Squire",1,1,1,1,1,true,true,false,false);
	//	initMy[3] = new Minion("Argent Squire",3,1,1,1,1,true,true,false,false);
		//initMy[3] = new Minion("Abomination",3,5,4,4,4, true, false, false, false);
	//	initMy[4] = new Minion("Deathwing",1,10,10,10);
		
		//myHand[0] = new Wisp();
		//myHand[2] = new AcidicSwampOoze();
	//	myHand[0] = new Fireball();
		//myHand[1] = new BlessingOfMight();
	//	myHand[3] = new Consecration();
		//myHand[3] = new FieryWarAxe();
		
	
		
		BoardState config = new BoardState(new Uther("Garrosh",20,0,null),new Hero("Anduin",30,0,new Weapon("Fiery War Axe",3,2)),10,10,initOpp,initMy,null,myHand);
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
