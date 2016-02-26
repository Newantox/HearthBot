package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import GUI.UI;
import Game.BoardState;
import Game.Card;
import Game.Deck;
import Game.GameGoalTest;
import Game.GamePlay;
import Game.GamePrinting;
import Game.Hand;
import Game.MulliganState;
import Game.MyTurnState;
import Game.TargetsType;
import Game.ViewType;
import Game.Minions.AbusiveSergeant;
import Game.Minions.AcidicSwampOoze;
import Game.Minions.ArgentSquire;
import Game.Minions.DragonlingMechanic;
import Game.Minions.GrimscaleOracle;
import Game.Minions.KnifeJuggler;
import Game.Minions.LeperGnome;
import Game.Minions.LootHoarder;
import Game.Minions.MechanicalDragonling;
import Game.Minions.Murloc;
import Game.Minions.MurlocTidecaller;
import Game.Minions.MurlocWarleader;
import Game.Minions.NoviceEngineer;
import Game.Minions.Ragnaros;
import Game.Minions.ShieldedMinibot;
import Game.Minions.SouthseaDeckhand;
import Game.Minions.Wisp;
import Game.Minions.Wolfrider;
import Game.Actions.EndTurn;
import Game.Actions.PlayCard;
import Game.Actions.SwapCards;
import Game.Cards.Spells.TargettedSpell.BlessingOfMight;
import Game.Cards.Spells.TargettedSpell.Fireball;
import Game.Cards.Spells.TargettedSpell.HammerOfWrath;
import Game.Cards.Spells.Untargetted.AvengingWrath;
import Game.Cards.Spells.Untargetted.Consecration;
import Game.Cards.Spells.Untargetted.DivineFavour;
import Game.Cards.Spells.Untargetted.Equality;
import Game.Cards.Spells.Untargetted.MusterForBattle;
import Game.Cards.Spells.Untargetted.TheCoin;
import Game.Heroes.Hero;
import Game.Heroes.Uther;
import Game.Minions.Minion;
import Game.SummonEffects.KnifeJugglerSE;
import Game.SummonEffects.SummonEffect;
import Game.SummonEffects.SwordOfJusticeSE;
import Game.Weapons.FieryWarAxe;
import Game.Weapons.SwordOfJustice;
import Game.Weapons.TruesilverChampion;
import MCTS.MCTS;
import MCTS.MCTSNode;
import Search.Action;
import Search.BestFirstFrontier;
import Search.BlindGreedySearch;
import Search.Frontier;
import Search.GraphSearch;
import Search.Node;
import Search.RandomPlayout;
import Search.Search;
import Search.State;

public class Test {

	public static void main(String[] args) {
		GamePrinting Printer = new GamePrinting();
		GameGoalTest goalTest = new GameGoalTest();
		
	//	Frontier frontier = new BestFirstFrontier(new AStarFunction(new GameHeuristic()),0.5,0.5);
	//	Frontier frontier2 = new BestFirstFrontier(new AStarFunction(new GameHeuristic()),0.5,0.5);
	//	Frontier frontier3 = new BestFirstFrontier(new AStarFunction(new GameHeuristic()),0.5,0.5);
		
	//	Search search = new GraphSearch(frontier);
		
		//initOpp.add(new LootHoarder(7));
		//initMy.add(new MurlocTidecaller(0));
		
		//positionsInPlayOrder.add(7);
		//positionsInPlayOrder.add(0);
		
		//initMy.get(0).setReady(true);
	//	initMy[1].setReady(true);
	//	initMy[2].setReady(true);
	//	initMy[3].setReady(true);
	//	initMy[4].setReady(true);
	//	initMy[5].setReady(true);
	//  initMy[6].setReady(true);
		
	//	myHand.add(new ArgentSquire());
	//	myHand.add(new Equality());
	//	myHand.add(new AbusiveSergeant());
	//	myHand.add(new DivineFavour());
	//	myHand.add(new TheCoin());
	//	myHand.add(new MurlocTidehunter());
	//	myHand.add(new MurlocScout());
	//	myHand.add(new MurlocWarleader());
	//	myHand.add(new Murloc());
	//	myHand.add(new GrimscaleOracle());
		
		Deck myDeck = new Deck();
		myDeck = myDeck.add(new BlessingOfMight(),2);
		myDeck = myDeck.add(new Equality(),2);
		myDeck = myDeck.add(new ShieldedMinibot(),2);
		myDeck = myDeck.add(new SwordOfJustice(),1);
		myDeck = myDeck.add(new MusterForBattle(),2);
		myDeck = myDeck.add(new TruesilverChampion(),2);
		myDeck = myDeck.add(new Consecration(),2);
		myDeck = myDeck.add(new HammerOfWrath(),2);
		myDeck = myDeck.add(new AbusiveSergeant(),2);
		myDeck = myDeck.add(new ArgentSquire(),2);
		myDeck = myDeck.add(new LeperGnome(),2);
		myDeck = myDeck.add(new SouthseaDeckhand(),2);
		myDeck = myDeck.add(new KnifeJuggler(),2);
		myDeck = myDeck.add(new LootHoarder(),1);
		myDeck = myDeck.add(new Wolfrider(), 1);
		myDeck = myDeck.add(new NoviceEngineer(), 2);
		myDeck = myDeck.add(new DragonlingMechanic(), 1);
		
		Deck enemyDeck = new Deck();
		enemyDeck = enemyDeck.add(new BlessingOfMight(),2);
		enemyDeck = enemyDeck.add(new Equality(),2);
		enemyDeck = enemyDeck.add(new ShieldedMinibot(),2);
		enemyDeck = enemyDeck.add(new SwordOfJustice(),1);
		enemyDeck = enemyDeck.add(new MusterForBattle(),2);
		enemyDeck = enemyDeck.add(new TruesilverChampion(),2);
		enemyDeck = enemyDeck.add(new Consecration(),2);
		enemyDeck = enemyDeck.add(new HammerOfWrath(),2);
		enemyDeck = enemyDeck.add(new AbusiveSergeant(),2);
		enemyDeck = enemyDeck.add(new ArgentSquire(),2);
		enemyDeck = enemyDeck.add(new LeperGnome(),2);
		enemyDeck = enemyDeck.add(new SouthseaDeckhand(),2);
		enemyDeck = enemyDeck.add(new KnifeJuggler(),2);
		enemyDeck = enemyDeck.add(new LootHoarder(),1);
		enemyDeck = enemyDeck.add(new Wolfrider(), 1);
		enemyDeck = enemyDeck.add(new NoviceEngineer(), 2);
		enemyDeck = enemyDeck.add(new DragonlingMechanic(), 1);
		
		Hero hero1 = new Uther("Uther",TargetsType.ALLYCHAR,30,30,0,0,0,new Hand(), myDeck, 0, 0, null);
		Hero hero2 = new Uther("Uther2",TargetsType.ENEMYCHAR,30,30,0,0,0,new Hand(), enemyDeck, 0, 0, null);
		
	//	MyTurnState config = new BoardState(ViewType.UNBIASED,hero1,hero2,new ArrayList<Minion>(),new ArrayList<Minion>(),new ArrayList<Integer>(),0,false);
		
	/*	config = config.drawCard();
		config = config.drawCard();
		config = config.drawCard();
		config = config.drawCard();
		config = config.resolveRNG(true);
		config = config.enemyDrawCard();
		config = config.enemyDrawCard();
		config = config.enemyDrawCard();
		config = config.enemyDrawCard();
		config = config.resolveRNG(true);
		config = config.resolveRNG(true);
		MCTSNode ndai = new MCTS(new RandomPlayout(),new RandomPlayout()).solution(config, goalTest, 0);
		System.out.println("dsifnvisnfinfisenfisenfisn");
		MCTSNode temp = ndai;
		Stack<Action> stack = new Stack<Action>();
		while (temp!=null) {
			stack.push(temp.getAction());
			temp = temp.getParent();
		}
		System.out.println(stack.size());
		while (!stack.isEmpty()) {
			Action printe = stack.pop();
			if (printe!=null) printe.print();
		}
		System.out.println("dsifnvisnfinfisenfisenfisn");
		config = (MyTurnState) ndai.getState();
		//config = config.getActionResult(new PlayCard(new KnifeJuggler(),hero1,0));
		//config = config.resolveRNG(true);
		//config = config.getActionResult(new PlayCard(new ShieldedMinibot(),hero1,0));
		//config = config.resolveRNG(true);
		
		//config = config.getActionResult(new PlayCard(new Ragnaros(),hero1,0));
		//config = ((MyTurnState) config).resolveRNG(true);
		//config = config.getActionResult(new EndTurn());
		//config = ((MyTurnState) config).resolveRNG(true);
		config = config.resolveRNG(true);
		config.print();*/
		
		int gamecount = 0;
		int onescore = 0;
		int twoscore = 0;
		
		ArrayList<GamePlay> games = new ArrayList<GamePlay>();
		
	//	for (int i = 0; i <= 10; i++) {
		//	for (int j = 0; j<=10; j++) {
				
				double x = (double) 1.1;
				double y = (double) 0.5;
				
		
				Frontier frontier = new BestFirstFrontier(x,1-x);
				Frontier frontier2 = new BestFirstFrontier(y,1-y);
		
				while (gamecount<100) {
			
					//Player player1 = new Player(new RandomPlayout(), hero1);
					Player player1 = new Player(new BlindGreedySearch(frontier),hero1);
					//Player player1 = new Player(new MCTS(new BlindGreedySearch(frontier),new BlindGreedySearch(frontier2)),hero1);
					Player player2 = new Player(new BlindGreedySearch(frontier2),hero2);
					//Player player2 = new Player(new RandomPlayout(), hero2);
			
					PlayGame simulation = new PlayGame(player1,player2);
					//PlayOut simulation = new PlayOut(config,new MCTS(new RandomPlayout(),new RandomPlayout()), new BlindGreedySearch(frontier2));
			
		
					if (simulation.play(true,null)==1) {onescore++; System.out.println("1:"+onescore);}
					else {twoscore++; System.out.println("2:"+twoscore);}
					games.add(simulation.gameplay);
					gamecount++;
					System.out.println("Games:"+gamecount);
				}
				
				System.out.println("Player 1: "+onescore+" , Player 2 :"+twoscore);
				gamecount = 0;
				onescore = 0;
				twoscore = 0;
			
		
		UI ui = new UI();
		ui.setGames(games);
		ui.buildScreen();
		
		//if (simulation.play()==1) System.out.println("Player 1 wins");
		//else System.out.println("Player 2 wins");
		
		/*System.out.println("A Star graph search on game");
		System.out.println();
		
		Node solution1 = search.solution(config, goalTest,1);
		Printer.printSolution(solution1);
		System.out.println("Total Nodes: "+(search.lastSearch()));
		System.out.println("Max Nodes: "+(frontier).maxNodes());*/
		
		
	
	}
}
