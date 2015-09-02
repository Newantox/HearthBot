package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Deck {
	
	private Map<Card, Integer> deck;
	
	public Deck(Map<Card, Integer> deck) {
		this.deck = deck;
	}
	
	public Deck add(Card card) {
		Map<Card, Integer> temp = deck;
		if (temp.containsKey(card)) temp.put(card, temp.get(card) + 1);
		else temp.put(card,1);
		return new Deck(temp);
	}
	
	public Deck remove(Card card) {
		Map<Card, Integer> temp = deck;
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
			tempdeck = this;
			temphand = state.getMyHand();
			tempdeck.remove(card);
			temphand.add(card);
			list.add(new StateProbabilityPair(new BoardState(state.getHero(),state.getEnemy(),state.getOppSide(),state.getMySide(),tempdeck,temphand),deck.get(card)/tempdeck.size()));
		}
		return new RandomState(list);
	}
}
