package Game;

import java.util.ArrayList;

public class Hand {
	private ArrayList<PlayableCard> hand;
	
	public Hand() {
		this.hand = new ArrayList<PlayableCard>();
	}
	public Hand(ArrayList<PlayableCard> hand) {
		this.hand = hand;
	}
	
	public Hand add(int pos, PlayableCard card) {
		if (hand.size() < 10) {
			ArrayList<PlayableCard> temp = new ArrayList<PlayableCard>();
			temp.addAll(hand);
		//	System.out.println("Sizebefore"+temp.size()+card.getName());
			temp.add(pos,card);
		//	System.out.println("Sizeafter"+temp.size()+card.getName());
			return new Hand(temp);
		}
		else return this;
	}
	
	public Hand add(PlayableCard card) {
		return add(hand.size(),card);
	}
	
	public Hand remove(int i) {
		ArrayList<PlayableCard> temp = new ArrayList<PlayableCard>();
		temp.addAll(hand);
		if (i<temp.size()) temp.remove(i);
		return new Hand(temp);
	}
	
	public ArrayList<PlayableCard> raw() {
		return hand;
	}
	
	public int getSize() {
		return hand.size();
	}
	
	public void print() {
		String s = "   | ";
		for (PlayableCard card : hand) s = s+card.getName()+" | ";
		System.out.println(s);
	}
	
	public String output() {
		String s = "   | ";
		for (PlayableCard card : hand) s = s+card.getName()+" | ";
		return s;
	}
	
}
