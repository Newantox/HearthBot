package Game.Minions;

import Game.BoardState;
import Game.Heroes.Hero;

public class LeperGnome extends Minion {
	private String name;
	private int mypos;
	private int cost;
	private int atk;
	private int hp;
	private int maxHP;
	private boolean ready;
	private boolean charge;
	private boolean divineshield;
	private boolean taunt;
	private boolean stealth;

	public LeperGnome(int target) {
		super(target);
		this.name = "Leper Gnome";
		this.cost = 1;
		this.atk = 2;
		this.hp = 1;
		this.maxHP = 1;
		this.ready = false;
		this.charge = false;
		this.divineshield = false;
		this.taunt = false;
		this.stealth = false;
	}
	
	public LeperGnome(Minion m) {
		super(m);
	}
	
	@Override
	public BoardState deathRattle(BoardState oldstate) {
		Hero targetHero;
		if (mypos<7) {
			targetHero = oldstate.getEnemy().fresh();
			return new BoardState(oldstate.getHero(),targetHero,oldstate.getCurrentMana(), oldstate.getTotalMana(), oldstate.getOppSide(), oldstate.getMySide(), oldstate.getMyDeck(), oldstate.getMyHand());	
		}
		
		else {
			targetHero = oldstate.getHero().fresh();
			return new BoardState(targetHero, oldstate.getEnemy(), oldstate.getCurrentMana(), oldstate.getTotalMana(), oldstate.getOppSide(), oldstate.getMySide(), oldstate.getMyDeck(), oldstate.getMyHand());	
		}
	}

}
