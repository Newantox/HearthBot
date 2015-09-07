package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Deck {
	
	private Map<Card, Integer> deck;
	
	public Deck(Map<Card, Integer> deck) {
		this.deck = new HashMap<Card, Integer>();
		(this.deck).putAll(deck);
	}
	
	public Deck add(Card card, int amount) {
		Map<Card, Integer> temp = new HashMap<Card, Integer>();
		temp.putAll(deck);
		if (temp.containsKey(card)) temp.put(card, temp.get(card) + amount);
		else temp.put(card,amount);
		return new Deck(temp);
	}
	
	public Deck remove(Card card) {
		Map<Card, Integer> temp = new HashMap<Card, Integer>();
		temp.putAll(deck);
		if (temp.get(card) > 1) temp.put(card, temp.get(card) - 1);
		else temp.remove(card);
		return new Deck(temp);
	}
	
	public ArrayList<Card> generate() {
		ArrayList<Card> temp = new ArrayList<Card>();
		for (Card card : deck.keySet()) {
			for (int j = 0; j < deck.get(card); j++) {
				temp.add(card);
			}
		}
		Collections.shuffle(temp);
		return temp;
	}
	
	public int size() {
		int k = 0;
		for (Card card : deck.keySet()) {
			k += deck.get(card);
		}
		return k;
	}
	
	public RandomState drawCard(BoardState state) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		Deck tempdeck;
		Hand temphand;
		for (Card card : deck.keySet()) {
			tempdeck = new Deck(deck);
			temphand = new Hand(state.getMyHand().raw());
			tempdeck.remove(card);
			temphand.add(card);
			list.add(new StateProbabilityPair(new BoardState(state.getHero(),state.getEnemy(),state.getOppSide(),state.getMySide(),tempdeck,temphand,state.getSummonEffects(),state.getEnemyHandSize()),deck.get(card)/deck.size()));
		}
		return new RandomState(list);
	}
}
