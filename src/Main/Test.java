package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Game.BoardState;
import Game.Card;
import Game.Deck;
import Game.GameGoalTest;
import Game.GamePrinting;
import Game.Hand;
import Game.MyTurnState;
import Game.ViewType;
import Game.Minions.AbusiveSergeant;
import Game.Minions.AcidicSwampOoze;
import Game.Minions.ArgentSquire;
import Game.Minions.GrimscaleOracle;
import Game.Minions.KnifeJuggler;
import Game.Minions.LeperGnome;
import Game.Minions.LootHoarder;
import Game.Minions.Murloc;
import Game.Minions.MurlocTidecaller;
import Game.Minions.MurlocWarleader;
import Game.Minions.ShieldedMinibot;
import Game.Minions.Wisp;
import Game.Minions.Wolfrider;
import Game.Actions.PlayCard;
import Game.Cards.Minions.AbusiveSergeantCard;
import Game.Cards.Minions.ArgentSquireCard;
import Game.Cards.Minions.GrimscaleOracleCard;
import Game.Cards.Minions.KnifeJugglerCard;
import Game.Cards.Minions.LeperGnomeCard;
import Game.Cards.Minions.LootHoarderCard;
import Game.Cards.Minions.MurlocCard;
import Game.Cards.Minions.MurlocScoutCard;
import Game.Cards.Minions.MurlocTidehunterCard;
import Game.Cards.Minions.MurlocWarleaderCard;
import Game.Cards.Minions.ShieldedMinibotCard;
import Game.Cards.Minions.SouthseaDeckhandCard;
import Game.Cards.Minions.WolfriderCard;
import Game.Cards.Spells.TargettedSpell.BlessingOfMight;
import Game.Cards.Spells.TargettedSpell.Fireball;
import Game.Cards.Spells.TargettedSpell.HammerOfWrath;
import Game.Cards.Spells.Untargetted.AvengingWrath;
import Game.Cards.Spells.Untargetted.Consecration;
import Game.Cards.Spells.Untargetted.DivineFavour;
import Game.Cards.Spells.Untargetted.Equality;
import Game.Cards.Spells.Untargetted.MusterForBattle;
import Game.Cards.Spells.Untargetted.TheCoin;
import Game.Cards.Weapons.SwordOfJusticeCard;
import Game.Cards.Weapons.TruesilverChampionCard;
import Game.Heroes.Hero;
import Game.Heroes.Uther;
import Game.Minions.Minion;
import Game.SummonEffects.KnifeJugglerSE;
import Game.SummonEffects.SummonEffect;
import Game.SummonEffects.SwordOfJusticeSE;
import Game.Weapons.FieryWarAxe;
import GameSearch.GameHeuristic;
import Search.AStarFunction;
import Search.BestFirstFrontier;
import Search.Frontier;
import Search.GraphSearch;
import Search.Node;
import Search.Search;
import Search.State;

public class Test {

	public static void main(String[] args) {
		GamePrinting Printer = new GamePrinting();
		GameGoalTest goalTest = new GameGoalTest();
		
		Frontier frontier = new BestFirstFrontier(new AStarFunction(new GameHeuristic()));
		
		Search search = new GraphSearch(frontier);
		
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
		
	//	myHand.add(new ArgentSquireCard());
	//	myHand.add(new Equality());
	//	myHand.add(new AbusiveSergeantCard());
	//	myHand.add(new DivineFavour());
	//	myHand.add(new TheCoin());
	//	myHand.add(new MurlocTidehunterCard());
	//	myHand.add(new MurlocScoutCard());
	//	myHand.add(new MurlocWarleaderCard());
	//	myHand.add(new MurlocCard());
	//	myHand.add(new GrimscaleOracleCard());
		
		//int startmana = 10;
		Deck myDeck = new Deck();
		myDeck = myDeck.add(new BlessingOfMight(),2);
		myDeck = myDeck.add(new Equality(),2);
		myDeck = myDeck.add(new ShieldedMinibotCard(),2);
		myDeck = myDeck.add(new SwordOfJusticeCard(),1);
	/*	myDeck = myDeck.add(new DivineFavour(),2);
		myDeck = myDeck.add(new MusterForBattle(),2);
		myDeck = myDeck.add(new TruesilverChampionCard(),2);
		myDeck = myDeck.add(new Consecration(),2);
		myDeck = myDeck.add(new HammerOfWrath(),2);
		myDeck = myDeck.add(new AvengingWrath(),1);
		myDeck = myDeck.add(new AbusiveSergeantCard(),2);
		myDeck = myDeck.add(new ArgentSquireCard(),2);
		myDeck = myDeck.add(new LeperGnomeCard(),2);
		myDeck = myDeck.add(new SouthseaDeckhandCard(),2);
		myDeck = myDeck.add(new KnifeJugglerCard(),2);
		myDeck = myDeck.add(new LootHoarderCard(),1);
		myDeck = myDeck.add(new WolfriderCard(), 1);*/
		
		Hero hero1 = new Uther("Uther",14,30,30,0,1,1,new Hand(), myDeck, 0, 0, null);
		Hero hero2 = new Uther("Uther2",15,30,30,0,1,1,new Hand(), myDeck, 0, 0, null);
		
		MyTurnState config = new BoardState(ViewType.UNBIASED,hero1,hero2,new ArrayList<Minion>(),new ArrayList<Minion>(),new ArrayList<Integer>(),0);
		
		/*config = config.drawCard();
		config = config.drawCard();
		config = config.drawCard();
		config = config.drawCard();
		config = config.resolveRNG();
		config = config.placeMinion(new KnifeJuggler(0));
		config = config.placeMinion(new ShieldedMinibot(1));
		config = config.resolveRNG();
		config.print();*/
		
		
		Player player1 = new Player(hero1);
		Player player2 = new Player(hero2);
		
		PlayGame simulation = new PlayGame(player1,player2);
		
		if (simulation.play()==1) System.out.println("Player 1 wins");
		else System.out.println("Player 2 wins");
		
		/*System.out.println("A Star graph search on game");
		System.out.println();
		
		Node solution1 = search.solution(config, goalTest,1);
		Printer.printSolution(solution1);
		System.out.println("Total Nodes: "+(search.lastSearch()));
		System.out.println("Max Nodes: "+(frontier).maxNodes());*/
		
		
	
	}
}
