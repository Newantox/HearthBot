package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Game.Heroes.Hero;

public class Deck {
	
	private Map<Card, Integer> deck;
	
	public Deck(Map<Card, Integer> deck) {
		this.deck = new HashMap<Card, Integer>();
		(this.deck).putAll(deck);
	}
	
	public Deck() {
		this.deck = new HashMap<Card, Integer>();
	}

	public Deck add(Card card, int amount) {
		Map<Card, Integer> temp = new HashMap<Card, Integer>();
		temp.putAll(deck);
		if (temp.containsKey(card)) temp.put(card, temp.get(card) + amount);
		else temp.put(card,amount);
		System.out.println("Card added, unique cards: "+temp.size());
		return new Deck(temp);
	}
	
	public Deck remove(Card card) {
		Map<Card, Integer> temp = new HashMap<Card, Integer>();
		temp.putAll(deck);
		if (temp.get(card) > 1) temp.put(card, temp.get(card) - 1);
		else temp.remove(card);
		return new Deck(temp);
	}
	
	public int getSize() {
		int k = 0;
		for (Card card : deck.keySet()) {
			k += deck.get(card);
		}
		return k;
	}
	
	public MyTurnState drawCard(BoardState state, Hero hero, int pos) {
		if (deck.size()<=0) return hero.fatigue(state);
		else {
			List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
			for (Card card : deck.keySet()) {
				Hero newHero = hero.fresh();
				
				Hand newHand = (hero.getMyHand()).add(pos,card);
				Deck newDeck = remove(card);
				
				newHero.setMyHand(newHand);
				newHero.setMyDeck(newDeck);
				
				double probability = ((double) deck.get(card))/getSize();
				if (newHero.getMyPos()==14) list.add(new StateProbabilityPair(new BoardState(state.getViewType(),newHero,state.getEnemy(),state.getOppSide(),state.getMySide(),state.getPositionsInPlayOrder(),state.getEnemyHandSize()), probability));
				else list.add(new StateProbabilityPair(new BoardState(state.getViewType(),state.getHero(),newHero,state.getOppSide(),state.getMySide(),state.getPositionsInPlayOrder(),state.getEnemyHandSize()+1), probability));
			}
			return new RandomState(list);
		}
	}
	
	public MyTurnState drawCard(BoardState state, Hero hero) {
		return drawCard(state,hero,hero.getMyHandSize());
	}
}
