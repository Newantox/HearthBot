package Game;

public class Weapon {
	private String name;
	private int atk;
	private int durability;
	
	public Weapon(String name, int atk, int durability) {
		this.name = name;
		this.atk = atk;
		this.setDurability(durability);
	}
	
	public Weapon(Weapon w) {
			this.name = w.name;
			this.setAtk(w.getAtk());
			this.setDurability(w.getDurability());
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAtk() {
		return atk;
	}
	
	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	@Override
	public boolean equals(Object that) {
		Weapon other = (Weapon)that;
		if (name != other.getName()) return false;
		if (atk != other.getAtk()) return false;
		if (durability != other.getDurability()) return false;
		return true;
	} 
	
}
