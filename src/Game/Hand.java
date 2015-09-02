package Game;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand;
	
	public Hand() {
		this.hand = new ArrayList<Card>();
	}
	public Hand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public Hand add(Card card) {
		if (hand.size() < 10) {
			ArrayList<Card> temp = hand;
			temp.add(card);
			return new Hand(temp);
		}
		else return this;
	}
	
	public Hand remove(int i) {
		ArrayList<Card> temp = hand;
		if (i<temp.size()) {
		temp.remove(i);
		}
		return new Hand(temp);
	}
	
	public ArrayList<Card> raw() {
		return hand;
	}
	
	public int getSize() {
		return hand.size();
	}
}
