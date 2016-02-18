package Game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Game.Heroes.Hero;

public class Deck {
	
	private Map<PlayableCard, Integer> deck;
	
	public Deck(Map<PlayableCard, Integer> deck) {
		this.deck = new HashMap<PlayableCard, Integer>();
		(this.deck).putAll(deck);
	}
	
	public Deck() {
		this.deck = new HashMap<PlayableCard, Integer>();
	}

	public Deck add(PlayableCard card, int amount) {
		Map<PlayableCard, Integer> temp = new HashMap<PlayableCard, Integer>();
		temp.putAll(deck);
		if (temp.containsKey(card)) temp.put(card, temp.get(card) + amount);
		else temp.put(card,amount);
		return new Deck(temp);
	}
	
	public Deck remove(PlayableCard card) {
		Map<PlayableCard, Integer> temp = new HashMap<PlayableCard, Integer>();
		temp.putAll(deck);
		if (temp.get(card) > 1) temp.put(card, temp.get(card) - 1);
		else temp.remove(card);
		return new Deck(temp);
	}
	
	public int getSize() {
		int k = 0;
		for (PlayableCard card : deck.keySet()) {
			k += deck.get(card);
		}
		return k;
	}
	
	public MyTurnState drawCard(BoardState state, Hero hero, int pos) {
		if (deck.size()<=0) return hero.fatigue(state);
		else {
			List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
			for (PlayableCard card : deck.keySet()) {
				Hero newHero = hero.fresh();
				
				Hand newHand = new Hand(newHero.getMyHand().raw());
				if (newHand.getSize()<10) newHand = newHand.add(pos,card);
				Deck newDeck = remove(card);
				
				newHero.setMyHand(newHand);
				newHero.setMyDeck(newDeck);
				
				double probability = ((double) deck.get(card))/getSize();
				if (newHero.getSide().equals(TargetsType.ALLYCHAR)) list.add(new StateProbabilityPair(new BoardState(state.getViewType(),newHero,state.getEnemy(),state.getOppSide(),state.getMySide(),state.getIdsInPlayOrder(),state.getEnemyHandSize(),state.isTurnEnded(),state.getIdCounter()), probability, newHero.getName()+" draws "+card.getName()));
				else list.add(new StateProbabilityPair(new BoardState(state.getViewType(),state.getHero(),newHero,state.getOppSide(),state.getMySide(),state.getIdsInPlayOrder(),Math.min(10,state.getEnemyHandSize()+1),state.isTurnEnded(),state.getIdCounter()), probability, newHero.getName()+" draws "+card.getName()));
			}
			return new RandomState(list);
		}
	}
	
	public MyTurnState drawCard(BoardState state, Hero hero) {
		return drawCard(state,hero,hero.getMyHandSize());
	}
}
