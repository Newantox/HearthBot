package Game.Buffs;

import Game.Minions.Minion;

public class AttributeBuff extends Buff {
	
	private double id;
	
	private int chargeChange;
	private int divineshieldChange;
	private int tauntChange;
	private int stealthChange;
	private int windfuryChange;
	private int spelldamageChange;
	private int frozenChange;
	private int immuneChange;
	private int filterValue;
	
	// 1 represents giving the minion the attribute, -1 taking it away, and 0 no change.
	public AttributeBuff(double id, int chargeChange , int divineshieldChange, int tauntChange, int stealthChange, int windfuryChange, int spelldamageChange, int frozenChange, int immuneChange) {
		this.chargeChange = chargeChange;
		this.divineshieldChange = divineshieldChange;
		this.tauntChange = tauntChange;
		this.stealthChange = stealthChange;
		this.windfuryChange = windfuryChange;
		this.spelldamageChange = spelldamageChange;
		this.frozenChange = frozenChange;
		this.immuneChange = immuneChange;
		this.filterValue = (int) Math.signum(chargeChange+divineshieldChange+tauntChange+stealthChange+windfuryChange+spelldamageChange-frozenChange+immuneChange);
	}
	
	public int getFilterValue() {
		return filterValue;
	}

	public double getID() {
		return id;
	}
	
	public void apply(Minion minion) {
		if (chargeChange==1) minion.setCharge(true);
		else if (chargeChange==-1) minion.setCharge(false);
		
		if (divineshieldChange==1) minion.setDivineShield(true);
		else if (divineshieldChange==-1) minion.setDivineShield(false);
		
		if (tauntChange==1) minion.setTaunt(true);
		else if (tauntChange==-1) minion.setTaunt(false);
		
		if (stealthChange==1) minion.setStealth(true);
		else if (stealthChange==-1) minion.setStealth(false);
		
		if (windfuryChange==1) minion.setMaxAttacks(2);
		else if (windfuryChange==-1) minion.setMaxAttacks(1);
		
		minion.setSpelldamage(minion.getSpelldamage()+spelldamageChange);
		
		if (frozenChange==1) minion.setFrozen(true);
		else if (frozenChange==-1) minion.setFrozen(false);
		
		if (immuneChange==1) minion.setImmune(true);
		else if (immuneChange==-1) minion.setImmune(false);
	}
	
	public void remove(Minion minion) {
		if (chargeChange==-1) minion.setCharge(true);
		else if (chargeChange==1) minion.setCharge(false);
		
		if (divineshieldChange==-1) minion.setDivineShield(true);
		else if (divineshieldChange==1) minion.setDivineShield(false);
		
		if (tauntChange==-1) minion.setTaunt(true);
		else if (tauntChange==1) minion.setTaunt(false);
		
		if (stealthChange==-1) minion.setStealth(true);
		else if (stealthChange==1) minion.setStealth(false);
		
		if (windfuryChange==-1) minion.setMaxAttacks(2);
		else if (windfuryChange==1) minion.setMaxAttacks(1);
		
		minion.setSpelldamage(minion.getSpelldamage()-spelldamageChange);
		
		if (frozenChange==-1) minion.setFrozen(true);
		else if (frozenChange==1) minion.setFrozen(false);
		
		if (immuneChange==-1) minion.setImmune(true);
		else if (immuneChange==1) minion.setImmune(false);
	}
	
}
