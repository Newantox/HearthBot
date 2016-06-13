package Game.Buffs;

import Game.Minions.Minion;

public class AdditiveBuff extends Buff {
	
	private double id;
	private int atkChange;
	private int hpChange;
	private int costChange;
	private int filterValue;
	
	public AdditiveBuff(double id, int atkChange,int hpChange,int costChange) {
		this.id = id;
		this.atkChange = atkChange;
		this.hpChange = hpChange;
		this.costChange = costChange;
		this.filterValue = (int) Math.signum(atkChange+hpChange-costChange);
	}
	
	public int getFilterValue() {
		return filterValue;
	}

	public double getID() {
		return id;
	}
	
	public void apply(Minion minion) {
		minion.setAtk(minion.getAtk()+atkChange);
		
		minion.setMaxHP(minion.getMaxHP()+hpChange);
		if (hpChange<0) minion.setDamageTaken(Math.max(0,minion.getDamageTaken()+hpChange));
		
		minion.setCost(minion.getCost()+costChange);
	}
	
	public void remove(Minion minion) {
		System.out.println("REMOVING");
		minion.setAtk(minion.getAtk()-atkChange);
		
		minion.setMaxHP(minion.getMaxHP()-hpChange);
		if (hpChange>0) minion.setDamageTaken(Math.max(0,minion.getDamageTaken()-hpChange));
		
		minion.setCost(minion.getCost()-costChange);
	}
	
}
