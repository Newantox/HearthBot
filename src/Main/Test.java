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
import Game.Minions.AbusiveSergeant;
import Game.Minions.AcidicSwampOoze;
import Game.Minions.ArgentSquire;
import Game.Minions.KnifeJuggler;
import Game.Minions.LeperGnome;
import Game.Minions.Wisp;
import Game.Cards.Minions.AbusiveSergeantCard;
import Game.Cards.Minions.ArgentSquireCard;
import Game.Cards.Minions.KnifeJugglerCard;
import Game.Cards.Minions.LeperGnomeCard;
import Game.Cards.Minions.LootHoarderCard;
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

public class Test {

	public static void main(String[] args) {
		GamePrinting Printer = new GamePrinting();
		GameGoalTest goalTest = new GameGoalTest();
		
		Minion[] initOpp = new Minion[7];
		Minion[] initMy = new Minion[7];
		Hand myHand = new Hand();
		Deck myDeck = new Deck(new HashMap<Card,Integer>());
		ArrayList<SummonEffect> summonEffects = new ArrayList<SummonEffect>();
		
		initOpp[0] = new LeperGnome(7);
		initOpp[1] = null;
		initOpp[2] = null;
		initOpp[3] = null;
		initOpp[4] = null;
		initOpp[5] = null;
		initOpp[6] = null;

		initMy[0] = null;
		initMy[1] = null;
		initMy[2] = null;
		initMy[3] = null;
		initMy[4] = null;
		initMy[5] = null;
		initMy[6] = null;
		
	//	initMy[0].setReady(true);
	//	initMy[1].setReady(true);
	//	initMy[2].setReady(true);
	//	initMy[3].setReady(true);
	//	initMy[4].setReady(true);
	//	initMy[5].setReady(true);
	//  initMy[6].setReady(true);
		
		myHand.add(new ArgentSquireCard());
		myHand.add(new Equality());
		myHand.add(new AbusiveSergeantCard());
		myHand.add(new KnifeJugglerCard());
		//myHand.add(new DivineFavour());
		myHand.add(new TheCoin());
		
		int startmana = 1;
		
	//	summonEffects.add(new KnifeJugglerSE(14));
	//	summonEffects.add(new SwordOfJusticeSE(14));
		
		myDeck.add(new BlessingOfMight(),2);
		myDeck.add(new Equality(),2);
		myDeck.add(new ShieldedMinibotCard(),2);
		myDeck.add(new SwordOfJusticeCard(),1);
		myDeck.add(new DivineFavour(),2);
		myDeck.add(new MusterForBattle(),2);
		myDeck.add(new TruesilverChampionCard(),2);
		myDeck.add(new Consecration(),2);
		myDeck.add(new HammerOfWrath(),2);
		myDeck.add(new AvengingWrath(),1);
		myDeck.add(new AbusiveSergeantCard(),2);
		myDeck.add(new ArgentSquireCard(),2);
		myDeck.add(new LeperGnomeCard(),2);
		myDeck.add(new SouthseaDeckhandCard(),2);
		myDeck.add(new KnifeJugglerCard(),2);
		myDeck.add(new LootHoarderCard(),1);
		//myDeck.add(new WolfRiderCard(), 1);
		
		
		BoardState config = new BoardState(new Uther("Uther",14,30,30,0,startmana,startmana,0,null),new Hero("Anduin",15,30,30,0,1,1,0, null),initOpp,initMy,myDeck,myHand,summonEffects,4);
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
