package Game.Heroes;

import Game.Weapon;
import Game.Heroes.HeroPowers.HeroPower;

public class Hero {
	private String name;
	private int HP;
	private int Armour;
	private Weapon weapon;
	private boolean ready;
	private boolean powerUsed;
	
	public Hero(String name, int HP, int Armour, Weapon weapon) {
		this.setName(name);
		this.HP = HP;
		this.Armour = Armour;
		this.weapon = weapon;
		this.ready = true;
		this.powerUsed = false;
	}
	
	public Hero(Hero h) {
		this.setName(h.getName());
		this.Armour = h.Armour;
		this.setHP(h.getHP());
		this.setReady(h.isReady());
		this.powerUsed = h.powerUsed;
	}
	
	public Hero fresh() {
		return new Hero(this);
	}

	public boolean canAttack() {
		return (weapon!=null && ready && this.getWeapon().getDurability()>0);
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public int getArmour() {
		return Armour;
	}

	public void setArmour(int Armour) {
		this.Armour = Armour;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getPowerUsed() {
		return powerUsed;
	}
	
	public void setPowerUsed(boolean b) {
		powerUsed = b;
	}

	public boolean isAttackable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void destroyWeapon() {
		weapon = null;
	}
	
	public HeroPower getHeroPower() {
		return null;
	}
	
	@Override
	public boolean equals(Object that) {
		Hero other = (Hero)that;
		if (name != other.getName()) return false;
		if (HP != other.getHP()) return false;
		if (Armour != other.getArmour()) return false;
		if (weapon==null && other.getWeapon() != null) return false;
		if (weapon!= null && other.getWeapon() == null) return false;
		if (weapon==null && other.getWeapon() == null);
		else if (!weapon.equals(other.getWeapon())) return false;
		if (ready != other.isReady()) return false;
		return true;
	} 
}
